package com.sudharsan.reviewms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all reviews for a specific company
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    // Get a specific review by ID (companyId is not strictly required)
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    // Add a new review for a company
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(companyId, review));
    }

    // Update an existing review
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long reviewId,
            @RequestBody Review review) {
        boolean updated = reviewService.updateReview(reviewId, review);
        if (updated) {
            return ResponseEntity.ok("Review updated successfully");
        }
        return ResponseEntity.badRequest().body("Review not found");
    }

    // Delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted) {
            return ResponseEntity.ok("Review deleted successfully");
        }
        return ResponseEntity.badRequest().body("Review not found");
    }
}
