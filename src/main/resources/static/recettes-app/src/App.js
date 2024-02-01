import React, { Component } from 'react';
import './App.css';
import RecipeList from './RecipeList';
import ShowRecipe from './ShowRecipe.js';
import CreateRecipe from './CreateRecipe.js';
import { Route, Routes } from "react-router-dom";

class App extends Component {

  render() {
    return (
      <div className="App">
    
        <Routes>
          <Route path="/" element={<RecipeList />} />
          <Route path="/create-recipe" element={<CreateRecipe />} />
          <Route path="/show-recipe" element={<ShowRecipe />} />
        </Routes>
      </div>
    );
  }
}
export default App;