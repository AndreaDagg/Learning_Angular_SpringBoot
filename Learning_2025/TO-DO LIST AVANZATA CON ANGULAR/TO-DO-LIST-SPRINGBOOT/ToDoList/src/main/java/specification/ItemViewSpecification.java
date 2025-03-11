package specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.ToDoList.View.itemTranslatedView;
import com.example.ToDoList.dto.ItemFilterDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ItemViewSpecification implements Specification<itemTranslatedView> {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ItemFilterDto example; 
    
    public ItemViewSpecification(ItemFilterDto example) {
        this.example = example; 
    }

    @Override
    public Predicate toPredicate(Root<itemTranslatedView> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        
        List<Predicate> predicates = new ArrayList<>();

        // Filtro per ID
        if (example.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), example.getId()));
        }

        // Filtro per titolo
        if (example.getTitle() != null && !example.getTitle().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + example.getTitle().toLowerCase() + "%"));
        }

        // Filtro per categoria
        if (example.getCategory() != null && !example.getCategory().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("category"), example.getCategory()));
        }

        // Filtro per done
        if (example.getDone() != null) {
            predicates.add(criteriaBuilder.equal(root.get("done"), example.getDone()));
        }

        /*
        // Filtro per isEnglish
        if (example.getIsEnglish() != null) {
            predicates.add(criteriaBuilder.equal(root.get("isEnglish"), example.getIsEnglish()));
        } */

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
