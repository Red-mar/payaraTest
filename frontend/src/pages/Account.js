import React from 'react';

import { Person2FA } from '../component/Person.js';

class Account extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedin: false,
            user: "no user",
        }
    }

    componentDidMount() {
        const json = {
            method: 'GET',
            credentials: 'include',
        };
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/auth/login', json)
            .then(res => res.json()).then(item => {
                if ( item !== "") {
                    this.setState({
                        loggedin: true, user:item.user
                    });
                }
            })
            .catch(error => {console.log(error);});
    }

    render() {
        return (
                <div>
                <div class="jumbotron">
                <h1 class="display-4">Account page</h1>
                </div>
                {this.state.loggedin ?
                 (<div>
                  <p>Logged in as {this.state.user}!</p>
                  <h3>Scan this code for 2FA!</h3>
                  <Person2FA />
                  </div>) :
                 <p>a</p>}
                </div>
        );
    }
}

export default Account;
