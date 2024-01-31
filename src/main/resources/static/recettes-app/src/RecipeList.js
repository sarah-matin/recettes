import React, { Component } from 'react';
import RecipeCard from './RecipeCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus } from '@fortawesome/free-solid-svg-icons'
import { Link } from "react-router-dom";
class RecipeList extends Component {
  state = {
    recipes: []
  };
    //const url = 'http://localhost:8081/api/recipe';

    async componentDidMount() {

      fetch('/api/recipe')
              .then(response => response.json())
              .then(data => this.setState({recipes: data}));
    }

    render() {
      const {recipes} = this.state;
        return (<div id="recipeListElements">
          <p id="pageTitle">Liste des recettes</p>
          {recipes.map(function(recipe) {
            return (
              <RecipeCard recipe={recipe} key={recipe.id} />
            )
          })}
          <div id='createRecipeButton'>
            <Link to="/create-recipe">
              <button><FontAwesomeIcon icon={faPlus} color="green" /> Nouvelle recette</button>
            </Link>
          </div>
        </div>
      );
    
  }
}

export default RecipeList;