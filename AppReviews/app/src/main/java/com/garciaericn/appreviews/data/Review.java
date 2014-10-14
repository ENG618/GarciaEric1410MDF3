package com.garciaericn.appreviews.data;

import android.os.Bundle;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/14/14.
 */
public class Review implements Serializable{
    public static final long serialVersionUID = 7595587912452158245L;

    // Constant fields for reference
    public static final String REVIEW_TITLE = "com.garciaericn.appreviews.REVIEWTITLE";
    public static final String STARS_EARNED = "com.garciaericn.appreviews.STARSEARNED";
    public static final String REVIEW_SUMMARY = "com.garciaericn.appreviews.REVIEWSUMMARY";
    public static final String REVIEW_KEY = "com.garciaericn.appreviews.REVIEWKEY";

    // Privet fields
    private String reviewTitle;
    private int starsEarned;
    private String reviewSummary;
    private String reviewKey;

    public Review(String title, int stars, String summary) {
        this.reviewTitle = title;
        this.starsEarned = stars;
        this.reviewSummary = summary;
        this.reviewKey = generateKey();
    }

    // Random key generator
    private String generateKey() {
        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(32);
    }

    // Getters and Setters
    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public int getStarsEarned() {
        return starsEarned;
    }

    public void setStarsEarned(int starsEarned) {
        this.starsEarned = starsEarned;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public String getReviewKey() {
        return reviewKey;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(REVIEW_TITLE, this.reviewTitle);
        bundle.putInt(STARS_EARNED, this.starsEarned);
        bundle.putString(REVIEW_SUMMARY, this.reviewSummary);
        bundle.putString(REVIEW_KEY, this.reviewKey);

        return bundle;
    }

    public Review(Bundle bundle) {
        if (bundle != null) {
            this.reviewTitle = bundle.getString(REVIEW_TITLE);
            this.starsEarned = bundle.getInt(STARS_EARNED);
            this.reviewSummary = bundle.getString(REVIEW_SUMMARY);
            this.reviewKey = bundle.getString(REVIEW_KEY);
        }
    }

}
