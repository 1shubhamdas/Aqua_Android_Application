package com.example.fyp;

public class Data {
    private String image;
    private String title;
    private String description;
    private String id;
    private String price;
    private String feedback;
    private String rating;
    private String name;
    private String phone;

    public Data() {
    }

    public Data(String image, String title, String description, String id, String price, String feedback, String rating, String name, String phone) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.id = id;
        this.price = price;
        this.feedback = feedback;
        this.rating = rating;
        this.name = name;
        this.phone=phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void msetName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void msetFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRating() {
        return rating;
    }

    public void msetRating(String rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
