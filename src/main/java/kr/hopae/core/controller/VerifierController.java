package kr.hopae.core.controller;

import kr.hopae.aries.model.*;
import kr.hopae.aries.repository.AriesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping(value = "/api/did/verifier")
public class VerifierController {

    @Resource(name = "ariesRepository")
    private AriesRepository ariesRepository;

    @RequestMapping(value = "/prove", method = RequestMethod.GET)
    public Map<String, Object> requestProof(@RequestParam String alias) throws Exception {

        // Get Connections
        ConnectionList connectionList = ariesRepository.getConnections(alias);
        ConnRecord connRecord = connectionList.results.get(0);

        // Get Credential Definitions
        CredentialDefinitionsCreatedResult creDefCreatedResult = ariesRepository.getCredentialDefs("hopae_pass");
        String creDefId = creDefCreatedResult.credentialDefinitionIds.get(creDefCreatedResult.credentialDefinitionIds.size() - 1);

        // Config Request
        V20PresSendRequestRequest presSendReqRequest = new V20PresSendRequestRequest();

        presSendReqRequest.comment = "string";
        presSendReqRequest.connectionId = connRecord.connectionId;
        presSendReqRequest.presentationRequest = new V20PresRequestByFormat();
        presSendReqRequest.presentationRequest.indy = new V20PresRequestByFormat.Indy();
        presSendReqRequest.presentationRequest.indy.name = "Proof of Hopae Pass";
//        presSendReqRequest.presentationRequest.indy.nonRevoked = new V20PresRequestByFormat.Indy.NonRevoked();
//        presSendReqRequest.presentationRequest.indy.nonRevoked.to = 1622833408;
        presSendReqRequest.presentationRequest.indy.version = "1.0";
        presSendReqRequest.presentationRequest.indy.requestedAttributes = new HashMap<>();

        List<Map<String, Object>> restrictions = new ArrayList<>();
        Map<String, Object> restriction = new HashMap<>();

        restriction.put("cred_def_id", creDefId);
        restrictions.add(restriction);

        presSendReqRequest.presentationRequest.indy.requestedAttributes.put(
                "0_timestamp_uuid", new IndyProofReqAttrSpec("timestamp", null, null, restrictions));
        presSendReqRequest.presentationRequest.indy.requestedAttributes.put("0_student_id_uuid",
                new IndyProofReqAttrSpec("student_id", null, null, restrictions));
        presSendReqRequest.presentationRequest.indy.requestedAttributes.put("0_name_uuid",
                new IndyProofReqAttrSpec("name", null, null, restrictions));

        presSendReqRequest.presentationRequest.indy.requestedPredicates = new HashMap<>();
        presSendReqRequest.presentationRequest.indy.requestedPredicates.put("0_temp_L_uuid",
                new IndyProofReqPredSpec("temp", null, "<", 375, restrictions));

        // Send proof request
        V20PresExRecord presExRecord = ariesRepository.requestProof(presSendReqRequest);

        return verify(presExRecord.presExId);
    }

    @RequestMapping(value = "/verified", method = RequestMethod.GET)
    public Map<String, Object> verify(@RequestParam String presExId) throws Exception {
        Map<String, Object> map = new HashMap<>();

        // Verify Presentation
        map.put("verified", ariesRepository.getPresentation(presExId).verified.equals("true"));

        return map;
    }
}
