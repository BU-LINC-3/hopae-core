package kr.hopae.aries.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * GET /issue-credential-2.0/records/{cred_ex_id}
 * Response Model
 */
public class V20CredExRecordDetail {

    @SerializedName("cred_ex_record")
    public V20CredExRecord credExRecord;

    @SerializedName("dif")
    public Map<String, Object> dif;

    @SerializedName("indy")
    public Map<String, Object> indy;

}
