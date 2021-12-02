import React from 'react';
import Supplier from "./pages/Supplier";
import Login from "./pages/Login";
import {Link} from "react-router-dom";

const App = () => {
    return (
        <div>
            <h1>App page</h1>
            <div>
                <Link to="/supplier">Supplier page</Link>
                <br/>
                <Link to="/login">Login page</Link>
                <br/>
                <Link to="/currency">Currency page</Link>
                <br/>
                <Link to="/input">Input page</Link>
                <br/>
            </div>


        </div>
    );
};

export default App;