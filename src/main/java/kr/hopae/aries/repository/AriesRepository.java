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
                .baseUrl("http://34.64.218.185:8021")
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
        Call<V20PresExRecord> request = service.getPresentation(presExId);
        Response<V20PresExRecord> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

}
