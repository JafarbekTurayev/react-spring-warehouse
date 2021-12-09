import React, {useEffect} from 'react';
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {UPDATE_STATE} from "../type/types";
import {connect} from "react-redux";
import Measurement from "./Measurement";
import {addMeas, deleteMeas, editMeas, updateState, getMeasurements} from "../action/measurementAction";

const MeasurementRedux = (props) => {
    useEffect(() => {
        props.getMeasurements()
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
    const saveMeas = (event, values) => {
        if (props.currentItem !== undefined) {
            props.editMeas(props.currentItem.id, values)
            props.getMeasurements()
            open()
        } else {
            props.addMeas(values);
            props.getMeasurements()
            open()
        }
    }

    const edit = (item) => {
        props.updateState(
            {
                modalOpen: !props.modalOpen,
                currentItem: item
            }
        )
    }
    const deleteM = (item) => {
        props.updateState(
            {
                deleteModal: !props.deleteModal,
                deleteId: item.id
            }
        )
    }
    const deleteMeas = () => {
        props.deleteMeas(props.deleteId)
        // props.getSuppliers()
        openDeleteModal()
    }
    return (
        <div>
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-3">
                            <button className="btn btn-success" onClick={() => open()}>Add Measurement</button>
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
                                <th>Operations</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                props?.measurements.map((value, index) =>
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{value.name}</td>
                                        <td>
                                            <button className="btn btn-warning" onClick={() => edit(value)}>EDIT
                                            </button>
                                            <button className="btn btn-danger"
                                                    onClick={() => deleteM(value)}>DELETE
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
                        Measurement qo'shish
                    </ModalHeader>
                    <ModalBody>

                        <AvForm onValidSubmit={saveMeas}>
                            {/* With AvField */}
                            <AvField name="name" label="Nomi" required
                                     value={props.currentItem ? props.currentItem.name : ""}/>
                            <button type="submit">Save</button>
                        </AvForm>
                    </ModalBody>
                </Modal>

                <Modal isOpen={props.deleteModal}>
                    <ModalHeader toggle={() => openDeleteModal()}>
                        O'chirishni tasdiqlaysizmi?
                    </ModalHeader>
                    <ModalBody>
                        <button onClick={() => deleteMeas()}>Xa</button>
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
        measurements: state.meas.measurements,
        modalOpen: state.meas.modalOpen,
        deleteModal: state.meas.deleteModal,
        currentItem: state.meas.currentItem,
        deleteId: state.meas.deleteId
    }
}

export default connect(mapStateToProps, {
    getMeasurements,
    updateState,
    editMeas,
    addMeas,
    deleteMeas
})(MeasurementRedux);