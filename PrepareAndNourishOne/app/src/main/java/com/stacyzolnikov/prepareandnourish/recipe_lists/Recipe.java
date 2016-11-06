package com.stacyzolnikov.prepareandnourish.recipe_lists;

/**
 * Created by stacyzolnikov on 10/21/16.
 */
public class Recipe {
    private int id;
    String recipeName;
    int recipePhoto;
    String recipeCategory;

    public Recipe(int id, String recipeName, int recipePhoto) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipePhoto = recipePhoto;
    }

    public Recipe(String recipeName, int recipePhoto, String recipeCategory) {
        this.recipeName = recipeName;
        this.recipePhoto = recipePhoto;
        this.recipeCategory = recipeCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipePhoto() {
        return recipePhoto;
    }

    public void setRecipePhoto(int recipePhoto) {
        this.recipePhoto = recipePhoto;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }
}
