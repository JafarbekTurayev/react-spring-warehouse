import React, {useEffect, useState} from 'react';
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import axios from "axios";
import {
    AvForm,
    AvField
} from 'availity-reactstrap-validation';
import {API_PATH, HEADER} from "../service/api";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

const Currency = () => {
    let history = useNavigate();
    const [currencies, setCurrency] = useState([]);
    const [openModal, setOpenModal] = useState(false);
    const [currentItem, setCurrentItem] = useState(undefined);
    const [deleteModal, setDeleteModal] = useState(false);
    useEffect(() => {
        getCurrencies()
    }, [])

    const getCurrencies = () => {
        //bazaga borib olib kelishi kerak
        axios.get(API_PATH + "currency/all", HEADER)
            .then(res => {
                setCurrency(res.data)
                console.log(res)
            })
    }

    const open = () => {
        setOpenModal(!openModal);
    }
    const closeDelete = () => {
        setDeleteModal(!deleteModal);
    }
    const saveCurrency = (event, values) => {
        if (currentItem === undefined) {
            axios.post(API_PATH + "currency", values, HEADER)
                .then(response => {
                    console.log(response)
                    open()
                    getCurrencies()
                    toast.success(response.data.message)
                })
        } else {
            axios.put(API_PATH + "currency/" + currentItem.id, values, HEADER)
                .then(response => {
                    console.log(response)
                    open()
                    getCurrencies()
                    toast.success(response.data.message)
                    setCurrentItem(undefined)
                })
        }
    }

    const edit = (item) => {
        setCurrentItem(item);
        open();
    }

    const deleteCurrency = (item) => {
        closeDelete();
        setCurrentItem(item);
    }

    const deleteSupp = () => {
        axios.delete(API_PATH + "currency/" + currentItem.id, HEADER)
            .then(value => {
                console.log(value)
                toast.success("DELETED!")
                closeDelete();
                getCurrencies();
                setCurrentItem(undefined)
            })
    }
    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-3">
                        <button className="btn btn-success" onClick={() => open()}>Add Currency</button>
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
                            <th>Active</th>
                            <th>Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            currencies.map((value, index) =>
                                <tr key={index} onClick={
                                    () => history("/currency/" + value.id)}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{value.name}</td>
                                    <td>{value.active}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => edit(value)}>EDIT
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={() => deleteCurrency(value)}>DELETE
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
                    Currency qo'shish
                </ModalHeader>
                <ModalBody>

                    <AvForm onValidSubmit={saveCurrency}>
                        {/* With AvField */}
                        <AvField name="name" label="Nomi" required value={currentItem ? currentItem.name : ""}/>
                        {/*<AvField name="phoneNumber" label="Telefon nomer" required*/}
                        {/*         value={currentItem ? currentItem.phoneNumber : ""}/>*/}
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

export default Currency;