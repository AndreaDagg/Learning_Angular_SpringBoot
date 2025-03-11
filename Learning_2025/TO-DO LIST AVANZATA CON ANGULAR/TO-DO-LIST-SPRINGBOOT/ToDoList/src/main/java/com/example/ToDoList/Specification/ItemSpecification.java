package com.example.ToDoList.Specification;

import com.example.ToDoList.model.Item;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import com.example.ToDoList.dto.ItemDTO;

public class ItemSpecification {

    public static Specification<Item> filterBy(ItemDTO itemDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtra per ID se presente
            if (itemDTO.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), itemDTO.getId()));
            }

            // Filtra per titolo se presente (case insensitive LIKE)
            if (itemDTO.getTitle() != null && !itemDTO.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")), "%" + itemDTO.getTitle().toLowerCase() + "%"
                ));
            }

            // Filtra per categoria se presente
            if (itemDTO.getCategory() != null && !itemDTO.getCategory().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("category"), itemDTO.getCategory()));
            }

            // Filtra per stato "done" se presente
            if (itemDTO.getDone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("done"), itemDTO.getDone()));
            }

            // Filtra per description se presente (case insensitive LIKE)
            if (itemDTO.getDescription() != null && !itemDTO.getDescription().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")), "%" + itemDTO.getDescription().toLowerCase() + "%"
                ));
            }

            // Filtra per isEnglish se presente (Booleano)
            if (itemDTO.getIsEnglish() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isEnglish"), itemDTO.getIsEnglish()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
