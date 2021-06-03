package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SchemaSent {

    @SerializedName("schema")
    public Map<String, Object> schema;

    @SerializedName("schema_id")
    public String schemaId;

}
