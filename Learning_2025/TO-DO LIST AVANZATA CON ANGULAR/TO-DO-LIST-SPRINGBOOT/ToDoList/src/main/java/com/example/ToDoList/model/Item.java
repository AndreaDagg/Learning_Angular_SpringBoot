package com.example.ToDoList.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name= "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description; 

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DONE")
    private Boolean done;     

    public Item() {}

    public Item(String title, String description, String category, Boolean done) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.done = done; 
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, done);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(title, other.title) &&
               Objects.equals(description, other.description) &&
               Objects.equals(category, other.category) &&
               Objects.equals(done, other.done);
    }

    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", category='" + category + '\'' +
               ", done=" + done +
               '}';
    }
}
