package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.ResInputDTO;
import com.example.warehouseapp.payload.ResInputProductDTO;
import com.example.warehouseapp.payload.responce.InputDTO;
import com.example.warehouseapp.payload.responce.InputProductDTO;
import com.example.warehouseapp.repository.*;
import com.example.warehouseapp.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public ApiResponse add(InputDTO inputDTO) throws ParseException {
        Input input = new Input();
        input.setFactureNumber(inputDTO.getFactureName());
        input.setDate(new Date());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        input.setCurrency(optionalCurrency.get());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        input.setSupplier(optionalSupplier.get());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        input.setWarehouse(optionalWarehouse.get());


        //furadan tushirdik
        List<InputProductDTO> inputProductDTOS = inputDTO.getInputProductDTOS();

        List<InputProduct> inputProductList = new ArrayList<>();

        for (InputProductDTO inputProductDTO : inputProductDTOS) {
            InputProduct inputProduct = new InputProduct();

            //iphone
            Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
            inputProduct.setProduct(optionalProduct.get());

            inputProduct.setAmount(inputProductDTO.getAmount());
            inputProduct.setPrice(inputProductDTO.getPrice());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(inputProductDTO.getExpireDate());
            inputProduct.setExpireDate(date);
            inputProduct.setInput(input);

            inputProductList.add(inputProduct);
        }
        input.setInputProductList(inputProductList);
        inputRepository.save(input);
        return new ApiResponse("Saved!", true, input);
    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Input> all = inputRepository.findAll(pageable);
        Page<ResInputDTO> resInputDTOS = new PageImpl<>(
                all.getContent().stream().map(this::getResInput).collect(Collectors.toList()),
                all.getPageable(),
                all.getTotalElements()
        );

        return new ApiResponse("Mana", true, resInputDTOS);
    }

    public ResInputDTO getResInput(Input input) {
        return new ResInputDTO(
                input.getDate(),
                input.getWarehouse().getName(),
                input.getSupplier().getName(),
                input.getCurrency().getName(),
                input.getFactureNumber(),
                input.getInputProductList().stream().map(this::getResInputProductDTO).collect(Collectors.toList()),
                input.getInputProductList().stream().map(this::getSumma).reduce(Double.valueOf(0), (item, i) -> item + i)
        );
    }

    private double getSumma(InputProduct inputProduct) {
        return inputProduct.getPrice() * inputProduct.getAmount();//20* 10000
    }

    public ResInputProductDTO getResInputProductDTO(InputProduct inputProduct) {
        return new ResInputProductDTO(
                inputProduct.getProduct().getName(),
                inputProduct.getAmount(),
                inputProduct.getPrice(),
                inputProduct.getExpireDate()
        );
    }

    public ApiResponse getAllFromTo(String from, String to) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = dateFormat.parse(from);
        Date toDate = dateFormat.parse(to);
        List<Input> allByDateBetween = inputRepository.findAllByDateBetween(fromDate, toDate);

        List<ResInputDTO> collect = allByDateBetween.stream().map(this::getResInput).collect(Collectors.toList());

        return new ApiResponse("Mana", true, collect);
    }

    public ApiResponse getAllSearchType(String type, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = dateFormat.parse(date);
        Date toDate = dateFormat.parse(date);

        List<Input> allByDate = new ArrayList<>();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fromDate);
        if (type.equals("daily")) {
            //kunlik
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date fromDate = dateFormat.parse(date);
//            Date toDate = dateFormat.parse(date);
            allByDate = inputRepository.findAllByDateBetween(fromDate, toDate);
        } else if (type.equals("weekly")) {
            c.add(Calendar.DATE, 7);
            toDate = c.getTime();
            allByDate = inputRepository.findAllByDateBetween(fromDate, toDate);

        } else {
            //oylik
            c.add(Calendar.MONTH, 1);
            toDate = c.getTime();
            allByDate = inputRepository.findAllByDateBetween(fromDate, toDate);
        }

        List<ResInputDTO> collect = allByDate.stream().map(this::getResInput).collect(Collectors.toList());

        return new ApiResponse("Mana", true, collect);

    }

    public ApiResponse getById(Integer id) {
        Optional<Input> input = inputRepository.findById(id);
        return new ApiResponse("Id", true, input.get());
    }

    public ApiResponse getAllHistoryType(Integer supplierId, String from, String to) throws ParseException {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp());
        Date fromDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from);
        Date toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to);
        List<Input> all = new ArrayList<>();
        if (supplierId == 0 && from.equals(AppConstants.BEGIN_DATE) && to.equals(AppConstants.END_DATE)) {
            all = inputRepository.getAllHistory();
        } else if (supplierId != 0 && from.equals(AppConstants.BEGIN_DATE) && to.equals(AppConstants.END_DATE)) {
            all = inputRepository.findAllBySupplier_Id(supplierId);
        } else if (supplierId == 0 && !from.equals(AppConstants.BEGIN_DATE) && !to.equals(AppConstants.END_DATE)) {
            all = inputRepository.findAllByDateBetween(fromDate, toDate);
        } else {
            all = inputRepository.findAllBySupplier_IdAndDateBetween(supplierId, fromDate, toDate);
        }
        return new ApiResponse("Mana", true, all);
    }
}
