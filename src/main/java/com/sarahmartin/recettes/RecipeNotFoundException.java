package com.sarahmartin.recettes;

public class RecipeNotFoundException extends RuntimeException {

	public RecipeNotFoundException() {
		super();
	}
	
	public RecipeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RecipeNotFoundException(String message) {
		super(message);
	}
	
	public RecipeNotFoundException(Throwable cause) {
		super(cause);
	}
}
