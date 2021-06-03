package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class V20CredPreview {

    @SerializedName("@type")
    public String type;

    @SerializedName("attributes")
    public List<V20CredAttrSpec> attributes;

}
