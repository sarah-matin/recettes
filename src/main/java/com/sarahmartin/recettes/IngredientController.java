package com.sarahmartin.recettes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@GetMapping
	public Iterable<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}
	
	@GetMapping("/name/{ingredientName}")
	public List<Ingredient> findByName(@PathVariable String ingredientName) {
		return ingredientRepository.findByName(ingredientName);
	}
	
	@GetMapping("/vegetarian/{isVegetarian}")
	public List<Ingredient> findAllVegetarian(@PathVariable boolean isVegetarian) {
		return ingredientRepository.findByVegetarian(isVegetarian);
	}
	
	@GetMapping("/vegan/{isVegan}")
	public List<Ingredient> findAllVegan(@PathVariable boolean isVegan) {
		return ingredientRepository.findByVegan(isVegan);
	}
	
	@GetMapping("/{id}")
	public Ingredient findOne(@PathVariable Long id) {
		return ingredientRepository.findById(id).orElseThrow(IngredientNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient create(@RequestBody Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		ingredientRepository.findById(id).orElseThrow(IngredientNotFoundException::new);
		ingredientRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Ingredient updateIngredient(@RequestBody Ingredient ingredient, @PathVariable Long id) {
		if (ingredient.getId() != id) {
			throw new IngredientIdMismatchException();
		}
		ingredientRepository.findById(id).orElseThrow(IngredientNotFoundException::new);
		return ingredientRepository.save(ingredient);
	}
}
