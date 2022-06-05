package sv.edu.catolica.project_final.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sv.edu.catolica.project_final.Models.LoginModel;
import sv.edu.catolica.project_final.Models.LoginResponseModel;

public interface LoginApi {
    @POST("api/login")
    Call<LoginResponseModel> login (@Body LoginModel login);

    @GET("api/user")
    Call<ResponseBody> getUser (@Header("Authorization") String authToken);
}
