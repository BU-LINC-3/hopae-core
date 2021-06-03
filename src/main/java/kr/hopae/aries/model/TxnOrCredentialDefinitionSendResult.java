package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /credential-definitions
 * Response Model
 */
public class TxnOrCredentialDefinitionSendResult {

    @SerializedName("sent")
    public CredentialDefinitionSent sent;

    @SerializedName("txn")
    public Txn txn;

}
