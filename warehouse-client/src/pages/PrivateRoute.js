import React from 'react';
import {TOKEN_VALUE} from "../service/api";
import {Navigate} from "react-router-dom";
import AdminNavbar from "./AdminNavbar";

const PrivateRoute = () => {
    return (
        <div>
            {TOKEN_VALUE != null ? <AdminNavbar/> : <Navigate to="/login"/>}
        </div>
    );
};

export default PrivateRoute;