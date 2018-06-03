package com.example.strahinja.contacts;

/**
 * Created by Strahinja on 4/11/2018.
 */

public class Contact {

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private int image;
    private int favorite;

    public Contact(String id, String firstName, String lastName, String phone, int favorite) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.favorite = favorite;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getImage() {
        return image;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
