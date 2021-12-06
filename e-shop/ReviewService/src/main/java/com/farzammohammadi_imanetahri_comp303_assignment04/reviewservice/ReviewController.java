package com.farzammohammadi_imanetahri_comp303_assignment04.reviewservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "reviews/productReviews";
    }

    @GetMapping("/addNewReview/{id}")
    public String addNewReviewFrom(@PathVariable("id") Long productId, Model model) {
        Review newReview = new Review();
        newReview.setProductId(productId);
        model.addAttribute("review", newReview);
        return "reviews/add";
    }
    @PostMapping("/addNewReview")
    public String addNewReviewSubmit(@ModelAttribute Review review, Model model) {
        addNewReview(review);
        return "products/list";
    }


    @DeleteMapping("/deleteReview/{id}")
    public void deleteReview(@PathVariable("id") Long productId){
        reviewService.deleteReview(productId);
    }

    @PutMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Long productId){
        Review review = reviewService.getReviewById(productId);
        reviewService.updateReview(review);
        return "products/list";
    }

    @PostMapping
    public void addNewReview(@RequestBody Review review){
        reviewService.addNewReview(review);
    }
}