package kr.hopae.core.controller;

import kr.hopae.aries.model.*;
import kr.hopae.aries.repository.AriesRepository;
import kr.hopae.baekseok.model.LoginInfo;
import kr.hopae.baekseok.repository.BUAuthRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping(value = "/api/did/issuer")
public class IssuerController {

    @Resource(name = "ariesRepository")
    private AriesRepository ariesRepository;

    @RequestMapping(value = "/create-session", method = RequestMethod.GET)
    public Map<String, Object> requestAgent(HttpSession session,
                                            @RequestParam int univerGu,
                                            @RequestParam String userId,
                                            @RequestParam String userPw) throws Exception {

        // Initialize BUAuthRepository
        BUAuthRepository buAuthRepository = new BUAuthRepository();

        // Request login
        if (!buAuthRepository.requestLogin(univerGu, userId, userPw)) {
            throw new Exception("Login failure");
        }

        // Request LoginInfo
        LoginInfo loginInfo = buAuthRepository.requestLoginInfo();

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Generate Alias
        String alias = "hopae-".concat(loginInfo.userId).concat("-").concat(UUID.randomUUID().toString());

        // Set LoginInfo attribute
        session.setAttribute("LoginInfo", loginInfo);
        session.setAttribute("Alias", alias);

        Map<String, Object> map = new HashMap<>();

        map.put("port", 1062);
        map.put("alias", alias);

        return map;
    }

    @RequestMapping(value = "/create-invitation", method = RequestMethod.GET)
    public ConnectionInvitation requestCreateInvitation(HttpSession session) throws Exception {

        // Check LoginInfo
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Get Attributes from session
        String alias = (String) session.getAttribute("Alias");

        // Create Invitation
        InvitationResult invitationResult = ariesRepository.createInvitation(alias, true);
        session.setAttribute("ConnectionId", invitationResult.connectionId);

        return invitationResult.invitation;
    }

    @RequestMapping(value = "/receive-invitation", method = RequestMethod.POST)
    public ConnRecord requestReceiveInvitation(HttpSession session,
                                               @RequestBody ConnectionInvitation invitation) throws Exception {

        // Check LoginInfo
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Get attributes from session
        String alias = (String) session.getAttribute("Alias");

        // Receive Invitation
        ConnRecord connRecord = ariesRepository.receiveInvitation(alias, true, invitation);
        session.setAttribute("ConnectionId", connRecord.connectionId);

        return connRecord;
    }

    @RequestMapping(value = "/create-schema", method = RequestMethod.GET)
    public Map<String, Object> requestCreateSchema(HttpSession session) throws Exception {

        // Check LoginInfo
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Config Schema
        SchemaSendRequest schemaSendRequest = new SchemaSendRequest();
        schemaSendRequest.schemaName = "hopae_pass";
        schemaSendRequest.schemaVersion = "1.0";
        schemaSendRequest.attributes = new ArrayList<>();
        schemaSendRequest.attributes.add("student_id");
        schemaSendRequest.attributes.add("name");
        schemaSendRequest.attributes.add("temp");
        schemaSendRequest.attributes.add("timestamp");

        // Create Schema
        TxnOrSchemaSendResult schemaSendResult = ariesRepository.createSchemas(null, schemaSendRequest);
        session.setAttribute("SchemaId", schemaSendResult.sent.schemaId);

        Map<String, Object> map = new HashMap<>();

        map.put("schema_id", schemaSendResult.sent.schemaId);

        return map;
    }

    @RequestMapping(value = "/create-credential-def", method = RequestMethod.GET)
    public Map<String, Object> requestCreateCredentialDef(HttpSession session) throws Exception {

        // Check LoginInfo
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Get attributes from session
        String schemaId = (String) session.getAttribute("SchemaId");

        // Config Credential Definition
        CredentialDefinitionSendRequest creDefSendRequest = new CredentialDefinitionSendRequest();
        creDefSendRequest.revocationRegistrySize = 1000;
        creDefSendRequest.schemaId = schemaId;
        creDefSendRequest.supportRevocation = true;
        creDefSendRequest.tag = "revocable";

        // Define Credential definition
        TxnOrCredentialDefinitionSendResult creDefSendResult = ariesRepository.createCredentialDef(null, creDefSendRequest);
        session.setAttribute("CredDefId", creDefSendResult.sent.credentialDefinitionId);

        Map<String, Object> map = new HashMap<>();

        map.put("cred_def_id", creDefSendResult.sent.credentialDefinitionId);

        return map;
    }

    @RequestMapping(value = "/issue-credential", method = RequestMethod.GET)
    public Map<String, Object> requestIssueCredential(HttpSession session,
                                                      @RequestParam String temp) throws Exception {

        // Check LoginInfo
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");

        if (!loginInfo.isLogined) {
            throw new Exception("Login Info is not valid");
        }

        // Get attributes from session
        String connectionId = (String) session.getAttribute("ConnectionId");
        String schemaId = (String) session.getAttribute("SchemaId");
        String credDefId = (String) session.getAttribute("CredDefId");

        // Config Issue Credential
        V20CredProposalRequestPreviewMand crePropRequest = new V20CredProposalRequestPreviewMand();
        crePropRequest.autoRemove = true;
        crePropRequest.comment = "string";
        crePropRequest.connectionId = connectionId;
        crePropRequest.credentialPreview = new V20CredPreview();
        crePropRequest.credentialPreview.type = "issue-credential/2.0/credential-preview";
        crePropRequest.credentialPreview.attributes = new ArrayList<>();
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "student_id", loginInfo.userId));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "name", loginInfo.userName));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "temp", temp));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "timestamp", String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))));
        crePropRequest.filter = new V20CredProposalRequestPreviewMand.Filter();
        crePropRequest.filter.indy = new V20CredProposalRequestPreviewMand.Filter.Indy();
        crePropRequest.filter.indy.credDefId = credDefId;
        crePropRequest.filter.indy.issuerDID = credDefId.split(":")[0];
        crePropRequest.filter.indy.schemaId = schemaId;
        crePropRequest.filter.indy.schemaIssuerDID = crePropRequest.filter.indy.issuerDID;
        crePropRequest.filter.indy.schemaName = schemaId.split(":")[2];
        crePropRequest.filter.indy.schemaVersion = schemaId.split(":")[3];
        crePropRequest.trace = false;

        // Send Credential
        V20CredExRecord credExRecord = ariesRepository.issueCredential(crePropRequest);
        session.setAttribute("CredExId", credExRecord.credExId);

        Map<String, Object> map = new HashMap<>();

        map.put("cred_ex_id", credExRecord.credExId);
        map.put("thread_id", credExRecord.threadId);
        map.put("created_at", credExRecord.createdAt);

        return map;
    }

    @RequestMapping(value = "/revoke-credential", method = RequestMethod.GET)
    public Map<String, Object> requestRevokeCredential(@RequestParam(required = false) String credExId,
                                                       @RequestParam(required = false) String credRevId,
                                                       @RequestParam(required = false) String revRegId) throws Exception {

        // Config Revoke Request
        RevokeRequest revokeRequest = new RevokeRequest();
        revokeRequest.credExId = credExId;
        revokeRequest.credRevId = credRevId;
        revokeRequest.revRegId = revRegId;
        revokeRequest.publish = true;

        // Revoke Credential
        Map<String, Object> map = new HashMap<>();

        map.put("revoked", ariesRepository.revokeCredential(revokeRequest));

        return map;
    }
}
