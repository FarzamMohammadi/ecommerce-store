package com.farzammohammadi_imanetahri_comp303_assignment04.reviewservice;

import javax.persistence.*;

@Entity
@Table
public class Review {
    @Id
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence"
    )

    private Long id;
    private String reviewerName;
    private String comment;
    private Integer rating;
    private Long productId;

    public Review(){}

    public Review(Long id, String reviewerName, String comment, Integer rating, Long productId){
        this.id = id;
        this.reviewerName = reviewerName;
        this.comment = comment;
        this.rating = rating;
        this.productId = productId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Review(String reviewerName, String comment, Integer rating, Long productId){
        this.reviewerName = reviewerName;
        this.comment = comment;
        this.rating = rating;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getProductId() {
        return productId;
    }
}
