package com.sarahmartin.recettes;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(nullable = false)
	private Map<Long, String> ingredientList;

	public Map<Long, String> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(Map<Long, String> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	@Column(nullable = false)
	private List<String> steps;

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
}
