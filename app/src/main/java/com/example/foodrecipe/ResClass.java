package com.example.foodrecipe;

public class ResClass {
    int id;
    String name,ingredients,description,imgurl,link,category;

    public ResClass(int id, String name, String ingredients, String description, String imgurl, String link,String category) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.imgurl = imgurl;
        this.link = link;
        this.category=category;
    }
    public ResClass(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String imgurl) {
        this.category = category;
    }

}
