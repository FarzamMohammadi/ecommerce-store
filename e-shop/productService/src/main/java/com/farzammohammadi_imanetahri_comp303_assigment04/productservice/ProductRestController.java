package com.farzammohammadi_imanetahri_comp303_assigment04.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RestController
@RequestMapping(path= "api/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/listAll")
    public List<Product> getProducts() {
        return productService.getProducts();
    }


    @PostMapping("/addProduct")
    public String addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return "Product Added";
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "Product Deleted";
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody Product productToUpdate){
        productService.updateProduct(productToUpdate);
        return "Product Updated";
    }
}