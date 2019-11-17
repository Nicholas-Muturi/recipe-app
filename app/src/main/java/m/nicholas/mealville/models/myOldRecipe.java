package m.nicholas.mealville.models;

import java.util.ArrayList;
import java.util.List;

public class myOldRecipe {
    private static List<myOldRecipe> allMyOldRecipes = new ArrayList<>();
    private String mealTitle;
    private String description;
    private String[] ingredients;
    private String[] steps;
    private int id;

    public myOldRecipe(String mealTitle, String description, String ingredients, String steps) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.ingredients = ingredients.split(",");
        this.steps = steps.split("\\.");
        allMyOldRecipes.add(this);
        this.id = allMyOldRecipes.size();
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
    public static List<myOldRecipe> getAllMyOldRecipes() {
        return allMyOldRecipes;
    }
}
