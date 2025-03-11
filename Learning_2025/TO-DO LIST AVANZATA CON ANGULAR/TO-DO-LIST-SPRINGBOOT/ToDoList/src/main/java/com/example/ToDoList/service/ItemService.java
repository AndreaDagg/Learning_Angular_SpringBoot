package com.example.ToDoList.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.ToDoList.View.itemTranslatedView;
import com.example.ToDoList.dto.ItemFilterDto;
import com.example.ToDoList.dto.ItemOutputDto;
import com.example.ToDoList.dto.ItemUpdateDTO;
import com.example.ToDoList.model.Item;
import com.example.ToDoList.model.itemTranslate;
import com.example.ToDoList.repository.ItemRepository;
import com.example.ToDoList.repository.ItemTranslateRepository;
import com.example.ToDoList.repository.ItemTranslatedViewRepository;

import specification.ItemSpecification;
import specification.ItemViewSpecification;

@Service
public class ItemService {
	private final ItemRepository itemRepository;
	private final ItemTranslatedViewRepository itemTranslatedViewRepository;
	private final ItemTranslateRepository itemTranslateRepository; 
	//private final ItemViewSpecification itemViewSpecification; 
	
	public ItemService(ItemRepository itemRepository, ItemTranslatedViewRepository itemTranslatedViewRepository, ItemTranslateRepository itemTranslateRepository) {
		this.itemRepository = itemRepository; 
		this.itemTranslatedViewRepository = itemTranslatedViewRepository;
		this.itemTranslateRepository = itemTranslateRepository; 
		//this.itemViewSpecification = itemViewSpecification; 
	}
	
	public List<ItemOutputDto> getAll(ItemFilterDto itemFilterDto){		
		
		if (itemFilterDto.getIsEnglish()) {	
			ItemViewSpecification itemViewSpecification = new ItemViewSpecification(itemFilterDto); 			
			//return getOutFromPagedResultsItemView(itemTranslatedViewRepository.findAll(itemViewSpecification));	
			return getOutFromPageResultsGeneric(itemTranslatedViewRepository.findAll(itemViewSpecification)); 
		}
		
		ItemSpecification itemSpecification = new ItemSpecification(itemFilterDto); 
		//return getOutFromPagedResultsItem(itemRepository.findAll(itemSpecification));
		return getOutFromPageResultsGeneric(itemRepository.findAll(itemSpecification)); 
				 
	}
	
	private List<ItemOutputDto> getOutFromPagedResultsItemView(List<itemTranslatedView> allList) {
		List<ItemOutputDto> allPouts = new ArrayList<ItemOutputDto>();
		for (itemTranslatedView p : allList) {
			
			ItemOutputDto out = new ItemOutputDto();
			BeanUtils.copyProperties(p, out);
			allPouts.add(out);
		}
		return allPouts;
	}
	
	private List<ItemOutputDto> getOutFromPagedResultsItem(List<Item> allList) {
		List<ItemOutputDto> allPouts = new ArrayList<ItemOutputDto>();
		//System.out.print(allList);
		for (Item p : allList) {
			ItemOutputDto out = new ItemOutputDto();
			BeanUtils.copyProperties(p, out);
			allPouts.add(out);
		}
		return allPouts;
	}
	
	private <T> List<ItemOutputDto> getOutFromPageResultsGeneric(List<T> allList) {
	    List<ItemOutputDto> allPouts = new ArrayList<ItemOutputDto>();
	    for (T p : allList) {
	        ItemOutputDto out = new ItemOutputDto();
	       
	        BeanUtils.copyProperties(p, out);
	        
	        // Forza la copia dell'ID (assumendo che 'id' sia un campo comune nelle entità)
	        try {
	            Field idField = p.getClass().getDeclaredField("id");  // Ottieni il campo id dall'oggetto generico
	            idField.setAccessible(true); // Rendi il campo accessibile
	            Object idValue = idField.get(p); // Ottieni il valore dell'id

	            // Imposta il valore dell'id nell'oggetto di destinazione (ItemOutputDto)
	            out.setId((Integer) idValue); // Assumendo che l'ID sia di tipo Integer
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            e.printStackTrace();
	        }

	        allPouts.add(out);
	    }
	    return allPouts;
	}


	public List<Item> getItems(){
		return itemRepository.findAll();
	}

	public List<itemTranslatedView> getItemsTranslated() {
		return itemTranslatedViewRepository.findAll();
	}

	public void insertItem(Item item) {
		itemRepository.save(item);
	}

	public Item getItemById(Integer id) {
		return itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
	}
	
	public void updateDoneItem(ItemUpdateDTO itemUpdateDTO) {
		Integer id = itemUpdateDTO.getId();
		Boolean done = itemUpdateDTO.getDone();
		Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
		item.setDone(done);
		itemRepository.save(item);
	}
	
	public void deleteItem(Integer id) {
		itemTranslate itemTraslater = itemTranslateRepository.findByIdIta(id);
		if(itemTraslater != null) {
			itemTranslateRepository.delete(itemTraslater);
		}
		Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
		itemRepository.delete(item);
	}
}