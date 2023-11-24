package com.sarahmartin.recettes;

public class RecipeIdMismatchException extends RuntimeException {

	public RecipeIdMismatchException() {
		super();
	}
	
	public RecipeIdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RecipeIdMismatchException(String message) {
		super(message);
	}
	
	public RecipeIdMismatchException(Throwable cause) {
		super(cause);
	}
}
