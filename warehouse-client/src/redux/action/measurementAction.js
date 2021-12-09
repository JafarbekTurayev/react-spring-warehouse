import axios from "axios";
import {API_PATH, HEADER} from "../../service/api";
import {toast} from "react-toastify";
import {UPDATE_STATE} from "../type/types"
//data ketmon
export const updateState = (data) => {
    return {
        type: UPDATE_STATE,
        payload: data,
    }
}

export function getMeasurements() {
    return function (dispatch) {
        axios.get(API_PATH + "measurement", HEADER)
            .then((res) => {
                console.log(res)
                dispatch(updateState({measurements: res.data}))
            })
            .catch((error) => {
                console.log(error);
                toast.error("Xatolik!");
            })
    }
}

export function addMeas(malumotlar) {
    return function (dispatch) {
        axios.post(API_PATH + "measurement", malumotlar, HEADER)
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

export function editMeas(id, malumotlar) {
    return function (dispatch) {
        axios.put(API_PATH + "measurement/" + id, malumotlar, HEADER)
            .then((res) => {
                console.log(res)
                if (res.data.success) {
                    toast.success("UPDATED!")
                    getMeasurements()
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

export function deleteMeas(id) {
    return function (dispatch) {
        axios.delete(API_PATH + "measurement/" + id, HEADER)
            .then((res) => {
                getMeasurements()
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