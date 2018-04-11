package origin.com.http.retrofit.service;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zc on 2018/4/8.
 */

public interface SimpleService {
    @FormUrlEncoded
    @POST("restful/usercenter/userCenter/loginByAccount")
    Call<JSONObject> reqLogin(@Field("channel") String channel, @Field("account") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("account/login")
    Call<JSONObject> reqLogin1(@Field("username") String username,
                                @Field("password") String password);
}
