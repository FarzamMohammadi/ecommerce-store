package com.farzammohammadi_imanetahri_comp303_assigment04.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path= "products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "index";
    }

    @GetMapping("/addReview/{id}")
    public ModelAndView addNewReviewRedirect(@PathVariable("id") Long productId, Model model) {
        String addNewReviewURL = "http://localhost:8090/reviews/addNewReview/" +productId;
        return new ModelAndView("redirect:" + addNewReviewURL);
    }

    @GetMapping("/getReviews/{id}")
    public ModelAndView viewAllReviewsRedirect(@PathVariable("id") Long productId, Model model) {
        String addNewReviewURL = "http://localhost:8090/reviews/listReviews/" +productId;
        return new ModelAndView("redirect:" + addNewReviewURL);
    }

    @GetMapping("/listAll")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "list";
    }

    @GetMapping("/addNewProduct")
    public String addNewProductFrom(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }
    @PostMapping("/addNewProduct")
    public String addNewProductSubmit(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if(result.hasErrors()){
            getErrorPage();
        }

        addNewProduct(product);
        return getProducts(model);
    }

    @GetMapping()
    private String getErrorPage() {
        return "error";
    }


    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "list";
    }

    @PutMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        productService.updateProduct(product);
        return "list";
    }
}
