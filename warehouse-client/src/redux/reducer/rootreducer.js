import {combineReducers} from "redux";
import {appReducer} from "./appReducer";

//brigadir
export const rootReducer = combineReducers(
    {
        app: appReducer
        // login:loginReducer
        //supplier:suppliReducer
    }
    //1-ishchi
    //2-ishchi
)