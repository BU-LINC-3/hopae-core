package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

/**
 * POST /schemas
 * Response Model
 */
public class TxnOrSchemaSendResult {

    @SerializedName("sent")
    public SchemaSent sent;

    @SerializedName("txn")
    public Txn txn;

}
