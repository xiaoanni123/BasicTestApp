package com.example.lanxumit.testapplication.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TestRealm_book extends RealmObject {
    @PrimaryKey
    private int id;
    private String author;
    private String name;
    private int page;
    private int price;
    private RealmList<Dog> dogs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }
}
