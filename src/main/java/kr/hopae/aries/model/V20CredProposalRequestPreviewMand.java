package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /issue-credential-2.0/send
 * Request Model
 */
public class V20CredProposalRequestPreviewMand {

    @SerializedName("auto_remove")
    public boolean autoRemove;

    @SerializedName("comment")
    public String comment;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("credential_preview")
    public V20CredPreview credentialPreview;

    @SerializedName("filter")
    public Filter filter;

    @SerializedName("trace")
    public boolean trace;

    public static class Filter {

        @SerializedName("dif")
        public DIF dif;

        @SerializedName("indy")
        public Indy indy;

        public static class DIF {

            @SerializedName("some_dif_criterion")
            public String someDIFCriterion;

        }

        public static class Indy {

            @SerializedName("cred_def_id")
            public String credDefId;

            @SerializedName("issuer_did")
            public String issuerDID;

            @SerializedName("schema_id")
            public String schemaId;

            @SerializedName("schema_issuer_did")
            public String schemaIssuerDID;

            @SerializedName("schema_name")
            public String schemaName;

            @SerializedName("schema_version")
            public String schemaVersion;

        }

    }
}
