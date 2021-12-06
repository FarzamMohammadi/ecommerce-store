package com.farzammohammadi_imanetahri_comp303_assignment04.reviewservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getById(id);
    }
    public List<List<Review>> findByProductId(Long productId) {
        Optional<List<Review>> reviewForProduct = reviewRepository.findByProductId(productId);

        if (reviewForProduct.isEmpty()){
            throw new IllegalStateException("There are no review for this product.");
        }
        else{
            return reviewForProduct.stream().toList();
        }
    }
    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }

    public void addNewReview(Review review) {
        Optional<List<Review>> reviewForProduct = reviewRepository.findByProductId(review.getProductId().longValue());

        if (reviewForProduct.isEmpty()){
            throw new IllegalStateException("There are no review for this product.");
        }
        else{
            reviewRepository.save(review);
        }
    }
    public void updateReview(Review review) {
        Review reviewToUpdate = getReviewById(review.getId());

        if (reviewToUpdate.getComment() != null){
            reviewToUpdate.setComment(review.getComment());
            reviewToUpdate.setReviewerName(review.getReviewerName());
            reviewToUpdate.setRating(review.getRating());
            reviewRepository.save(reviewToUpdate);
        }
        else{
            throw new IllegalStateException("Review does not exist.");
        }
    }
}
