package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * GET /credential-definitions/created
 * Response Model
 */
public class CredentialDefinitionsCreatedResult {

    @SerializedName("credential_definition_ids")
    public List<String> credentialDefinitionIds;

}
