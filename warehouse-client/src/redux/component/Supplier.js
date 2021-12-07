import React, {useEffect} from 'react';
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {UPDATE_STATE} from "../type/types";
import {getSuppliers, updateState} from "../action/appAction";
import {connect} from "react-redux";

const SupplierRedux = (props) => {
    useEffect(() => {
        props.getSuppliers()
    }, [])
    const open = () => {
        console.log("keldi")
        props.updateState(
            {
                modalOpen: !props.modalOpen
            }
        )
    }

    return (
        <div>
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-3">
                            <button className="btn btn-success" onClick={() => open()}>Add Supplier</button>
                        </div>
                    </div>

                    <div className="row mt-5">
                        {/*<Table columns={columns} data={data}/>*/}
                        <Table
                            bordered
                            hover
                            responsive
                            striped>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>PhoneNumber</th>
                                <th>Operations</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                props.suppliers.map((value, index) =>
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{value.name}</td>
                                        <td>{value.phoneNumber}</td>
                                        {/*<td>*/}
                                        {/*    <button className="btn btn-warning" onClick={() => edit(value)}>EDIT*/}
                                        {/*    </button>*/}
                                        {/*    <button className="btn btn-danger"*/}
                                        {/*            onClick={() => deleteSupplier(value)}>DELETE*/}
                                        {/*    </button>*/}
                                        {/*</td>*/}
                                    </tr>
                                )
                            }
                            </tbody>
                        </Table>
                    </div>
                </div>

                <Modal isOpen={props.modalOpen}>
                    <ModalHeader toggle={() => open()}>
                        Supplier qo'shish
                    </ModalHeader>
                    {/*<ModalBody>*/}

                    {/*    <AvForm onValidSubmit={saveSupplier}>*/}
                    {/*        /!* With AvField *!/*/}
                    {/*        <AvField name="name" label="Nomi" required value={currentItem ? currentItem.name : ""}/>*/}
                    {/*        <AvField name="phoneNumber" label="Telefon nomer" required*/}
                    {/*                 value={currentItem ? currentItem.phoneNumber : ""}/>*/}
                    {/*        <button type="submit">Save</button>*/}
                    {/*    </AvForm>*/}
                    {/*</ModalBody>*/}
                </Modal>

                {/*<Modal isOpen={deleteModal}>*/}
                {/*    <ModalHeader toggle={() => closeDelete()}>*/}
                {/*        O'chirishni tasdiqlaysizmi?*/}
                {/*    </ModalHeader>*/}
                {/*    <ModalBody>*/}
                {/*        <button onClick={() => deleteSupp()}>Xa</button>*/}
                {/*        <button onClick={() => closeDelete()}>Yo'q</button>*/}

                {/*    </ModalBody>*/}
                {/*</Modal>*/}
            </div>
        </div>
    );
};
const mapStateToProps = (state) => {
    console.log(state)
    return {
        suppliers: state.app.suppliers,
        me: state.app.currentUser,
        modalOpen: state.app.modalOpen
    }
}

export default connect(mapStateToProps, {getSuppliers, updateState})(SupplierRedux);