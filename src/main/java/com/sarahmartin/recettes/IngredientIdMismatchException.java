package com.sarahmartin.recettes;

public class IngredientIdMismatchException extends RuntimeException {

	public IngredientIdMismatchException() {
		super();
	}
	
	public IngredientIdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IngredientIdMismatchException(String message) {
		super(message);
	}
	
	public IngredientIdMismatchException(Throwable cause) {
		super(cause);
	}
}
