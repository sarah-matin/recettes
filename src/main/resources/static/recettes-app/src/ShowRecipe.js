import * as React from 'react';
import { useRef, useReducer } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import './style.css';
export default function ShowRecipe() {
    let recipe = useLocation().state;
    let ingredientList = recipe.ingredientList;
    let steps = recipe.steps;
    const ingredientsDetail = useRef(Object.keys(ingredientList).map(function(ingredientid, i) {return <li key={i}>{ingredientList[ingredientid] + ' de '}</li>}));
    const [, forceUpdate] = useReducer(x => x + 1, 0)
    //const [ingredientsDetail, setIngredientsDetail] = useState('');
    async function getIngredient(ingredientId) {
        let ingredientAxos = await axios.get('/api/ingredient/' + ingredientId);
        forceUpdate();
        return await ingredientAxos.data;
    }

    React.useEffect(() =>{
        console.log(ingredientsDetail.current)
        Object.keys(ingredientList).map((ingredientid, i) => getIngredient(ingredientid).then(ingredient => ingredientsDetail.current[i] = <li key={i}>
            {ingredientList[ingredient.id] + ' de ' + ingredient.name}</li>));
            console.log(ingredientsDetail.current);
        forceUpdate();
    }, [ingredientList])

    return (
        <div className="recipeDetails" >
            <h1>{recipe.name}</h1>
            <div className="ingredientList">
                <h2>Liste des Ingrédients</h2>
                <ul>
                    {ingredientsDetail.current}
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