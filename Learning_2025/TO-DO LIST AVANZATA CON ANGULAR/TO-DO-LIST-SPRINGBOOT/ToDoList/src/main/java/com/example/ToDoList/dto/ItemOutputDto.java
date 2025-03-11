package com.example.ToDoList.dto;

import java.util.Objects;

public class ItemOutputDto {
	private Integer id;
    private Boolean done;
    private String title;
    private String description;
    private String category;
    
    
    
	public ItemOutputDto() {
		super();
	}

	public ItemOutputDto(Integer id, Boolean done, String title, String description, String category) {
		super();
		this.id = id;
		this.done = done;
		this.title = title;
		this.description = description;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
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
		ItemOutputDto other = (ItemOutputDto) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Objects.equals(done, other.done) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	}
  
	
    

}
