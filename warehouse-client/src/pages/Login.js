import React from 'react';
import {AvField, AvForm} from "availity-reactstrap-validation";
import axios from "axios";
import {useNavigate} from "react-router";
import {API_PATH} from "../service/api";

const Login = () => {
    let ketmon = useNavigate();
    const login = (event, values) => {
        console.log(values)
        axios.post(API_PATH + "auth/login", values)
            .then(value => {
                console.log(value)
                localStorage.setItem("warehouse-token", "Bearer " + value.data)
                ketmon("/supplier")
            })
    }
    return (
        <div>
            <div className="container">
                <AvForm onValidSubmit={login}>

                    {/* With AvField */}
                    <AvField name="userName" label="Telefon nomer" required/>
                    <AvField name="password" label="Parol" required/>
                    <button type="submit" className="btn btn-success">Login</button>
                </AvForm>
            </div>
        </div>
    );
};

export default Login;