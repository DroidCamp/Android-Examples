package xyz.hemangkumar.firebaseapp;

/**
 * Created by Hemang on 02/09/16.
 */
public class Datum{
    String title;
    String venue;
    String start_date;
    String end_date;
    String details;
    String picture;

    public Datum() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Datum(String title, String venue, String start_date, String end_date, String details, String picture) {

        this.title = title;
        this.venue = venue;
        this.start_date = start_date;
        this.end_date = end_date;
        this.details = details;
        this.picture = picture;
    }
}
