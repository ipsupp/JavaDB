package repository.interfaces;

import domain.Trip;

import java.sql.SQLException;
import java.util.List;

public interface ITripRepo extends Repository<String, Trip>{
    public List<Trip> find_by_location_time(String loc, int min, int max);
    public int ticketsPerTripId(String id) throws SQLException;
    public Trip getTripById(String id);
}
