package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /connections/create-invitation
 * Response Model
 */
public class InvitationResult {

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("invitation")
    public ConnectionInvitation invitation;

    @SerializedName("invitation_url")
    public String invitationUrl;

}
