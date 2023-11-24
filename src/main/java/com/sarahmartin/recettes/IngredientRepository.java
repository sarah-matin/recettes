package com.sarahmartin.recettes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	List<Ingredient> findByVegan(boolean vegan);
	List<Ingredient> findByVegetarian(boolean vegetarian);
	List<Ingredient> findByName(String name);
}
