export default function RecipeCard({recipe}) {

    return (
        <a href={"./recipes/" + recipe.id } className="recipeLink">
            <div className="recipeCard" id={"recipe " +  recipe.id }>
                <h1 className="recipeTitle">{ recipe.name }</h1>
                <img className="recipePicture" src="images/recipePlaceholder.jpg" alt="Image de la recette" />
            </div>
        </a>
    );
}