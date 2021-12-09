import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import "bootstrap/dist/css/bootstrap.min.css"
import 'react-toastify/dist/ReactToastify.css';
import "react-datepicker/dist/react-datepicker.css";
import {ToastContainer} from "react-toastify";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {applyMiddleware, compose, createStore} from "redux";
import redux_thunk from "redux-thunk"
import {rootReducer} from "./redux/reducer/rootreducer";
import {Provider} from "react-redux";
//asosiy reducer,  asinxron compose
export const baza = createStore(rootReducer, compose(applyMiddleware(redux_thunk)))

ReactDOM.render(
    <BrowserRouter>
        <Provider store={baza}>
            <App/>
        </Provider>
    </BrowserRouter>,

    document.getElementById('root')
)
;

