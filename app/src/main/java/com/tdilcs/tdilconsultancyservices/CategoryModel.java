package com.tdilcs.tdilconsultancyservices;

public class CategoryModel {
    private String categoryIcon, categoryName;
    public CategoryModel(String categoryIcon, String categoryName){
        this.categoryIcon=categoryIcon;
        this.categoryName=categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
