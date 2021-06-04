package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class IndyProofReqPredSpec {

    @SerializedName("name")
    public String name;

    @SerializedName("non_revoked")
    public V20PresRequestByFormat.Indy.NonRevoked nonRevoked;

    @SerializedName("p_type")
    public String pType;

    @SerializedName("p_value")
    public int pValue;

    @SerializedName("restrictions")
    public List<Map<String, Object>> restrictions;

    public IndyProofReqPredSpec() {
    }

    public IndyProofReqPredSpec(String name, V20PresRequestByFormat.Indy.NonRevoked nonRevoked, String pType, int pValue, List<Map<String, Object>> restrictions) {
        this.name = name;
        this.nonRevoked = nonRevoked;
        this.pType = pType;
        this.pValue = pValue;
        this.restrictions = restrictions;
    }
}
