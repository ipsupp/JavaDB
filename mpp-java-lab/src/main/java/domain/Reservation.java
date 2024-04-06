package domain;


public class Reservation {
    private String id;
    private String id_trip;
    public String name;
    //  public String first_name;
    public String phone_number;
    public int no_tickets;

    public Reservation(String id, String id_trip,
                       String name, String phone_number,
                       int no_tickets) {
        this.id = id;
        this.id_trip = id_trip;
        this.name = name;
        this.phone_number = phone_number;
        this.no_tickets = no_tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_trip() {
        return id_trip;
    }

    public void setId_trip(String id_trip) {
        this.id_trip = id_trip;
    }

    public String getRezervation_id() {
        return id;
    }

    public void setRezervation_id(String rezervation_id) {
        this.id = rezervation_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getNo_tickets() {
        return no_tickets;
    }

    public void setNo_tickets(int no_tickets) {
        this.no_tickets = no_tickets;
    }

    @Override
    public String toString() {
        return id + " " + id_trip + " " + name +
                " " + no_tickets + '\n';
    }
}