package kr.hopae.baekseok.repository;

import kr.hopae.baekseok.model.LoginInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface BUAuthService {

    @POST("/subLogin/web/login.do")
    Call<ResponseBody> requestLogin(@Query("univerGu") int univerGu,
                                    @Query("userId") String userId,
                                    @Query("userPwd") String userPw);

    @GET("/restful/checkLogined.do")
    Call<LoginInfo> requestLoginInfo();

}
