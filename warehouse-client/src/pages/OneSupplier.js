import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import {API_PATH, HEADER} from "../service/api";

const OneSupplier = () => {
    const [supplier, setSupplier] = useState({});
    useEffect(() => {
        getOneSupplier()
    }, []);
    const getOneSupplier = () => {
        axios.get(API_PATH + "supplier/" + param.id, HEADER)
            .then(res => {
                console.log(res)
                setSupplier(res.data.object)
            })
    }
    let param = useParams();
    return (
        <div>
            {console.log(param)}
            <h1>One Supplier</h1>
            <h1>{supplier.id}</h1>
            <h1>{supplier.name}</h1>
            {/*<h1>{supplier.active}</h1>*/}
            <h1>{supplier.phoneNumber}</h1>
        </div>
    );
};

export default OneSupplier;