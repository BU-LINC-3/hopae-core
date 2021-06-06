package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /revocation/revoke
 * Request Model
 */
public class RevokeRequest {

    @SerializedName("cred_ex_id")
    public String credExId;

    @SerializedName("cred_rev_id")
    public String credRevId;

    @SerializedName("publish")
    public boolean publish;

    @SerializedName("rev_reg_id")
    public String revRegId;

}
