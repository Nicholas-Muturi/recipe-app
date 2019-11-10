package m.nicholas.mealville.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private static List<Recipe> allRecipes = new ArrayList<>();
    private String mealTitle;
    private String description;
    private List<String> ingredients;
    private List<String> steps;
    private int id;

    public Recipe(String mealTitle, String description, List<String> ingredients, List<String> steps) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        allRecipes.add(this);
        this.id = allRecipes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(mealTitle, recipe.mealTitle) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(steps, recipe.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealTitle, description, ingredients, steps);
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
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
