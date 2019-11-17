package m.nicholas.mealville.network;

import java.lang.annotation.Target;

import m.nicholas.mealville.models.ApiSearchResult;
import m.nicholas.mealville.models.Recipe;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RapidApi {
    @GET("recipes/search")
    Call<ApiSearchResult> getResults(@Query("query") String filterQuery, @Query("type") String type, @Query("number") int numberOfResults);

    @GET("recipes/{id}/information")
    Call<Recipe> getRecipes(@Path("id") int recipeId);
}
