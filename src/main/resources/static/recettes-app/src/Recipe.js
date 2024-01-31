import { Component } from 'react';

class Recipe extends Component {
    constructor(props) {
        super(props);
        this.state = {recipe : []};
        this.remove = this.remove.bind(this);
    }

    async componentDidMount() {
        fetch('/recipe/')
            .then(response => response.json())
            .then(data => this.setState({clients: data}));
    }
    
}

export default Recipe;