package API;

import Model.AuthToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {


    @FormUrlEncoded
    @POST("login")
    Call <AuthToken> checkUser(@Field("username")String username,@Field("password")String password);


}
