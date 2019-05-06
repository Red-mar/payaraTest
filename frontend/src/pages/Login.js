import React from 'react';

import { PersonLogin } from '../component/Person';

class Login extends React.Component {
    render() {
        return (
                <div>
                <div class="jumbotron">
                <h1 class="display-4">Login</h1>
                <p></p>
                </div>
                <PersonLogin />
                </div>
        );
    }
}

export default Login;
