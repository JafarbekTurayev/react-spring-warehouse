import {GET_SUPPLIER, UPDATE_STATE} from "../type/types";

const initialState = {
    suppliers: [],
    categories: [],
    products: [],
    currentUser: {}
};

//state var action metodlar
export const appReducer = (state = initialState, action) => {
    switch (action.type) {
        case UPDATE_STATE:
            return {...state, ...action.payload};
        case GET_SUPPLIER:
            return;
        default:
            return state;
    }
}