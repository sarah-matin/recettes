import React from 'react';
import RecipeCard from './RecipeCard';

export default function RecipeList() {
    const [recipes, setRecipes] = React.useState([])
    const url = 'http://localhost:8081/api/recipe';

    const fetchInfo = () => { 
        return fetch(url) 
                .then((res) => res.json()) 
                .then((d) => setRecipes(d)) 
        }
        
    React.useEffect(() => {
        fetchInfo();
    }, [])

    return (
        <div id="recipeListElements">
        {recipes.map(function(recipe) {
          return (
            <RecipeCard recipe={recipe} />
          )
        })}
        </div>
    
      )
};