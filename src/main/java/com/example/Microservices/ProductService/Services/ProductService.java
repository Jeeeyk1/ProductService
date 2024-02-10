package com.example.Microservices.ProductService.Services;

import com.example.Microservices.ProductService.CustomException.ProductCustomException;
import com.example.Microservices.ProductService.Model.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservices.ProductService.Entity.ProductEntity;
import com.example.Microservices.ProductService.Model.ProductRequest;
import com.example.Microservices.ProductService.Repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class ProductService implements ProductServiceImpl {


    @Autowired
    ProductRepository repository;

    @Override
    public ProductResponse addProduct(ProductRequest requestBody) {

        ProductEntity product = new ProductEntity().builder().productName(requestBody.getProductName()).price(requestBody.getPrice()).quantity(requestBody.getQuantity()).build();
        System.out.println(requestBody.getProductName() + "request body \n" + product);
        repository.save(product);
        ProductResponse response = new ProductResponse().builder().productName(requestBody.getProductName()).price(requestBody.getPrice()).quantity(requestBody.getQuantity()).productId(product.getProductId()).build();
        log.info("Product Created Sucessfully");
        return response;
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> foundProducts = repository.findAll();
        List<ProductResponse> response = new ArrayList<>();


        BeanUtils.copyProperties(foundProducts, response);
        for (ProductEntity convertToResponse : foundProducts) {
            ProductResponse productResponse = new ProductResponse()
                    .builder()
                    .productId(convertToResponse.getProductId())
                    .productName(convertToResponse.getProductName())
                    .quantity(convertToResponse.getQuantity())
                    .price(convertToResponse.getPrice())
                    .build();
            response.add((productResponse));
        }

        System.out.println(foundProducts);


        return response;


    }


    public ProductResponse getProductsById(Long id) {

        ProductEntity responseFromJPA = repository.findById(id).orElseThrow(() -> new ProductCustomException("Product with given id is not found", "PRODUCT_NOT_FOUND"));
        ProductResponse response = new ProductResponse();
                    BeanUtils.copyProperties(responseFromJPA, response);
        return response;
    }

    public void reductQuantity(long id, long quantity) {

        ProductEntity foundProduct = repository.findById(id).orElseThrow(() -> new ProductCustomException("Product not found!", "NOT_FOUND"));


        if (foundProduct.getQuantity() > quantity) {
            foundProduct.setQuantity(foundProduct.getQuantity() - quantity);

            log.info("Successfully reduced quantity");

            repository.save(foundProduct);

        } else {
            throw new ProductCustomException("Product's quantity is insuficient", "INSUFICIENT_QUANTITY");
        }



    }
}
