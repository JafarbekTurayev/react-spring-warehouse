import axios from "axios";
import {API_PATH, HEADER} from "../../service/api";
import {toast} from "react-toastify";
import {UPDATE_STATE} from "../type/types"
//data ketmon
export const updateState = (data) => {
    console.log("update")
    return {
        type: UPDATE_STATE,
        payload: data,
    }
}

export function getMe() {
    return function (dispatch) {
        axios.get(API_PATH + "auth/me", HEADER)
            .then(res => {
                dispatch(updateState({currentUser: res.data}))
            })
    }
}

export function getSuppliers() {
    return function (dispatch) {
        axios.get(API_PATH + "supplier", HEADER)
            .then((res) => {
                // console.log(res)
                dispatch(updateState({suppliers: res.data}))
            })
            .catch((error) => {
                console.log(error);
                toast.error("Xatolik!");
            })
    }
}

export function addSupplier(malumotlar) {
    return function (dispatch) {
        axios.post(API_PATH + "supplier", malumotlar, HEADER)
            .then((res) => {
                console.log(res)
                if (res.data.success) {
                    toast.success("Saved!")
                } else {
                    toast.error("Xatolik!")
                }
            })
            .catch((error) => {
                console.log(error);
                toast.error("Xatolik!");
            })
    }
}

export function editSupplier(id, malumotlar) {
    return function (dispatch) {
        axios.put(API_PATH + "supplier/" + id, malumotlar, HEADER)
            .then((res) => {
                console.log(res)
                if (res.data.success) {
                    toast.success("UPDATED!")
                    getSuppliers()
                } else {
                    toast.error("Xatolik!")
                }
            })
            .catch((error) => {
                console.log(error);
                toast.error("Xatolik!");
            })
    }
}

export function deleteSupplier(id) {
    return function (dispatch) {
        axios.delete(API_PATH + "supplier/" + id, HEADER)
            .then((res) => {
                getSuppliers()
                console.log(res)
                if (res.data) {
                    toast.success("DELETED!")
                } else {
                    toast.error("Xatolik!")
                }
            })
            .catch((error) => {
                console.log(error);
                toast.error("Xatolik!");
            })
    }
}

