import React from 'react';
import Login from "./pages/Login";
import {Link, Route, Routes} from "react-router-dom";
import PrivateRoute from "./pages/PrivateRoute";
import Supplier from "./redux/component/Supplier";
import AdminNavbar from "./pages/AdminNavbar";
import SupplierRedux from "./redux/component/Supplier";
import {ToastContainer} from "react-toastify";
import MeasurementRedux from "./redux/component/Measurement";

const App = () => {
    return (
        <div>
            {/*<h1>App page</h1>*/}
            <Routes>
                <Route exact path="/" element={<PrivateRoute/>}>
                    <Route exact path='/' element={<AdminNavbar/>}/>
                    {/*<Route path="/supplier/:id" element={<OneSupplier/>}/>*/}
                    {/*<Route path="/supplier/test" element={<Test/>}/>*/}
                    {/*<Route path="/input" element={<Input/>}/>*/}
                    {/*<Route path="/currency" element={<Currency/>}/>*/}
                    {/*<Route path="/product" element={<Product/>}/>*/}
                </Route>
                <Route exact path="/login" element={<Login/>}/>
                <Route exact path="/supplier" element={<SupplierRedux/>}/>
                <Route exact path="/measurement" element={<MeasurementRedux/>}/>
            </Routes>
            <ToastContainer/>
        </div>
    );
};

export default App;