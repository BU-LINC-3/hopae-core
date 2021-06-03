package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /present-proof-2.0/send-request
 * Request Model
 */
public class V20PresSendRequestRequest {

    @SerializedName("comment")
    public String comment;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("presentation_request")
    public V20PresRequestByFormat presentationRequest;

    @SerializedName("trace")
    public boolean trace;

}
