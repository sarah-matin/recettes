import { Link } from "react-router-dom";
export default function RecipeCard({recipe}) {
    return (
        <Link to={"show-recipe"} state={recipe} className="recipeLink">
            <div className="recipeCard" id={"recipe " +  recipe.id }>
                <h1 className="recipeTitle">{ recipe.name }</h1>
                <img className="recipePicture" src="./images/recipePlaceholder.jpg" alt="Image de la recette" />
            </div>
        </Link>
    );
}