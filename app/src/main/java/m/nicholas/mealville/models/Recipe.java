package m.nicholas.mealville.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private static List<Recipe> allRecipes = new ArrayList<>();
    private String mealTitle;
    private String description;
    private String[] ingredients;
    private String[] steps;
    private int id;

    public Recipe(String mealTitle, String description, String ingredients, String steps) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.ingredients = ingredients.split(",");
        this.steps = steps.split("\\.");
        allRecipes.add(this);
        this.id = allRecipes.size();
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*----------- STATIC METHODS-----------*/
    public static List<Recipe> getAllRecipes() {
        return allRecipes;
    }
}
