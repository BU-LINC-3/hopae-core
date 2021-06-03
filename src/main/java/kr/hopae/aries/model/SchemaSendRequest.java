package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * POST /schemas
 * Request Model
 */
public class SchemaSendRequest {

    @SerializedName("attributes")
    public List<String> attributes;

    @SerializedName("schema_name")
    public String schemaName;

    @SerializedName("schema_version")
    public String schemaVersion;

}
