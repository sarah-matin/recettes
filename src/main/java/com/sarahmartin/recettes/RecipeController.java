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
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public Iterable<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	
	@GetMapping("/name/{recipeName}")
	public List<Recipe> findByName(@PathVariable String recipeName) {
		return recipeRepository.findByName(recipeName);
	}
	
//	@GetMapping("/ingredientList/{ingredientId}")
//	public List<Recipe> findByIngredient(@PathVariable Long ingredientId) {
//		List<Recipe> recipesWithIngredient = ((List<Recipe>)recipeRepository.findAll());
//		List<Recipe> recipesToReturn = new LinkedList<Recipe>();
//		for (Recipe recipe : recipesWithIngredient) {
//			if (recipe.getIngredientList().containsKey(ingredientId)) {
//				recipesToReturn.add(recipe);
//			}
//		}
//		return recipesToReturn;
//	}
	
	@GetMapping("/{id}")
	public Recipe findOne(@PathVariable Long id) {
		return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe create(@RequestBody Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
		recipeRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) {
		if (recipe.getId() != id) {
			throw new RecipeIdMismatchException();
		}
		recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
		return recipeRepository.save(recipe);
	}
}
