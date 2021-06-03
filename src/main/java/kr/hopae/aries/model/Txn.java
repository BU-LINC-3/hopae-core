package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Txn {

    @SerializedName("_id")
    public String _id;

    @SerializedName("_type")
    public String _type;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("formats")
    public Map<String, Object> formats;

    @SerializedName("messages_attach")
    public Map<String, Object> messagesAttach;

    @SerializedName("signature_request")
    public Map<String, Object> signatureRequest;

    @SerializedName("signature_response")
    public Map<String, Object> signatureResponse;

    @SerializedName("state")
    public String state;

    @SerializedName("thread_id")
    public String threadId;

    @SerializedName("timing")
    public Map<String, Object> timing;

    @SerializedName("trace")
    public boolean trace;

    @SerializedName("updated_at")
    public String updatedAt;

}
