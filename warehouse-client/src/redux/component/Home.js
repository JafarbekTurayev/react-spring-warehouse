import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {getMe, getSuppliers} from "../action/appAction";

const Home = (store) => {
    useEffect(() => {
        // localStorage.setItem("warehouse-token", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MTI0NTU4OTciLCJpYXQiOjE2Mzg4ODk5MDMsImV4cCI6MTYzODk3NjMwM30.1N1rAJ9OupIRdhs057qozfjC0SPBhJguEr-rL61hHMDwuQYYFRwngOf6dgLVf47BPStMPFbece_O0in9-1UPyg")
        store.getSuppliers()
        store.getMe()
    }, [])
    return (
        <div>
            <div>Home page</div>
            {console.log(store)}
            <p>{store.me.username}</p>
        </div>
    );
};

const mapStateToProps = (state) => {
    console.log(state)
    return {
        suppliers: state.app.suppliers,
        me: state.app.currentUser
    }
}


export default connect(mapStateToProps, {getSuppliers, getMe})(Home);