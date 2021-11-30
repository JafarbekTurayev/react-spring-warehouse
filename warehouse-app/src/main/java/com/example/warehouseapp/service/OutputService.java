package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.ResOutputDto;
import com.example.warehouseapp.payload.RestOutputProductDto;
import com.example.warehouseapp.payload.responce.OutputDto;
import com.example.warehouseapp.payload.responce.OutputProductDto;
import com.example.warehouseapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    public ApiResponse addOutput(OutputDto outputDto) {
        Output output  = new Output();
        output.setDate(new Date());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        output.setWarehouse(optionalWarehouse.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        output.setCurrency(optionalCurrency.get());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        output.setClient(optionalClient.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);

        List<OutputProductDto> outputProductDtoList = outputDto.getOutputProductDtoList();
        
        List<OutputProduct>outputProductList = new ArrayList<>();

        for (OutputProductDto outputProductDtofor : outputProductDtoList) {
            OutputProduct outputProduct = new OutputProduct();
            Optional<Product> optionalProduct = productRepository.findById(outputProductDtofor.getProductId());
            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setPrice(outputProductDtofor.getPrice());
            outputProduct.setAmount(outputProductDtofor.getAmount());
            outputProduct.setOutput(output);
            outputProductList.add(outputProduct);
        }
        output.setOutputProductList(outputProductList);
outputRepository.save(output);
return  new ApiResponse("Saved",true,output);
    }

    public ApiResponse getById(Integer id) {
        Optional<Output> optionalOutputId = outputRepository.findById(id);
        return  new ApiResponse("Id = ",true,optionalOutputId.get());
    }

    public ApiResponse aditById(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutputId = outputRepository.findById(id);
        if (optionalOutputId.isPresent()) {
            return new ApiResponse("Not Found 404",false);
        }
        Output editIdOutput = optionalOutputId.get();
        editIdOutput.setFactureNumber(outputDto.getFactureNumber());
        Output save = outputRepository.save(editIdOutput);
        return new ApiResponse("Edited",true,save);

    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page,size);

        Page<Output> outputs = outputRepository.findAll(pageable);
        Page<ResOutputDto>resOutputDtoPage = new PageImpl<>(
                outputs.getContent().stream().map(this::getResOutput).collect(Collectors.toList()),
                outputs.getPageable(),
                outputs.getTotalElements()

        );

        return new ApiResponse("Mana",true,outputs);
    }
    public ResOutputDto getResOutput(Output output){

        return new ResOutputDto(
                output.getDate(),
                output.getWarehouse().getName(),
                output.getCurrency().getName(),
                output.getFactureNumber(),
                output.getOutputProductList().stream().map(this::getResOutputProductDto).collect(Collectors.toList())

        );
    }
    public RestOutputProductDto getResOutputProductDto(OutputProduct outputProduct){

        return new RestOutputProductDto(
                outputProduct.getProduct().getName(),
                outputProduct.getAmount(),
                outputProduct.getPrice()
        );
    }

    public ApiResponse getAllFromTo(String from, String to) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = dateFormat.parse(from);
        Date toDate = dateFormat.parse(to);
        List<Output> allByDateBetween = outputRepository.findAllByDateBetween(fromDate, toDate);

        List<ResOutputDto> collect = allByDateBetween.stream().map(this::getResOutput).collect(Collectors.toList());

        return new ApiResponse("---",true,collect);
        
    }

    public ApiResponse getAllSearchType(String type, String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = dateFormat.parse(date);
        Date toDate = dateFormat.parse(date);
        List<Output> allByDate = new ArrayList<>();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fromDate);
        if (type.equals("daily")){
            allByDate = outputRepository.findAllByDate(fromDate);

        }else if (type.equals("wekly")){
            calendar.add(Calendar.DATE, 7);
            toDate = calendar.getTime();
            allByDate = outputRepository.findAllByDateBetween(fromDate, toDate);

        }else {
            calendar.add(Calendar.MONTH,1);
            toDate = calendar.getTime();
            allByDate = outputRepository.findAllByDateBetween(fromDate, toDate);
        }
        List<ResOutputDto> collect = allByDate.stream().map(this::getResOutput).collect(Collectors.toList());
        return new ApiResponse("Mana",true,collect);
    }
}
