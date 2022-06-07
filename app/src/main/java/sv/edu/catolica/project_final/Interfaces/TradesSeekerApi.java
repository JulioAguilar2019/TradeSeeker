package sv.edu.catolica.project_final.Interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sv.edu.catolica.project_final.Models.CategoryModel;
import sv.edu.catolica.project_final.Models.LoginModel;
import sv.edu.catolica.project_final.Models.LoginResponseModel;
import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkStoreModel;

public interface TradesSeekerApi {

    @GET("api/works")
    Call<List<WorkModel>> getWorks();

    @GET("api/get-categories")
    Call<List<CategoryModel>> getCategories();

    @POST("api/saveWorks")
    Call<String> saveWorks (@Header("Authorization") String authToken, @Body WorkStoreModel storeModel);

    @POST("api/logout")
    Call<String> logout ();
}
