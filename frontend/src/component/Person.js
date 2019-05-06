import React from 'react';

class PersonList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            error: 0
        };
    }

    componentDidMount() {
        const json = {
            method: 'GET',
            credentials: 'include',
        };
        console.log(json);
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/person', json)
            .then(result => result.json())
            .then(items => this.setState({items:items}))
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        return (
                <div>
                <ul class="list-group">
                {this.state.items.length ?
                 this.state.items.map(item =>
                                      <li class="list-group-item" key={item.id}>
                                      Name: {item.name} User: {item.username}
                                             </li>) :
                        <li class="list-group-item">Loading...</li>
                }
            </ul>
            </div>
        );
    }
}

class PersonForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            name: '',
            role: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
    }


    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state);
        const json = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                // "Content-Type": "application/x-www-form-urlencoded",
            },
            body: JSON.stringify(
                this.state
            )
        };
        const response = fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/person/register', json
                              ).then(res => res.json()).then(res => {
                                  alert(res.message);
                                  this.setState({
                                      username: "",
                                      password: "",
                                      name: "",
                                      role: ""
                                  });
                              })
              .catch(error => console.error('Error:', error));
    }

    handleChange(event) {
        const target = event.target;

        const value = target.value;
        const name = target.name;

        this.setState(
            {[name]: value}
        );
    }


    render() {
        return (
                <form onSubmit={this.handleSubmit}>
                <div class="form-group">
                <label>User</label>
                <input type="text" class="form-control" id="exampleInputUser" name="username" placeholder="Enter Username" value={this.state.username} onChange={this.handleChange}/>
                </div>
                <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" id="exampleInputPassword" name="password" placeholder="Enter Password" value={this.state.password} onChange={this.handleChange}/>
                </div>
                <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" id="exampleInputName" name="name" placeholder="Enter Name" value={this.state.name} onChange={this.handleChange}/>
                </div>

                <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" id="exampleInputRole" name="role" placeholder="Enter Role" value={this.state.role} onChange={this.handleChange}/>
                </div>

                <button type="submit" class="btn btn-primary">Register</button>
                </form>
        );
    }
}

class PersonLogin extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            code: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {

    }

    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state);
        const json = {
            credentials: 'include',
        };
        console.log(json);
        const response = fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/auth/login?name=' + this.state.username + '&password=' + this.state.password + '&rememberme=false&code=' + this.state.code, json
                              ).then(res => res.json()).then(response => console.log(response))
              .catch(error => console.error('Error:', error));
    }

    handleChange(event) {
        const target = event.target;

        const value = target.value;
        const name = target.name;

        this.setState(
            {[name]: value}
        );
    }

    render() {
        return (<form onSubmit={this.handleSubmit}>
                <div class="form-group">
                <label>Username</label>
                <input type="text" class="form-control" id="exampleInputUser" name="username" placeholder="Enter Username" value={this.state.username} onChange={this.handleChange}/>
                </div>
                <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" id="exampleInputPassword" name="password" placeholder="Enter Password" value={this.state.password} onChange={this.handleChange}/>
                </div>
                <div class="form-group">
                <label>2FA Code</label>
                <input type="text" class="form-control" id="input2FA" name="code" placeholder="Enter 2FA code" value={this.state.code} onChange={this.handleChange}/>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                </form>);
    }
}

class Person2FA extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            item: "",
        };
    }

    componentDidMount() {
        const json = {
            method: 'POST',
            credentials: 'include',
        };
        console.log(json);
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/auth/2fa', json)
            .then(result => result.json())
            .then(item => this.setState({item:item.url}))
            .catch(error => {
                console.log(error);
            });

    }

    render() { return (this.state.item ? <img src={'https://chart.googleapis.com/chart?chs=200x200&chld=M%7C0&cht=qr&chl=' + this.state.item} /> : <p>No 2FA</p> );    }
}

export {
    PersonList,
    PersonForm,
    PersonLogin,
    Person2FA,
}
