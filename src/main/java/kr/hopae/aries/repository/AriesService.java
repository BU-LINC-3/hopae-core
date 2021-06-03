package kr.hopae.aries.repository;

import kr.hopae.aries.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface AriesService {

    @GET("/connections")
    Call<ConnectionList> getConnections(
            @Query("alias") String alias);

    @POST("/connections/create-invitation")
    Call<InvitationResult> createInvitation(
            @Query("alias") String alias,
            @Query("auto_accept") boolean autoAccept,
            @Body() Map<String, Object> body);

    @POST("/schemas")
    Call<TxnOrSchemaSendResult> createSchemas(
            @Query("conn_id") String connectionId,
            @Body() SchemaSendRequest body);

    @POST("/credential-definitions")
    Call<TxnOrCredentialDefinitionSendResult> createCredentialDef(
            @Query("conn_id") String connectionId,
            @Body() CredentialDefinitionSendRequest body);

    @GET("/credential-definitions/created")
    Call<CredentialDefinitionsCreatedResult> getCredentialDefs(
            @Query("schema_name") String schemaName);

    @POST("/issue-credential-2.0/send")
    Call<V20CredExRecord> issueCredential(
            @Body() V20CredProposalRequestPreviewMand body);

    @POST("/present-proof-2.0/send-request")
    Call<V20PresExRecord> requestProof(
            @Body() V20PresSendRequestRequest body);

    @POST("/present-proof-2.0/records/{pres_ex_id}/verify-presentation")
    Call<V20PresExRecord> verifyPresentation(
            @Path("pres_ex_id") String presExId);

    @GET("/present-proof-2.0/records/{pres_ex_id}")
    Call<V20PresExRecord> getPresentation(
            @Path("pres_ex_id") String presExId);

}
