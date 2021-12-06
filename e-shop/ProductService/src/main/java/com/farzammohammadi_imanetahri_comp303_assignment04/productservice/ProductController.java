package com.farzammohammadi_imanetahri_comp303_assignment04.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path= "products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/listAll")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products/list";
    }

    @GetMapping("/addNewProduct")
    public String addNewProductFrom(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", new Product());
        return "products/add";
    }
    @PostMapping("/addNewProduct")
    public String addNewProductSubmit(@ModelAttribute Product product, Model model) {
        addNewProduct(product);
        return getProducts(model);
    }


    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "products/list";
    }

    @PutMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        productService.updateProduct(product);
        return "products/list";
    }
}
