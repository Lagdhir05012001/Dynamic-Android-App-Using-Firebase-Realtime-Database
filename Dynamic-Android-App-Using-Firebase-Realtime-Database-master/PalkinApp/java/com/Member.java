package com.example.retrive_image_tablayout;
public class Member {
    private String name;
    private String imageUrl;
    private String price;
    private String key;
    private String description;
    public Member() {
        //empty constructor needed
    }
    public Member(String name, String imageUrl,String price,String description) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
    }
    public String getName()
    {

        return name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public void setName(String name)
    {

        this.name = name;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl)

    {
        this.imageUrl = imageUrl;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

