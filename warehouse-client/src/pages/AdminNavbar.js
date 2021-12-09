import React from 'react';
import {Link} from "react-router-dom";

const AdminNavbar = () => {
    return (
        <div>
            <div>
                <Link to="/measurement">Meas page</Link>
                <br/>
                <Link to="/supplier">Supplier page</Link>
                <br/>
                <Link to="/currency">Currency page</Link>
                <br/>
                <Link to="/input">Input page</Link>
                <br/>
                <Link to="/product">Product page</Link>
                <br/>
            </div>
        </div>
    );
};

export default AdminNavbar;