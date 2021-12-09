import {GET_SUPPLIER, UPDATE_STATE} from "../type/types";

const initialState = {
    modalOpen: false,
    deleteModal: false,
    currentItem: undefined,
    deleteId: undefined,
    measurements: []
};

//state var action metodlar
export const measurementReducer = (state = initialState, action) => {
    console.log(action)
    switch (action.type) {
        case UPDATE_STATE:
            return {...state, ...action.payload};
        default:
            return state;
    }
}