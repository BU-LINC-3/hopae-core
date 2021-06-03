package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /credential-definitions
 * Request Model
 */
public class CredentialDefinitionSendRequest {

    @SerializedName("revocation_registry_size")
    public int revocationRegistrySize;

    @SerializedName("schema_id")
    public String schemaId;

    @SerializedName("support_revocation")
    public boolean supportRevocation;

    @SerializedName("tag")
    public String tag;

}
