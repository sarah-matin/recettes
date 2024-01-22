import React, { Component } from 'react';
import './App.css';
import RecipeList from './RecipeList';
import ShowRecipe from './ShowRecipe.js';
import { Route, Routes } from "react-router-dom";

class App extends Component {

  render() {
    return (
      <div className="App">
    
        <Routes>
          <Route path="/" element={<RecipeList />} />
          {/*<Route path="/edit-user/:id" element={<EditRecipe />} /> */}
          {/*<Route path="/user/:id" element={<Recipe />} /> */}
          {/*<Route path="/create-user" element={<CreateRecipe />} /> */}
          <Route path="/show-recipe" element={<ShowRecipe />} />
        </Routes>
      </div>
    );
  }
}
export default App;