package kr.hopae.aries.repository;

import kr.hopae.aries.model.*;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Repository("ariesRepository")
public class AriesRepository {

    private Retrofit retrofit;
    private AriesService service;

    public AriesRepository() {

        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build())
                .baseUrl("http://211.253.228.16:1061")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AriesService.class);
    }

    public ConnectionList getConnections(String alias) throws Exception {
        Call<ConnectionList> request = service.getConnections(alias);
        Response<ConnectionList> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public InvitationResult createInvitation(String alias, boolean autoAccept) throws Exception {
        Call<InvitationResult> request = service.createInvitation(alias, autoAccept, new HashMap<>());
        Response<InvitationResult> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public ConnRecord receiveInvitation(String alias, boolean autoAccept, ConnectionInvitation invitation) throws Exception {
        Call<ConnRecord> request = service.receiveInvitation(alias, autoAccept, invitation);
        Response<ConnRecord> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public TxnOrSchemaSendResult createSchemas(String connectionId, SchemaSendRequest body) throws Exception {
        Call<TxnOrSchemaSendResult> request = service.createSchemas(connectionId, body);
        Response<TxnOrSchemaSendResult> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public TxnOrCredentialDefinitionSendResult createCredentialDef(String connectionId, CredentialDefinitionSendRequest body) throws Exception {
        Call<TxnOrCredentialDefinitionSendResult> request = service.createCredentialDef(connectionId, body);
        Response<TxnOrCredentialDefinitionSendResult> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public CredentialDefinitionsCreatedResult getCredentialDefs(String schemaName) throws Exception {
        Call<CredentialDefinitionsCreatedResult> request = service.getCredentialDefs(schemaName);
        Response<CredentialDefinitionsCreatedResult> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public V20CredExRecord issueCredential(V20CredProposalRequestPreviewMand body) throws Exception {
        Call<V20CredExRecord> request = service.issueCredential(body);
        Response<V20CredExRecord> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public V20CredExRecordDetail getCredentialRecord(String credExId) throws Exception {
        return getCredentialRecord(credExId, 10);
    }

    private V20CredExRecordDetail getCredentialRecord(String credExId, int count) throws Exception {
        Call<V20CredExRecordDetail> request = service.getCredentialRecord(credExId);
        Response<V20CredExRecordDetail> response = request.execute();

        if (response.code() != 200 && response.code() != 404) {
            throw new Exception(response.message());
        }

        if (response.body() != null && !response.body().credExRecord.state.equals("done")) {
            if (count > 0) {
                Thread.sleep(1000);
                return getCredentialRecord(credExId, --count);
            }
            throw new Exception("State is not done");
        }

        return response.body();
    }

    public V20PresExRecord requestProof(V20PresSendRequestRequest body) throws Exception {
        Call<V20PresExRecord> request = service.requestProof(body);
        Response<V20PresExRecord> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public V20PresExRecord verifyPresentation(String presExId) throws Exception {
        Call<V20PresExRecord> request = service.verifyPresentation(presExId);
        Response<V20PresExRecord> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

    public V20PresExRecord getPresentation(String presExId) throws Exception {
        return getPresentation(presExId, 10);
    }

    private V20PresExRecord getPresentation(String presExId, int count) throws Exception {
        Call<V20PresExRecord> request = service.getPresentation(presExId);
        Response<V20PresExRecord> response = request.execute();

        if (response.code() != 200 && response.code() != 500) {
            throw new Exception(response.message());
        }

        if (response.body() == null || !response.body().state.equals("done")) {
            if (count > 0) {
                Thread.sleep(1000);
                return getPresentation(presExId, --count);
            }
            throw new Exception("State is not done");
        }

        return response.body();
    }

    public boolean revokeCredential(RevokeRequest body) throws Exception {
        Call<RevocationModuleResponse> request = service.revoke(body);
        Response<RevocationModuleResponse> response = request.execute();

        if (response.code() == 400) {
            return false;
        } else if (response.code() != 200) {
            throw new Exception(response.message());
        }

        return true;
    }

}
