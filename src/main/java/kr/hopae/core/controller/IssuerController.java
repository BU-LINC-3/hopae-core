package kr.hopae.core.controller;

import kr.hopae.aries.model.*;
import kr.hopae.aries.repository.AriesRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "/api/did/issuer")
public class IssuerController {

    @Resource(name = "ariesRepository")
    private AriesRepository ariesRepository;

    @RequestMapping(value = "/agent", method = RequestMethod.GET)
    public Map<String, Object> getAgent(@RequestParam String token) throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("port", 8031);
        map.put("alias", "holder-".concat(UUID.randomUUID().toString()));

        return map;
    }

    @RequestMapping(value = "/invitation", method = RequestMethod.GET)
    public ConnectionInvitation requestInvitation(@RequestParam String token,
                                                  @RequestParam String alias) throws Exception {

        // Create Invitation
        return ariesRepository.createInvitation(alias, true).invitation;
    }

    @RequestMapping(value = "/credential", method = RequestMethod.GET)
    public Map<String, Object> requestCredential(@RequestParam String token,
                                             @RequestParam String alias) throws Exception {

        // Get Connections
        ConnectionList connectionList = ariesRepository.getConnections(alias);
        ConnRecord connRecord = connectionList.results.get(0);

        // Config Schema
        SchemaSendRequest schemaSendRequest = new SchemaSendRequest();
        schemaSendRequest.schemaName = "account_schema";
        schemaSendRequest.schemaVersion = "1.0";
        schemaSendRequest.attributes = new ArrayList<>();
        schemaSendRequest.attributes.add("kakao_id");
        schemaSendRequest.attributes.add("nickname");
        schemaSendRequest.attributes.add("email");
        schemaSendRequest.attributes.add("gender");
        schemaSendRequest.attributes.add("timestamp");

        // Create Schema
        TxnOrSchemaSendResult schemaSendResult = ariesRepository.createSchemas(connRecord.connectionId, schemaSendRequest);

        // Config Credential definition
        CredentialDefinitionSendRequest creDefSendRequest = new CredentialDefinitionSendRequest();
        creDefSendRequest.revocationRegistrySize = 1000;
        creDefSendRequest.schemaId = schemaSendResult.sent.schemaId;
        creDefSendRequest.tag = alias;

        // Define Credential definition
        TxnOrCredentialDefinitionSendResult creDefSendResult = ariesRepository.createCredentialDef(connRecord.connectionId, creDefSendRequest);

        // Config Issue Credential
        V20CredProposalRequestPreviewMand crePropRequest = new V20CredProposalRequestPreviewMand();
        crePropRequest.autoRemove = true;
        crePropRequest.comment = "string";
        crePropRequest.connectionId = connRecord.connectionId;
        crePropRequest.credentialPreview = new V20CredPreview();
        crePropRequest.credentialPreview.type = "issue-credential/2.0/credential-preview";
        crePropRequest.credentialPreview.attributes = new ArrayList<>();
/*        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "kakao_id", String.valueOf(userInfo.id)));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "nickname", userInfo.kakaoAccount.profile.nickname));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "email", userInfo.kakaoAccount.isEmailValid ? userInfo.kakaoAccount.email : ""));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "gender", userInfo.kakaoAccount.isEmailValid ? userInfo.kakaoAccount.gender : ""));
        crePropRequest.credentialPreview.attributes.add(new V20CredAttrSpec(
                null, "timestamp", String.valueOf(new Date().getTime())));*/
        crePropRequest.filter = new V20CredProposalRequestPreviewMand.Filter();
        crePropRequest.filter.dif = new V20CredProposalRequestPreviewMand.Filter.DIF();
        crePropRequest.filter.dif.someDIFCriterion = "string";
        crePropRequest.filter.indy = new V20CredProposalRequestPreviewMand.Filter.Indy();
        crePropRequest.filter.indy.credDefId = creDefSendResult.sent.credentialDefinitionId;
        crePropRequest.filter.indy.issuerDID = creDefSendResult.sent.credentialDefinitionId.substring(
                0, creDefSendResult.sent.credentialDefinitionId.indexOf(":"));
        crePropRequest.filter.indy.schemaId = schemaSendResult.sent.schemaId;
        crePropRequest.filter.indy.schemaIssuerDID = crePropRequest.filter.indy.issuerDID;
        crePropRequest.filter.indy.schemaName = schemaSendRequest.schemaName;
        crePropRequest.filter.indy.schemaVersion = schemaSendRequest.schemaVersion;
        crePropRequest.trace = true;

        // Send credential
        V20CredExRecord credExRecord = ariesRepository.issueCredential(crePropRequest);

        Map<String, Object> map = new HashMap<>();

        map.put("state", credExRecord.state);
        map.put("thread_id", credExRecord.threadId);
        map.put("updated_at", credExRecord.updatedAt);

        return map;
    }
}
