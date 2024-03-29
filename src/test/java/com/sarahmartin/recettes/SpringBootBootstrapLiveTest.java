package com.sarahmartin.recettes;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class SpringBootBootstrapLiveTest {
	
	private static final String API_ROOT_RECIPE = "http://localhost:8081/api/recipe";
	
	
	private Recipe createRandomRecipe() {
		Recipe recipe = new Recipe();
		recipe.setName(RandomStringUtils.randomAlphabetic(10));
		List<String> steps = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			steps.add(RandomStringUtils.randomAlphabetic(20));
		}
		recipe.setSteps(steps);
		List<String> ingredients = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			ingredients.add(RandomStringUtils.randomAlphabetic(20));
		}
		recipe.setIngredientList(ingredients);
		return recipe;
	}
	
	private String createRecipeAsUri(Recipe recipe) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(recipe).post(API_ROOT_RECIPE);
		return API_ROOT_RECIPE + "/" + response.jsonPath().get("id");
	}
	
	@Test
	public void whenGetAllRecipes_thenOK() {
		Response response = RestAssured.get(API_ROOT_RECIPE);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	
	@Test
	public void whenGetCreatedRecipeById_thenOK() {
		Recipe recipe = createRandomRecipe();
		String location = createRecipeAsUri(recipe);
		Response response = RestAssured.get(location);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(recipe.getName(), response.jsonPath().get("name"));
	}
	
	@Test
	public void whenCreateNewRecipe_thenCreated() {
		Recipe recipe = createRandomRecipe();
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(recipe).post(API_ROOT_RECIPE);
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}
	
	@Test
	public void whenGetNotExistRecipeById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT_RECIPE + "/" + RandomStringUtils.randomNumeric(4));
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
	
	@Test
	public void whenInvalidRecipe_thenError() {
		Recipe recipe = createRandomRecipe();
		recipe.setName(null);
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(recipe).post(API_ROOT_RECIPE);
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}
	
	@Test
	public void whenUpdateCreatedRecipe_thenUpdated() {
		Recipe recipe = createRandomRecipe();
		String location = createRecipeAsUri(recipe);
		recipe.setId(Long.parseLong(location.split("api/recipe/")[1]));
		recipe.setName("newName");
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(recipe).put(location);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		
		response = RestAssured.get(location);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals("newName", response.jsonPath().get("name"));
	}


@Test
public void whenDeleteCreatedRecipe_thenOk() {
	Recipe recipe = createRandomRecipe();
	String location = createRecipeAsUri(recipe);
	Response response = RestAssured.delete(location);
	
	assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	
	response = RestAssured.get(location);
	assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
}

	
//	@Test
//	public void whenFindIngredient_thenOK() {
//		Ingredient ingredient = createRandomIngredient();
//		String ingredientLocation = createIngredientAsUri(ingredient);
//		String[] locationSplit = ingredientLocation.split("/");
//		long ingredientId = Long.parseLong(locationSplit[locationSplit.length - 1]);
//		Recipe recipeWith = createRandomRecipe();
//		recipeWith.getIngredientList().put(ingredientId, "200g");
//		String recipeLocation = createRecipeAsUri(recipeWith);
//		String[] RecipeLocationSplit = recipeLocation.split("/");
//		long recipeId = Long.parseLong(RecipeLocationSplit[RecipeLocationSplit.length - 1]);
//		Recipe recipeWithout = createRandomRecipe();
//		createRecipeAsUri(recipeWithout);
//		Response response = RestAssured.get(API_ROOT_RECIPE + "/ingredientList/" + ingredientId);
//		
//		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//		List<Recipe> recipesInResponse = response.jsonPath().getList("", Recipe.class);
//		assertEquals(1, recipesInResponse.size());
//		assertEquals(recipeId, recipesInResponse.get(0).getId());
//		
//	}

}
