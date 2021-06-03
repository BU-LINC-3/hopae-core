package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class V20PresRequestByFormat {

    @SerializedName("dif")
    public DIF dif;

    @SerializedName("indy")
    public Indy indy;

    public static class DIF {

        @SerializedName("some_dif")
        public String someDIF;

    }

    public static class Indy {

        @SerializedName("name")
        public String name;

        @SerializedName("non_revoked")
        public NonRevoked nonRevoked;

        @SerializedName("nonce")
        public String nonce;

        @SerializedName("requested_attributes")
        public Map<String, IndyProofReqAttrSpec> requestedAttributes;

        @SerializedName("requested_predicates")
        public Map<String, IndyProofReqPredSpec> requestedPredicates;

        @SerializedName("version")
        public String version;

        public static class NonRevoked {

            @SerializedName("to")
            int to;

        }
    }

}
