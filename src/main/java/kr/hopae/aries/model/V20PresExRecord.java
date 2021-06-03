package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * POST /present-proof-2.0/records/{pres_ex_id}
 * Response Model
 */
public class V20PresExRecord {

    @SerializedName("auto_present")
    public boolean autoPresent;

    @SerializedName("by_format")
    public Map<String, Object> byFormat;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("error_msg")
    public String errorMsg;

    @SerializedName("initiator")
    public String initiator;

    @SerializedName("pres")
    public Map<String, Object> pres;

    @SerializedName("pres_ex_id")
    public String presExId;

    @SerializedName("pres_proposal")
    public Map<String, Object> presProposal;

    @SerializedName("pres_request")
    public Map<String, Object> presRequest;

    @SerializedName("role")
    public String role;

    @SerializedName("state")
    public String state;

    @SerializedName("thread_id")
    public String threadId;

    @SerializedName("trace")
    public boolean trace;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("verified")
    public String verified;

}
