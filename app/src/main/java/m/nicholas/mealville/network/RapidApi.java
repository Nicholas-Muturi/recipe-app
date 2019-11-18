package m.nicholas.mealville.network;

import m.nicholas.mealville.models.ApiSearchResult;
import m.nicholas.mealville.models.FindByIngredients;
import m.nicholas.mealville.models.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RapidApi {
    @GET("recipes/search")
    Call<ApiSearchResult> getResults(@Query("query") String filterQuery, @Query("type") String type, @Query("number") int numberOfResults);

    @GET("recipes/search")
    Call<ApiSearchResult> getResults(@Query("query") String filterQuery, @Query("number") int numberOfResults);

    @GET("recipes/{id}/information")
    Call<Recipe> getRecipes(@Path("id") int recipeId);

    @GET("recipes/findByIngredients")
    Call<FindByIngredients[]> getResultsByIngredients(@Query("ingredients") String ingredients, @Query("number") int numberOfResults);
}
