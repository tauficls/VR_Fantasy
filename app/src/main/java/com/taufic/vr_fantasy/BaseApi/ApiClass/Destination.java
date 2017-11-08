package com.taufic.vr_fantasy.BaseApi.ApiClass;

/**
 * Created by taufic on 11/8/2017.
 */

public class Destination {
    public String address;
    public String booking;
    public String image;
    public String link;
    public String name;
    public Double longitude;
    public Double latitude;

    public Destination() {

    }

    public Destination(String address, String booking, String image, String link, String name, Double longitude, Double latitude) {
        this.address = address;
        this.booking = booking;
        this.image = image;
        this.link = link;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
