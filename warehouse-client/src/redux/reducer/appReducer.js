import {GET_SUPPLIER, UPDATE_STATE} from "../type/types";

const initialState = {
    modalOpen: false,
    deleteModal: false,
    currentSupplier: undefined,
    deleteSupplierId: undefined,
    suppliers: [],
    categories: [],
    products: [],
    currentUser: {}
};

//state var action metodlar
export const appReducer = (state = initialState, action) => {
    console.log(action)
    switch (action.type) {
        case UPDATE_STATE:
            console.log("reducer")
            return {...state, ...action.payload};
        case GET_SUPPLIER:
            return;
        default:
            return state;
    }
}