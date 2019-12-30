package com.shinwari.registertion.Model;

import java.io.Serializable;



public class DonatorInfo implements Serializable {


    String donatorName;
    String donatorEmail;
    String donatorImage;
    String donatorCell;
    String donatorAge;
    String donatorDiseases;
    String donatorLocation;
    String donaterSpecilization;
    float donatorRatingSum;
    int donatorNoCount;


    public DonatorInfo() {
    }

    public DonatorInfo(String donatorName, String donatorEmail, String donatorImage, String donatorCell, String donatorAge, String donatorDiseases, String donatorLocation, String donaterSpecilization, float donatorRatingSum, int donatorNoCount) {
        this.donatorName = donatorName;
        this.donatorEmail = donatorEmail;
        this.donatorImage = donatorImage;
        this.donatorCell = donatorCell;
        this.donatorAge = donatorAge;
        this.donatorDiseases = donatorDiseases;
        this.donatorLocation = donatorLocation;
        this.donaterSpecilization = donaterSpecilization;
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

    public String getDonatorAge() {
        return donatorAge;
    }

    public void setDonatorAge(String donatorAge) {
        this.donatorAge = donatorAge;
    }

    public String getDonatorDiseases() {
        return donatorDiseases;
    }

    public void setDonatorDiseases(String donatorDiseases) {
        this.donatorDiseases = donatorDiseases;
    }

    public String getDonatorLocation() {
        return donatorLocation;
    }

    public void setDonatorLocation(String donatorLocation) {
        this.donatorLocation = donatorLocation;
    }

    public String getDonaterSpecilization() {
        return donaterSpecilization;
    }

    public void setDonaterSpecilization(String donaterSpecilization) {
        this.donaterSpecilization = donaterSpecilization;
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
