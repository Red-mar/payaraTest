import React from 'react';

import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

import Home from './pages/Home';
import Stream from './pages/Stream';
import Person from './pages/Person';
import Login from './pages/Login';
import Account from './pages/Account.js';

class App extends React.Component {

  render() {
      return (
              <Router>

              <div>

              <nav aria-label="breadcrumb">
              <ol class="breadcrumb">
              <li class="breadcrumb-item"><Link to="/">Home</Link></li>
              <li class="breadcrumb-item"><Link to="/stream">Stream</Link></li>
              <li class="breadcrumb-item"><Link to="/account">Account</Link></li>
              <li class="breadcrumb-item"><Link to="/register">Register</Link></li>
              <li class="breadcrumb-item"><Link to="/login">Login</Link></li>
              </ol>
              </nav>

              <Route exact path="/" component={Home} />
              <Route path="/stream" component={Stream} />
              <Route path="/register" component={Person} />
              <Route path="/login" component={Login} />
              <Route path="/account" component={Account} />
              </div>
              </Router>

      );
  }

}

export default App;
