package repository;
import domain.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.IReservationRepo;
import utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReservationRepo implements IReservationRepo {
    private jdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();


    public ReservationRepo(Properties props) {
        logger.info("Initializing ReservationDBRepository with properties: {} ",props);
        dbUtils=new jdbcUtils(props);
    }

    @Override
    public List<Reservation> findAll() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Reservation> ress = new ArrayList<>();
        try {
            String command = "select * from Reservation";
            PreparedStatement statement = connection.prepareStatement(command);
            try(ResultSet result = statement.executeQuery()){

                while (result.next()) {
                    String id = result.getString("id");
                    String id_trip = result.getString("id_trip");
                    String name = result.getString("name");
                    String phone_number = result.getString("phone_number");
                    int no_tickets = result.getInt("no_tickets");
                    Reservation res = new Reservation(id, id_trip,name,phone_number,no_tickets);
                    ress.add(res);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: FindAll");
        }

        logger.traceExit();
        if (ress.getFirst() == null)
            return null;
        return ress;
    }

    @Override
    public void add(Reservation elem) {
        logger.traceEntry("saving task{}", elem);
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preStmt = connection.prepareStatement("insert into " +
                "Reservation(id, trip, name," +
                " phone_number, no_tickets) values (?,?,?,?)")) {
            preStmt.setString(1,elem.getRezervation_id());
            preStmt.setObject(2,elem.getId_trip());
            preStmt.setString(3,elem.getName());
            preStmt.setString(4,elem.getPhone_number());
            preStmt.setInt(5,elem.getNo_tickets());
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }

    @Override
    public void update(String s, int x) {

    }

    @Override
    public void delete(String id) {
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Reservation WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }
}
