package model;

import java.util.Date;
import java.util.List;

public class Review {
    private int customerId;
    private int productId;
    private int rating; // out of 5
    private String comment;
    private Date date;

    public static double getAverageRating(List<Review> reviews) {
        return reviews.stream().mapToInt(r -> r.rating).average().orElse(0.0);
    }
}
