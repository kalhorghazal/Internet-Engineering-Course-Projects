import React, {useState} from "react";
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from "./pages/home/Home";
<<<<<<< HEAD
import UnitSelection from "./pages/unitSelection/UnitSelection";
=======
import Login from "./pages/login/Login";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from "react-toastify";
// import 'react-notifications/lib/notifications.css';
// import {NotificationContainer, NotificationManager} from 'react-notifications';

>>>>>>> 3053b1e2d71c3481727e6631908c535fa09d1e2c

function App() {
  const [token, setToken] = useState();
  
  if(!token) {
    return <Login setToken={setToken} />
  }

  return (
    <Router>
      <Switch>
        <Route path="/login">
          <Login/>
        </Route>
        <Route exact path="/" component={Home} />

        <Route path="/courses">
          <UnitSelection />
        </Route>

      </Switch>
      <ToastContainer/>
    </Router>
  );
}

export default App;
