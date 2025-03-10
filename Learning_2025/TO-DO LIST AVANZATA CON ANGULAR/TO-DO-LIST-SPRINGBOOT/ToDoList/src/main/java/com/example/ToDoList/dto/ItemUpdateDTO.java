package com.example.ToDoList.dto;


public class ItemUpdateDTO {

    /*
     * Nella classe DTO vado a mettere solamente i campi che possono essere
     * modificati, in questo caso solo il campo done
     */

    private Integer id;
    private Boolean done;

    public ItemUpdateDTO() {
    }

    public ItemUpdateDTO(Integer id, Boolean done) {
        this.id = id;
        this.done = done;
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

}
