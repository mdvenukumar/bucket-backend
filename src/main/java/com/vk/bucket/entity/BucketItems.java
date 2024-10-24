package com.vk.bucket.entity;


import jakarta.persistence.*;

@Entity
@Table
public class BucketItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String item;

    public BucketItems() {}
    public BucketItems(int id, String item) {
        this.id = id;
        this.item = item;
    }
    public int getId() {
        return id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public void setId(int id) {
        this.id = id;
    }

}
