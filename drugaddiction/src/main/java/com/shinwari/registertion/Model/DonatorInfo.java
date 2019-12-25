package com.shinwari.registertion.Model;

import java.io.Serializable;



public class DonatorInfo implements Serializable {


    String donatorName;
    String donatorEmail;
    String donatorImage;
    String donatorCell;
    String donatorLocation;
    float donatorRatingSum;
    int donatorNoCount;


    public DonatorInfo() {
    }

    public DonatorInfo(String donatorName, String donatorEmail, String donatorImage, String donatorCell, String donatorLocation, float donatorRatingSum, int donatorNoCount) {
        this.donatorName = donatorName;
        this.donatorEmail = donatorEmail;
        this.donatorImage = donatorImage;
        this.donatorCell = donatorCell;
        this.donatorLocation = donatorLocation;
        this.donatorRatingSum = donatorRatingSum;
        this.donatorNoCount = donatorNoCount;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public String getDonatorEmail() {
        return donatorEmail;
    }

    public void setDonatorEmail(String donatorEmail) {
        this.donatorEmail = donatorEmail;
    }

    public String getDonatorImage() {
        return donatorImage;
    }

    public void setDonatorImage(String donatorImage) {
        this.donatorImage = donatorImage;
    }

    public String getDonatorCell() {
        return donatorCell;
    }

    public void setDonatorCell(String donatorCell) {
        this.donatorCell = donatorCell;
    }

    public String getDonatorLocation() {
        return donatorLocation;
    }

    public void setDonatorLocation(String donatorLocation) {
        this.donatorLocation = donatorLocation;
    }

    public float getDonatorRatingSum() {
        return donatorRatingSum;
    }

    public void setDonatorRatingSum(float donatorRatingSum) {
        this.donatorRatingSum = donatorRatingSum;
    }

    public int getDonatorNoCount() {
        return donatorNoCount;
    }

    public void setDonatorNoCount(int donatorNoCount) {
        this.donatorNoCount = donatorNoCount;
    }
}
