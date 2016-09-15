package xyz.hemangkumar.rnfapp.models;
/**
 * Created by Hemang on 02/09/16.
 */
public class Workshop{
    String title;
    String venue;
    String workshop_date;
    String workshop_time;
    String details;
    String category;
    String organiser;

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Workshop(String title, String venue, String workshop_date, String workshop_time, String details, String category, String organiser, String contact) {
        this.title = title;
        this.venue = venue;
        this.workshop_date = workshop_date;
        this.workshop_time = workshop_time;
        this.details = details;
        this.category = category;
        this.organiser = organiser;
        this.contact = contact;
    }

    String contact;

    public Workshop() {
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

    public String getWorkshop_date() {
        return workshop_date;
    }

    public void setWorkshop_date(String workshop_date) {
        this.workshop_date = workshop_date;
    }

    public String getWorkshop_time() {
        return workshop_time;
    }

    public void setWorkshop_time(String workshop_time) {
        this.workshop_time = workshop_time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Workshop(String title, String venue, String workshop_date, String workshop_time, String details, String category) {

        this.title = title;
        this.venue = venue;
        this.workshop_date = workshop_date;
        this.workshop_time = workshop_time;
        this.details = details;
        this.category = category;
    }
}
