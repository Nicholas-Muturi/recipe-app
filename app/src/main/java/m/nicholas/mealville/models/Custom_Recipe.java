package m.nicholas.mealville.models;

import java.util.ArrayList;
import java.util.List;

public class Custom_Recipe {
    private static List<Recipe> allCustomRecipes = new ArrayList<>();

    /*----------- STATIC METHODS-----------*/
    public static List<Recipe> getAllCustomRecipes() {
        return allCustomRecipes;
    }

    public static void addCustomRecipe(Recipe customRecipe){allCustomRecipes.add(customRecipe);}
}
