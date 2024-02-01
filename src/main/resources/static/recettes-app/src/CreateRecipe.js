import * as React from 'react';
import { useRef, useState, useReducer } from 'react';
import { useLocation, Navigate, Link } from 'react-router-dom';
import axios from 'axios';
export default function CreateRecipe() {
    const recipe = useRef(useLocation().state);
    const [ingredientInputs, setIngredientInputs] = useState(recipe.current === null ? [""] : recipe.current.ingredientList);
    const [stepInputs, setStepInputs] = useState(recipe.current === null ? [""] : recipe.current.steps);
    const [, forceUpdate] = useReducer(x => x + 1, 0)
    const redirect = useRef(false);

    if (recipe.current === null) {
        recipe.current = {id: null, name : '', ingredientList : {}, steps : {}}
        console.log(recipe.current)
    }

    function updateRecipeName(name) {
        recipe.current.name = name;
    }

    const handleIngredientInputChange = (e, index) => {
        const { value } = e.target;
        const list = [...ingredientInputs];
        list[index] = value;
        setIngredientInputs(list);
    };

    const handleStepInputChange = (e, index) => {
        const { value } = e.target;
        const list = [...stepInputs];
        list[index] = value;
        setStepInputs(list);
    };

    const handleRemoveIngredient = index => {
        const list = [...ingredientInputs];
        list.splice(index, 1);
        setIngredientInputs(list);
      };

    const handleRemoveStep = index => {
        const list = [...stepInputs];
        list.splice(index, 1);
        setStepInputs(list);
    };

    const addIngredient = () => {
        setIngredientInputs([...ingredientInputs, ""])
    }

    const addStep = () => {
        setStepInputs([...stepInputs, ""])
    }

    const createRecipe = () => {
        recipe.current.ingredientList = ingredientInputs;
        recipe.current.steps = stepInputs;
        if(recipe.current.id === null) {
            axios.post("/api/recipe", recipe.current).then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });
        } else {
            let API_URL = "/api/recipe/" + recipe.current.id;
            axios({method: "put", url: API_URL, data: recipe.current}).then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });
            // axios.put().put(API_URL, recipe.current).then(function (response) {
            //     console.log(response);
            //   })
            //   .catch(function (error) {
            //     console.log(error);
            //   });
        }
        redirect.current = true;
        forceUpdate();
    }

    return (
        <div className="recipeCreation" >
        {redirect.current && <Navigate to="/" replace={true} />}
            <div>
                <Link to="/" state={recipe}>
                    <button id="returnButton">Retour</button>
                </Link>
                <h1>Titre de la recette</h1>
                <input type="text" id="recipeTitle" defaultValue={recipe.current.name} onChange={e => updateRecipeName(e.target.value)} />
            </div>
            <div className="ingredients">
                <h2>Liste des Ingrédients</h2>
                <ul id="ingredientList">
                    {ingredientInputs.map((ingredient, i) => { return(
                        <div>
                            <input type="text" name={"ingredient" + i} id={"ingredient" + i} key={"ingredient" + i} value={ingredient} onChange={e => handleIngredientInputChange(e, i)} />
                            {ingredientInputs.length !== 1 && <button className="mr10" key={"buttonIngredient" + i} onClick={() => handleRemoveIngredient(i)}>Remove</button>}
                        </div>
                    )})}
                </ul>
                <button onClick={addIngredient}>Ajouter ingrédient</button>
            </div>
            <div className="steps">
                <h2>Étapes</h2>
                <ul id="stepList">
                    {stepInputs.map((step, i) => { return (
                        <div>
                            <input type="text" name={"step" + i} id={"step" + i} key={"step" + i} value={step} onChange={e => handleStepInputChange(e, i)} />
                            {stepInputs.length !== 1 && <button className="mr10" key={"buttonStep" + i} onClick={() => handleRemoveStep(i)}>Remove</button>}
                        </div>
                    )})}
                </ul>
                <button onClick={addStep}>Ajouter étape</button>
            </div>
            <button onClick={createRecipe}>Créer recette</button>
        </div>
    );
}