package com.stacyzolnikov.prepareandnourish.unsigned_users;

/**
 * Created by stacyzolnikov on 10/20/16.
 */
public class Category {
    private int id;
    String categoryName;
    int categoryPhoto;


    public Category(int id, String categoryName, int categoryPhoto) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryPhoto = categoryPhoto;
    }

    public Category(String categoryName, int categoryPhoto) {
        this.categoryName = categoryName;
        this.categoryPhoto = categoryPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryPhoto(int categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
    }
}
