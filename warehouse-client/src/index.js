import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import "bootstrap/dist/css/bootstrap.min.css"
import 'react-toastify/dist/ReactToastify.css';
import "react-datepicker/dist/react-datepicker.css";
import {ToastContainer} from "react-toastify";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./pages/Login";
import Supplier from "./pages/Supplier";
import OneSupplier from "./pages/OneSupplier";
import Test from "./pages/Test";
import Currency from "./pages/Currency";
import Input from "./pages/Input";
import Product from "./pages/Product";

ReactDOM.render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<App/>}/>
            <Route path="/login" element={<Login/>}/>
            {/*<Route path="/supplier" element={<Supplier/>}>*/}
            {/*    <Route path=":id" element={<OneSupplier/>}/>*/}
            {/*    <Route path="test" element={<Test/>}/>*/}
            {/*</Route>*/}
            <Route path="/supplier" element={<Supplier/>}/>
            <Route path="/supplier/:id" element={<OneSupplier/>}/>
            <Route path="/supplier/test" element={<Test/>}/>
            <Route path="/input" element={<Input/>}/>
            <Route path="/currency" element={<Currency/>}/>
            <Route path="/product" element={<Product/>}/>
            {/*<Route path="/measurement" element={<Measurement/>}/>*/}
        </Routes>
    </BrowserRouter>,
    document.getElementById('root')
)
;

