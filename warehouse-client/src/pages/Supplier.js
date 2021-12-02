import React, {useEffect, useState} from 'react';
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import axios from "axios";
import {
    AvForm,
    AvField
} from 'availity-reactstrap-validation';
import {API_PATH, HEADER} from "../service/api";
import {toast} from "react-toastify";

const Supplier = () => {
    const [suppliers, setSupplier] = useState([]);
    const [openModal, setOpenModal] = useState(false);
    const [currentItem, setCurrentItem] = useState(undefined);
    const [deleteModal, setDeleteModal] = useState(false);
    useEffect(() => {
        getSuppliers()
    }, [])

    const getSuppliers = () => {
        //bazaga borib olib kelishi kerak
        axios.get(API_PATH + "supplier", HEADER)
            .then(res => {
                setSupplier(res.data)
                console.log(res)
            })
    }

    const open = () => {
        setOpenModal(!openModal);
    }
    const closeDelete = () => {
        setDeleteModal(!deleteModal);
    }
    const saveSupplier = (event, values) => {
        if (currentItem === undefined) {
            axios.post(API_PATH + "supplier", values, HEADER)
                .then(response => {
                    console.log(response)
                    open()
                    getSuppliers()
                    toast.success(response.data.message)
                })
        } else {
            axios.put(API_PATH + "supplier/" + currentItem.id, values, HEADER)
                .then(response => {
                    console.log(response)
                    open()
                    getSuppliers()
                    toast.success(response.data.message)
                    setCurrentItem(undefined)
                })
        }
    }

    const edit = (item) => {
        setCurrentItem(item);
        open();
    }

    const deleteSupplier = (item) => {
        closeDelete();
        setCurrentItem(item);
    }

    const deleteSupp = () => {
        axios.delete(API_PATH + "supplier/" + currentItem.id, HEADER)
            .then(value => {
                console.log(value)
                toast.success("DELETED!")
                closeDelete();
                getSuppliers();
                setCurrentItem(undefined)
            })
    }
    return (
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
                            suppliers.map((value, index) =>
                                <tr key={index}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{value.name}</td>
                                    <td>{value.phoneNumber}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => edit(value)}>EDIT
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={() => deleteSupplier(value)}>DELETE
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </div>
            </div>


            <Modal isOpen={openModal}>
                <ModalHeader toggle={() => open()}>
                    Supplier qo'shish
                </ModalHeader>
                <ModalBody>

                    <AvForm onValidSubmit={saveSupplier}>
                        {/* With AvField */}
                        <AvField name="name" label="Nomi" required value={currentItem ? currentItem.name : ""}/>
                        <AvField name="phoneNumber" label="Telefon nomer" required
                                 value={currentItem ? currentItem.phoneNumber : ""}/>
                        <button type="submit">Save</button>
                    </AvForm>
                </ModalBody>
            </Modal>

            <Modal isOpen={deleteModal}>
                <ModalHeader toggle={() => closeDelete()}>
                    O'chirishni tasdiqlaysizmi?
                </ModalHeader>
                <ModalBody>
                    <button onClick={() => deleteSupp()}>Xa</button>
                    <button onClick={() => closeDelete()}>Yo'q</button>

                </ModalBody>
            </Modal>
        </div>
    );
};

export default Supplier;