package com.sarahmartin.recettes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findByName(String name);
}
