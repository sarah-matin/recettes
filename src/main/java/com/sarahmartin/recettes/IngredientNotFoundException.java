package com.sarahmartin.recettes;

public class IngredientNotFoundException extends RuntimeException {

	public IngredientNotFoundException() {
		super();
	}
	
	public IngredientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IngredientNotFoundException(String message) {
		super(message);
	}
	
	public IngredientNotFoundException(Throwable cause) {
		super(cause);
	}
}
