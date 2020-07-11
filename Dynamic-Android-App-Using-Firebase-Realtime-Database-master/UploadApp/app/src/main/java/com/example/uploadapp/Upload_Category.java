package com.example.uploadapp;

public class Upload_Category {
    private String category;
    private String key;

    public Upload_Category(){
        //Neeeded
    }
    public Upload_Category(String category){
        this.category = category;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
