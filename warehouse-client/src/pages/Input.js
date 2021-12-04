import React, {useEffect, useState} from 'react';
import {toast} from "react-toastify";
import {Modal, ModalBody, ModalHeader, Table} from "reactstrap";
import {
    AvForm,
    AvField
} from 'availity-reactstrap-validation';
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {API_PATH, HEADER} from "../service/api";
import DatePicker from "react-datepicker";
// registerLocale('en', en)
const Input = () => {
        let history = useNavigate();
        const [inputs, setInputs] = useState([]);
        const [suppliers, setSuppliers] = useState([]);
        const [currencies, setCurrencies] = useState([]);
        const [measurements, setMeasurements] = useState([]);
        const [warehouses, setWarehouses] = useState([]);
        const [products, setProducts] = useState([]);
        const [openModal, setOpenModal] = useState(false);
        const [currentItem, setCurrentItem] = useState(undefined);
        const [deleteModal, setDeleteModal] = useState(false);
        const [html, setHtml] = useState([]);
        const [productId, setProductId] = useState(undefined);
        const [productPrice, setProductPrice] = useState(undefined);
        const [productAmount, setProductAmount] = useState(undefined);
        const [productDate, setProductDate] = useState(undefined);
        const [inputProductDTO, setInputProductDTO] = useState({});
        const [inputProducts, setInputProducts] = useState([]);
        const [fromDate, setFromDate] = useState('');
        const [toDate, setToDate] = useState('');

        useEffect(() => {
            getSuppliers()
            getInputs()
            getProducts()
            getCurrencies()
            getMeasurements()
            getWarehouses()
        }, [])
        const getFromToInputs = () => {
            axios.get("input?from=" + fromDate + "&to=" + toDate, HEADER)
                .then(value => {
                    console.log(value)
                })
        }
        const addRow = () => {
            let basket = {
                productId: productId,
                productAmount: productAmount,
                productPrice: productPrice,
                productDate: productDate
            }
            setInputProductDTO(inputProductDTO)
            inputProducts.push(basket);
            setInputProducts(inputProducts)
            setHtml([...html, `Entry ${html.length + 1}`]);
        }
        const minusRow = () => {
            let a = html.splice(html[html.length - 1], 1)
            setHtml([...html]);
        }
        const getSuppliers = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "supplier", HEADER)
                .then(res => {
                    setSuppliers(res.data)
                })
        }
        const getCurrencies = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "currency/all", HEADER)
                .then(res => {
                    setCurrencies(res.data)
                })
        }
        const getMeasurements = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "measurement", HEADER)
                .then(res => {
                    setMeasurements(res.data)
                    // console.log(res)
                })
        }
        const getProducts = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "product/allProduct", HEADER)
                .then(res => {
                    setProducts(res.data)
                })
        }
        const getWarehouses = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "warehouse", HEADER)
                .then(res => {
                    setWarehouses(res.data)
                })
        }
        const getInputs = () => {
            //bazaga borib olib kelishi kerak
            axios.get(API_PATH + "input/all?page=0&size=10", HEADER)
                .then(res => {
                    console.log(res)
                    setInputs(res.data.object.content)
                })
        }
        const open = () => {
            setOpenModal(!openModal);
        }
        const closeDelete = () => {
            setDeleteModal(!deleteModal);
        }
        const saveInput = (event, values) => {
            console.log(values)
            let dto = inputProducts;
            let inputDTO = {
                productId: values.productId,
                amount: values.amount,
                price: values.price,
                expireDate: values.expireDate
            }
            dto.push(inputDTO)
            if (currentItem === undefined) {
                axios.post(API_PATH + "input", {...values, inputProductDTOS: dto}, HEADER)
                    .then(response => {
                        console.log(response)
                        open()
                        getInputs()
                        toast.success(response.data.message)
                    })
            } else {
                // axios.put(API_PATH + "input/" + currentItem.id, values, HEADER)
                //     .then(response => {
                //         console.log(response)
                //         open()
                //         getSuppliers()
                //         toast.success(response.data.message)
                //         setCurrentItem(undefined)
                //     })
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
                {console.log(fromDate)}
                {console.log(toDate)}
                <div className="container">
                    <div className="row">
                        <div className="col-3">
                            <button className="btn btn-success" onClick={() => open()}>Add Kirim</button>
                        </div>
                    </div>

                    <div className="row mt-5">
                        <div className="row">
                            <div className="col-md-6">
                                <DatePicker
                                    dateFormat="dd-MM-yyyy"
                                    locale="en" selected={fromDate} onChange={(date) => setFromDate(date)}/>
                            </div>
                            <div className="col-md-6">
                                <DatePicker
                                    dateFormat="dd-MM-yyyy"
                                    locale="en" selected={toDate} onChange={(date) => {
                                    setToDate(date)
                                    getFromToInputs()
                                }
                                }/>
                            </div>
                        </div>
                        <Table
                            bordered
                            hover
                            responsive
                            striped>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>FactureNumber</th>
                                <th>Date</th>
                                <th>Ombor</th>
                                <th>Taminotchi</th>
                                <th>Summa</th>
                                <th>Operations</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                inputs.map((value, index) =>
                                    <tr key={index} onClick={
                                        () => history("/supplier/" + value.id)}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{value.factureNumber}</td>
                                        <td>{value.date.substring(0, 10) + " " + value.date.substring(11, 19)}</td>
                                        <td>{value.warehouseName}</td>
                                        <td>{value.supplierName}</td>
                                        <td>{value.summa}</td>
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

                <Modal isOpen={openModal} fullscreen={'xl'} size={'xl'}>
                    <ModalHeader toggle={() => open()}>
                        Input(Kirim) qo'shish
                    </ModalHeader>
                    <ModalBody>

                        <AvForm onValidSubmit={saveInput}>
                            <div className="row">
                                <div className="col-md-4">
                                    <AvField name="factureName" label="Faktura Nomi" required
                                             value={currentItem ? currentItem.factureName : ""}/>
                                </div>
                                <div className="col-md-4">
                                    <AvField type="select" name="warehouseId" label="Ombor"
                                             helpMessage="Idk, this is an example. Deal with it!">
                                        <option value="">Omborni tanlang</option>
                                        {warehouses?.map((item, index) =>
                                            <option value={item.id}>{item.name}</option>
                                        )}
                                    </AvField>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-4">
                                    <AvField type="select" name="currencyId" label="Valyuta"
                                             helpMessage="Idk, this is an example. Deal with it!">
                                        <option value="">Valyutani tanlang</option>
                                        {currencies.map((item, index) =>
                                            <option value={item.id}>{item.name}</option>
                                        )}
                                    </AvField>
                                </div>
                                <div className="col-md-4">
                                    <AvField type="select" name="supplierId" label="Taminotchii"
                                             helpMessage="Idk, this is an example. Deal with it!">
                                        <option value="">Ta'minotchini tanlang</option>
                                        {suppliers.map((item, index) =>
                                            <option value={item.id}>{item.name}</option>
                                        )}
                                    </AvField>
                                </div>
                            </div>
                            <div>
                                <button className="mt-4 btn btn-success" onClick={() => addRow()}>+</button>
                                {html.map((value) => {
                                    return (
                                        <div className="row">
                                            <div className="col-md-3">
                                                <AvField type="select" name="productId" label="Mahsulot"
                                                         helpMessage="Idk, this is an example. Deal with it!"
                                                         onChange={(e) => {
                                                             setProductId(e.target.value)
                                                         }}>
                                                    <option value="">Mahsulotni tanlang</option>
                                                    {products.map((item, index) =>
                                                        <option value={item.id}>{item.name}</option>
                                                    )}
                                                </AvField>
                                            </div>
                                            <div className="col-md-2">
                                                <AvField name="amount" label="Miqdori" required
                                                         value={currentItem ? currentItem.amount : ""}
                                                         onChange={(e) => {
                                                             setProductAmount(e.target.value)
                                                         }}/>
                                            </div>
                                            <div className="col-md-2">
                                                <AvField name="price" label="Narxi" required
                                                         value={currentItem ? currentItem.price : ""}
                                                         onChange={(e) => {
                                                             setProductPrice(e.target.value)
                                                         }}/>
                                            </div>
                                            <div className="col-md-3">
                                                <AvField type={"date"} name="expireDate" label="Expire" required
                                                         value={currentItem ? currentItem.expireDate : ""}
                                                         onChange={(e) => {
                                                             setProductDate(e.target.value)
                                                         }}/>
                                            </div>
                                            <div className="col-md-2">
                                                <button className="mt-4 btn btn-success" onClick={() => addRow()}>+
                                                </button>
                                                <button className="mt-4 btn btn-danger" onClick={() => minusRow()}>-
                                                </button>
                                            </div>
                                        </div>
                                    )
                                })}
                            </div>

                            <button className="btn btn-success" type="submit" onClick={() => addRow}>Save</button>
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
    }
;

export default Input;