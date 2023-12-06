'use strict';

const e = React.createElement;


class RecipeCard extends React.Component {
    constructor(props) {
      super(props);
    }

    render() {
        return e('div', { class : "recipeCard" }, 
                e('h1', { class : "recipeTitle" }, "Titre recettes"), 
                e('img', { class : "recipePicture", src : "images/tartiflette.jpg", alt : "Image de la recette" })
                );
        /*return (<div class="recipeCard">
                <h1 class="recipeTitle">Titre recettes</h1>
                <img class="recipePicture" src="images/tartiflette.jpg" alt="Image de la recette" />
            </div>);*/
    }

}

const domContainerLike = document.querySelector('#recipeCard');
const root = ReactDOM.createRoot(domContainerLike);
root.render(e(RecipeCard));