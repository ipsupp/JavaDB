package service;

import domain.Reservation;
import domain.Trip;
import repository.*;

import java.sql.SQLException;

public class Service {
    private AgencyRepo agency_repo;
    private EmployeeRepo employee_repo;
    private TCompanyRepo company_repo;
    private TripRepo trip_repo;
    private ReservationRepo res_repo;

    public Service(AgencyRepo agency_repo, EmployeeRepo employee_repo,
                   TCompanyRepo company_repo, TripRepo trip_repo, ReservationRepo res_repo) {
        this.agency_repo = agency_repo;
        this.employee_repo = employee_repo;
        this.company_repo = company_repo;
        this.trip_repo = trip_repo;
        this.res_repo = res_repo;
    }

    public boolean validate_login(String login_user, String login_pswd){
        return employee_repo.user_validation(login_user,login_pswd);
    }

    public boolean confirm_reservation(Reservation ag) throws SQLException {
        if (trip_repo.ticketsPerTripId(ag.getId_trip()) >= ag.getNo_tickets()){
            return true;
        }
        return false;
    }
    public void add_reservation(String id, String id_trip, String name,
                                String phone_number, int no_tickets){
        Reservation ag = new Reservation(id,id_trip,name,phone_number,no_tickets);
        try {
            if (trip_repo.ticketsPerTripId(ag.getId_trip()) >= ag.getNo_tickets()){
                res_repo.add(ag);
                trip_repo.updateTicketsTripId(trip_repo.ticketsPerTripId(
                        ag.getId_trip()) - ag.getNo_tickets(), ag.getId_trip());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Trip> get_all_trips(){
        return trip_repo.findAll();
    }
    public Iterable<Trip> get_all_trips_by_location(String location, int depTimeStart, int depTimeEnd){
        return trip_repo.find_by_location_time(location,depTimeStart,depTimeEnd);
    }




}
