import * as React from 'react';
import { useLocation } from 'react-router-dom';
import './style.css';
export default function ShowRecipe() {
    let recipe = useLocation().state;
    console.log(recipe);
    let ingredientList = recipe.ingredientList;
    let steps = recipe.steps;

    return (
        <div className="recipeDetails" >
            <h1>{recipe.name}</h1>
            <div className="ingredientList">
                <h2>Liste des Ingrédients</h2>
                <ul>
                {ingredientList.map(function(ingredient, i) {
                        return (
                            <li key={i}>{ingredient}</li>
                        )
                    })}
                </ul>
            </div>
            <div className="steps">
                <h2>Étapes</h2>
                <ul>
                    {steps.map(function(step, i) {
                        return (
                            <li key={i}>{step}</li>
                        )
                    })}
                </ul>    
            </div>
        </div>
    );
}