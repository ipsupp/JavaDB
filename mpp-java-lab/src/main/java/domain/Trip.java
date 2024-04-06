package domain;


public class Trip {
    protected String id;
    protected String id_agency;
    protected String id_company;
    public String location;
    public int departure_time;
    public float ticket_price;
    public int available_tickets;

    public Trip(String trip_id, String id_agency, String id_company, String location, int departure_time_hour, float ticket_price, int available_tickets) {
        this.id = trip_id;
        this.id_agency = id_agency;
        this.id_company = id_company;
        this.location = location;
        this.departure_time = departure_time_hour;
        this.ticket_price = ticket_price;
        this.available_tickets = available_tickets;
    }


    public String getId_agency() {
        return id_agency;
    }

    public void setId_agency(String id_agency) {
        this.id_agency = id_agency;
    }

    public String getId_company() {
        return id_company;
    }

    public void setId_company(String id_company) {
        this.id_company = id_company;
    }

    public int getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(int departure_time_hour) {
        this.departure_time = departure_time_hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency_id() {
        return id_agency;
    }

    public void setAgency_id(String id) {
        this.id_agency = id;
    }

    public String getCompany_id() {
        return id_company;
    }

    public void setCompany_id(String id) {
        this.id_company = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public float getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(float ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getAvailable_tickets() {
        return available_tickets;
    }

    public void setAvailable_tickets(int available_tickets) {
        this.available_tickets = available_tickets;
    }

    @Override
    public String toString() {
        return id + " " + location + " " +id_agency + " " + id_company + " " +
                departure_time  + " " + ticket_price + " " +
                available_tickets + '\n';
    }
}