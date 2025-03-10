package com.example.ToDoList.controller;

import com.example.ToDoList.service.ItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDoList.View.itemTranslatedView;
import com.example.ToDoList.dto.ItemFilterDto;
import com.example.ToDoList.dto.ItemOutputDto;
import com.example.ToDoList.dto.ItemUpdateDTO;
import com.example.ToDoList.model.Item;

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/itemController")
public class ItemController {
	
	@Autowired
	private ItemService itemService; 
	
	@PostMapping
	public List<Item> getItems(){
		return itemService.getItems();
	}	

	@PostMapping("/translatedItems")
	public List<itemTranslatedView> getItemsTranslated(){
		return itemService.getItemsTranslated();
	}
	
	
	@PostMapping("/all")
	public List<ItemOutputDto> all (@RequestBody ItemFilterDto itemFilterDto){
		return itemService.getAll(itemFilterDto); 
	}
	
	@PostMapping("{id}")
	public Item getItemsById(@PathVariable Integer id){
		return itemService.getItemById(id);
	}
	
	@PostMapping("/addNewItem")
	public void addNewItem(@RequestBody Item item) {
		itemService.insertItem(item); 
	}
	

	@PutMapping("/updateDone")
	public void updateDoneItem(@RequestBody ItemUpdateDTO itemUpdateDTO) {
		itemService.updateDoneItem(itemUpdateDTO);
	}
	
	@DeleteMapping("/deleteItem")
	public void deleteItem(@RequestBody Integer id) {
		itemService.deleteItem(id);
	}


}
