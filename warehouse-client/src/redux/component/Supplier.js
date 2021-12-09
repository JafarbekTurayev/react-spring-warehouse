import React, {useEffect} from 'react';
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {UPDATE_STATE} from "../type/types";
import {addSupplier, deleteSupplier, editSupplier, getSuppliers, updateState} from "../action/appAction";
import {connect} from "react-redux";

const SupplierRedux = (props) => {
    useEffect(() => {
        props.getSuppliers()
    }, [])
    const open = () => {
        props.updateState(
            {
                modalOpen: !props.modalOpen
            }
        )
    }
    const openDeleteModal = () => {
        props.updateState(
            {
                deleteModal: !props.deleteModal
            }
        )
    }
    const saveSupplier = (event, values) => {
        if (props.currentSupplier !== undefined) {
            props.editSupplier(props.currentSupplier.id, values)
            props.getSuppliers()
            open()
        } else {
            props.addSupplier(values);
            props.getSuppliers()
            open()
        }
    }

    const edit = (item) => {
        props.updateState(
            {
                modalOpen: !props.modalOpen,
                currentSupplier: item
            }
        )
    }
    const deleteS = (item) => {
        props.updateState(
            {
                deleteModal: !props.deleteModal,
                deleteSupplierId: item.id
            }
        )
    }
    const deleteSupplier = () => {
        props.deleteSupplier(props.deleteSupplierId)
        // props.getSuppliers()
        openDeleteModal()
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
                                        <td>
                                            <button className="btn btn-warning" onClick={() => edit(value)}>EDIT
                                            </button>
                                            <button className="btn btn-danger"
                                                    onClick={() => deleteS(value)}>DELETE
                                            </button>
                                        </td>
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
                    <ModalBody>

                        <AvForm onValidSubmit={saveSupplier}>
                            {/* With AvField */}
                            <AvField name="name" label="Nomi" required
                                     value={props.currentSupplier ? props.currentSupplier.name : ""}/>
                            <AvField name="phoneNumber" label="Telefon nomer" required
                                     value={props.currentSupplier ? props.currentSupplier.phoneNumber : ""}/>
                            <button type="submit">Save</button>
                        </AvForm>
                    </ModalBody>
                </Modal>

                <Modal isOpen={props.deleteModal}>
                    <ModalHeader toggle={() => openDeleteModal()}>
                        O'chirishni tasdiqlaysizmi?
                    </ModalHeader>
                    <ModalBody>
                        <button onClick={() => deleteSupplier()}>Xa</button>
                        <button onClick={() => openDeleteModal()}>Yo'q</button>

                    </ModalBody>
                </Modal>
            </div>
        </div>
    );
};
const mapStateToProps = (state) => {
    console.log(state)
    return {
        suppliers: state.app.suppliers,
        me: state.app.currentUser,
        modalOpen: state.app.modalOpen,
        deleteModal: state.app.deleteModal,
        currentSupplier: state.app.currentSupplier,
        deleteSupplierId: state.app.deleteSupplierId
    }
}

export default connect(mapStateToProps, {
    getSuppliers,
    updateState,
    editSupplier,
    addSupplier,
    deleteSupplier
})(SupplierRedux);