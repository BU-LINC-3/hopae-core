package kr.hopae.baekseok.repository;

import kr.hopae.baekseok.model.LoginInfo;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;

public class BUAuthRepository {

    private Retrofit retrofit;
    private BUAuthService service;

    public BUAuthRepository() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .cookieJar(new JavaNetCookieJar(new CookieManager()))
                        .build())
                .baseUrl("https://www.bu.ac.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BUAuthService.class);
    }

    public boolean requestLogin(int univerGu, String userId, String userPw) throws Exception {
        Call<ResponseBody> request = service.requestLogin(univerGu, userId, userPw);
        Response<ResponseBody> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.raw().request().method().equals("POST");
    }

    public LoginInfo requestLoginInfo() throws Exception {
        Call<LoginInfo> request = service.requestLoginInfo();
        Response<LoginInfo> response = request.execute();

        if (response.code() != 200 || response.body() == null) {
            throw new Exception(response.message());
        }

        return response.body();
    }

}
