package com.farzammohammadi_imanetahri_comp303_assignment04.reviewservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

//@RestController
@Controller
@RequestMapping(path= "reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/listReviews/{id}")
    public String getReviews(@PathVariable("id") Long productId, Model model) {
        List<List<Review>> reviewsToShow = reviewService.findByProductId(productId);
        List<Review> productReviews = null;
        if(reviewsToShow.size() == 1){
            productReviews = reviewsToShow.get(0);
            model.addAttribute("reviews", productReviews);
        }

        return "productReviews";
    }

    @GetMapping("/addNewReview/{id}")
    public String addNewReviewFrom(@PathVariable("id") Long productId, Model model) {
        Review newReview = new Review();
        newReview.setProductId(productId);
        model.addAttribute("review", newReview);
        return "add";
    }
    @PostMapping("/addNewReview")
    public ModelAndView addNewReviewSubmit(@ModelAttribute @Valid Review review, BindingResult result, Model model) {
        if(result.hasErrors()){
            getErrorPage();
        }

        addNewReview(review);
        String listAllURL = "http://localhost:8099/products/listAll";
        return new ModelAndView("redirect:" + listAllURL);
    }
    @GetMapping()
    private String getErrorPage() {
        return "error";
    }


    @DeleteMapping("/deleteReview/{id}")
    public void deleteReview(@PathVariable("id") Long productId){
        reviewService.deleteReview(productId);
    }

    @PutMapping("/updateProduct/{id}")
    public ModelAndView updateProduct(@PathVariable("id") Long productId){
        Review review = reviewService.getReviewById(productId);
        reviewService.updateReview(review);
        String listAllURL = "http://localhost:8099/products/listAll";
        return new ModelAndView("redirect:" + listAllURL);
    }

    @PostMapping
    public void addNewReview(@RequestBody Review review){
        reviewService.addNewReview(review);
    }
}