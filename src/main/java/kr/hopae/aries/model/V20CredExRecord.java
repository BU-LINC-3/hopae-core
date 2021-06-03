package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * POST /issue-credential-2.0/send
 * Response Model
 */
public class V20CredExRecord {

    @SerializedName("auto_issue")
    public boolean autoIssue;

    @SerializedName("auto_offer")
    public boolean autoOffer;

    @SerializedName("auto_remove")
    public boolean autoRemove;

    @SerializedName("by_format")
    public Map<String, Object> byFormat;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("cred_ex_id")
    public String credExId;

    @SerializedName("cred_id_stored")
    public String credIdStored;

    @SerializedName("cred_issue")
    public Map<String, Object> credIssue;

    @SerializedName("cred_offer")
    public Map<String, Object> credOffer;

    @SerializedName("cred_preview")
    public Map<String, Object> credPreview;

    @SerializedName("cred_proposal")
    public Map<String, Object> credProposal;

    @SerializedName("cred_request")
    public Map<String, Object> credRequest;

    @SerializedName("cred_request_metadata")
    public Map<String, Object> credRequestMetadata;

    @SerializedName("error_msg")
    public String errorMsg;

    @SerializedName("initiator")
    public String initiator;

    @SerializedName("parent_thread_id")
    public String parentThreadId;

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

}
