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

const Product = () => {
    let history = useNavigate();
    const [categories, setCategories] = useState([]);
    const [measurements, setMeasurements] = useState([]);
    const [products, setProduct] = useState([]);
    const [openModal, setOpenModal] = useState(false);
    const [currentItem, setCurrentItem] = useState(undefined);
    const [deleteModal, setDeleteModal] = useState(false);
    const [photoId, setPhotoId] = useState(undefined);
    useEffect(() => {
        getMeasurements()
        getCategories()
        getProducts()
    }, [])

    const getMeasurements = () => {
        //bazaga borib olib kelishi kerak
        axios.get(API_PATH + "measurement", HEADER)
            .then(res => {
                setMeasurements(res.data)
                // console.log(res)
            })
    }
    const getCategories = () => {
        //bazaga borib olib kelishi kerak
        axios.get(API_PATH + "category/all", HEADER)
            .then(res => {
                setCategories(res.data.object)
                // console.log(res)
            })
    }
    const getProducts = () => {
        //bazaga borib olib kelishi kerak
        axios.get(API_PATH + "product/allProduct", HEADER)
            .then(res => {
                setProduct(res.data)
                // console.log(res)
            })
    }

    const open = () => {
        setOpenModal(!openModal);
    }
    const closeDelete = () => {
        setDeleteModal(!deleteModal);
    }
    const saveProduct = (event, values) => {
        if (currentItem === undefined) {
            let obj = {
                photoId: photoId,
                ...values
            }
            // console.log(obj)
            // console.log(...values)
            axios.post(API_PATH + "product", obj, HEADER)
                .then(response => {
                    // console.log(response)
                    open()
                    getProducts()
                    toast.success(response.data.message)
                })
        } else {
            axios.put(API_PATH + "product/" + currentItem.id, values, HEADER)
                .then(response => {
                    // console.log(response)
                    open()
                    getProducts()
                    toast.success(response.data.message)
                    setCurrentItem(undefined)
                })
        }
    }

    const edit = (item) => {
        console.log(item)
        setCurrentItem(item);
        open();
    }

    const deletePro = (item) => {
        closeDelete();
        setCurrentItem(item);
    }

    const deleteProduct = () => {
        axios.delete(API_PATH + "product/" + currentItem.id, HEADER)
            .then(value => {
                // console.log(value)
                toast.success("DELETED!")
                closeDelete();
                getProducts();
                setCurrentItem(undefined)
            })
    }

    const saveFile = (data) => {
        // console.log(data.target.files[0])
        // console.log(data.target)
        let image = new FormData();
        image.append(data.target.files[0].name, data.target.files[0]);

        axios.post(API_PATH + "attachment/upload", image, HEADER)
            .then(value => {
                // console.log(value)
                setPhotoId(value.data[0])
            })
    }

    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-3">
                        <button className="btn btn-success" onClick={() => open()}>Add Product</button>
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
                            <th>Rasm</th>
                            <th>Name</th>
                            <th>Code</th>
                            <th>CategoryName</th>
                            <th>O'lchov birligi</th>
                            <th>Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        {products.map((value, index) =>
                            <tr key={index}>
                                <th scope="row">{index + 1}</th>
                                <td><img className="img-fluid" width="60px" height="60px"
                                         src={API_PATH + "attachment/download/" + value.photo?.id} alt=""/></td>
                                <td>{value.name}</td>
                                <td>{value.code}</td>
                                <td>{value.category.name}</td>
                                <td>{value.measurement.name}</td>
                                <td>
                                    <button className="btn btn-warning" onClick={() => edit(value)}>EDIT
                                    </button>
                                    <button className="btn btn-danger"
                                            onClick={() => deletePro(value)}>DELETE
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
                    Product qo'shish
                </ModalHeader>
                <ModalBody>

                    <AvForm onValidSubmit={saveProduct}>


                        <input type="file"
                            // value={currentItem ? currentItem.photo : ""}
                               onChange={(e) => saveFile(e)}/>
                        {/* With AvField */}
                        <AvField name="name" label="Nomi" required value={currentItem ? currentItem.name : ""}/>

                        <AvField type="select" name="catId" label="Category"
                                 value={currentItem ? currentItem.category.id : ""}
                                 helpMessage="Idk, this is an example. Deal with it!">
                            <option value="">Categoryni tanlang</option>
                            {categories?.map((item, index) =>
                                <option value={item.id}>{item.name}</option>
                            )}
                        </AvField>
                        <AvField type="select" name="measureId" label="Olchov turi"
                                 value={currentItem ? currentItem.measurement.id : ""}
                                 helpMessage="Idk, this is an example. Deal with it!"
                        >
                            <option value="">Olchov birligini tanlang</option>
                            {measurements?.map((item, index) =>
                                <option value={item.id}>{item.name}</option>
                            )}
                        </AvField>

                        <button type="submit">Save</button>
                    </AvForm>
                </ModalBody>
            </Modal>

            <Modal isOpen={deleteModal}>
                <ModalHeader toggle={() => closeDelete()}>
                    O'chirishni tasdiqlaysizmi?
                </ModalHeader>
                <ModalBody>
                    <button onClick={() => deleteProduct()}>Xa</button>
                    <button onClick={() => closeDelete()}>Yo'q</button>

                </ModalBody>
            </Modal>
        </div>
    );
};

export default Product;