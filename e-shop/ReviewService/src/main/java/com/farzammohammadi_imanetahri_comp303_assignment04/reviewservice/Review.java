package com.farzammohammadi_imanetahri_comp303_assignment04.reviewservice;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

//    Instead of using -> @GeneratedValue(strategy = GenerationType.AUTO) the code above retrieves appropriate ID number and is never blank
    private Long id;
    @NotEmpty
    @Size(max = 30, min = 3, message = "Enter your name.")
    private String reviewerName;
    private String comment;
    @NotNull
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
