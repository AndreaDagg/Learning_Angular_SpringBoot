package com.example.ToDoList.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.example.ToDoList.View.itemTranslatedView;

public interface ItemTranslatedViewRepository
        extends JpaRepository<itemTranslatedView, Integer>, JpaSpecificationExecutor<itemTranslatedView> {

}
