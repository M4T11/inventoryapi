package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categories")
@JsonPropertyOrder({ "category_id", "name"})
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("category_id")
    private Long categoryId;
    @Column(name="name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<EanDevice> eanDevices = new HashSet<EanDevice>();

    public Category() {

    }
//    public Category(String name) {
//        this.name = name;
//    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
