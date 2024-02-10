package com.example.Microservices.ProductService.Controller;

import com.example.Microservices.ProductService.Model.ProductResponse;
import com.example.Microservices.ProductService.Services.ProductServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Microservices.ProductService.Model.ProductRequest;
import com.example.Microservices.ProductService.Services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest requestBody) {

        ProductResponse productResponse = new ProductResponse();
        productResponse = productService.addProduct(requestBody);

        return ResponseEntity.ok(productResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        ProductResponse response = productService.getProductsById(id);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {

        List<ProductResponse> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long id, @RequestParam long quantity) {


        productService.reductQuantity(id, quantity);

        return new ResponseEntity<>(HttpStatus.OK);


    }


}
