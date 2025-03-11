package com.example.ToDoList.View;

import java.util.Objects;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VIEW_item_translated")
@Immutable
public class itemTranslatedView {

    /*
    Query per la creazione della view nel db    
    CREATE VIEW VIEW_item_translated AS SELECT  I.ID AS id, T.TITLE_EN AS TITLE_EN, T.DESCRIPTION_EN AS DESCRIPTION_EN , I.CATEGORY AS CATEGORY, I.DONE AS DONE FROM ITEMS I JOIN TODO_ITEMS_TRANSLATED T ON I.ID = T.ID_ITA
    */

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "TITLE", nullable = false)
    private String title; 
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "CATEGORY", nullable = false)
    private String category;
    @Column(name = "DONE", nullable = false)
    private boolean done;





    
    public itemTranslatedView() {
    }

    public itemTranslatedView(Integer id, String title, String description, String category, boolean done) {
        this.id = id;
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

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, done, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		itemTranslatedView other = (itemTranslatedView) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& done == other.done && Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}
    
    
    
    }
