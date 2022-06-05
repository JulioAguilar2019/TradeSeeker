package sv.edu.catolica.project_final.Interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sv.edu.catolica.project_final.Models.WorkModel;

public interface TradesSeekerApi {

    @GET("api/works")
    Call<List<WorkModel>> getWorks();
}
