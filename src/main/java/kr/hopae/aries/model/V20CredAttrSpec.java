package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

public class V20CredAttrSpec {

    @SerializedName("mime-type")
    public String mimeType;

    @SerializedName("name")
    public String name;

    @SerializedName("value")
    public String value;

    public V20CredAttrSpec() {
    }

    public V20CredAttrSpec(String mimeType, String name, String value) {
        this.mimeType = mimeType;
        this.name = name;
        this.value = value;
    }
}
