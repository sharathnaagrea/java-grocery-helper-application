package com.grocery.helper.model;

import javax.persistence.*;

@Entity
@Table(name = "GROCERIES")
public class Grocery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item")
    private String itemName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "category")
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Grocery(long id, String itemName, Integer price, String category) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public Grocery() {
    }

}
