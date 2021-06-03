package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class IndyProofReqAttrSpec {

    @SerializedName("name")
    public String name;

    @SerializedName("names")
    public List<String> names;

    @SerializedName("non_revoked")
    public V20PresRequestByFormat.Indy.NonRevoked nonRevoked;

    @SerializedName("restrictions")
    public List<Map<String, Object>> restrictions;

    public IndyProofReqAttrSpec() {
    }

    public IndyProofReqAttrSpec(String name, List<String> names, V20PresRequestByFormat.Indy.NonRevoked nonRevoked, List<Map<String, Object>> restrictions) {
        this.name = name;
        this.names = names;
        this.nonRevoked = nonRevoked;
        this.restrictions = restrictions;
    }
}
