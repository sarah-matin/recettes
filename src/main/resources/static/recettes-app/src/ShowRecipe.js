import * as React from 'react';
import { useLocation, Navigate, Link } from 'react-router-dom';
import { useRef, useReducer } from 'react';
import './style.css';
import axios from 'axios';
export default function ShowRecipe() {
    let recipe = useLocation().state;
    console.log(recipe);
    let ingredientList = recipe.ingredientList;
    let steps = recipe.steps;
    const [, forceUpdate] = useReducer(x => x + 1, 0)
    const redirect = useRef(false);

    const deleteRecipe = () => {
        axios.delete("api/recipe/" + recipe.id)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.error(error);
            });
            redirect.current = true;
            forceUpdate();
    }

    return (
        <div className="recipeDetails" >
        {redirect.current && <Navigate to="/" replace={true} />}
            <Link to="/" state={recipe}>
                <button id="returnButton">Retour</button>
            </Link>
            <h1>{recipe.name}</h1>
            <Link to="/create-recipe" state={recipe}>
                <button id="modifyButton">Modifier</button>
            </Link>
            <button id="deleteButton" onClick={deleteRecipe}>Supprimer</button>
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