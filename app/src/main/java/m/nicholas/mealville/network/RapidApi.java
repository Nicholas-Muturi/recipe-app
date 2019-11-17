package m.nicholas.mealville.network;

import m.nicholas.mealville.models.ApiSearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RapidApi {
    @GET("recipes/search")
    Call<ApiSearchResult> getRecipes(@Query("query") String filterQuery);

}
