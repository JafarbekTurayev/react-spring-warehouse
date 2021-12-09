import {combineReducers} from "redux";
import {appReducer} from "./appReducer";
import {measurementReducer} from "./measurementReducer";

//brigadir
export const rootReducer = combineReducers(
    {
        app: appReducer,
        meas:measurementReducer
        // login:loginReducer
        //supplier:suppliReducer
    }
    //1-ishchi
    //2-ishchi
)