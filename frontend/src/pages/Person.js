import React from 'react';

import { PersonList, PersonForm, Person2FA } from '../component/Person';

class Person extends React.Component {
    render() {
        return (
                <div>
                <div class="jumbotron">
                <h1 class="display-4">Register</h1>
                </div>
                <PersonForm />
                </div>
        );
    }
}

export default Person;
