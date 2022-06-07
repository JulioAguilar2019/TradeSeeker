package sv.edu.catolica.project_final.Interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sv.edu.catolica.project_final.Models.CategoryModel;
import sv.edu.catolica.project_final.Models.HomeModel;
import sv.edu.catolica.project_final.Models.LoginModel;
import sv.edu.catolica.project_final.Models.LoginResponseModel;
import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkResponseModel;
import sv.edu.catolica.project_final.Models.WorkStoreModel;

public interface TradesSeekerApi {

    @GET("api/works")
    Call<List<WorkModel>> getWorks();

    @GET("api/home")
    Call<HomeModel> getHome();

    @GET("api/getWorks/{id}")
    Call<List<WorkModel>> getMyWorks(@Path("id") int id);

    @GET("api/get-categories")
    Call<List<CategoryModel>> getCategories();

    @POST("api/saveWorks")
    Call<ResponseBody> saveWorks (@Body WorkStoreModel storeModel);

    @POST("api/logout")
    Call<ResponseBody> logout (@Header("Authorization") String token);
}
