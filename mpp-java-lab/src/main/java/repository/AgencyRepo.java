package repository;

import domain.Agency;
import utils.jdbcUtils;
import org.apache.logging.log4j.LogManager;
import repository.interfaces.IAgencyRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AgencyRepo implements IAgencyRepo {

    private jdbcUtils dbUtils;
    private static Logger logger= LogManager.getLogger();


    public AgencyRepo(Properties props) {
        logger.info("Initializing AgencyDBRepository with properties: {} ",props);
        dbUtils=new jdbcUtils(props);
    }

    @Override
    public List<Agency> findAll() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Agency> agencies = new ArrayList<>();
        try {
            String command = "select * from Agency";
            PreparedStatement statement = connection.prepareStatement(command);
            try(ResultSet result = statement.executeQuery()){

                while (result.next()) {
                    String id = result.getString("id");
                    String agency_name = result.getString("agency_name");
                    Agency agency = new Agency(id,agency_name);
                    agency.setId(id);
                    agencies.add(agency);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: FindAll");
        }

        logger.traceExit();
        if (agencies.getFirst() == null)
            return null;
        return agencies;
    }

    @Override
    public void add(Agency elem) {
        logger.traceEntry("saving task{}", elem);
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preStmt = connection.prepareStatement("insert into Agency(agency_name) values (?)")) {
            preStmt.setString(1,elem.getAgency_name());
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
        // nu e nev
    }

   /* @Override
    public void update(String integer, Agency elem) {
        Connection connection = dbUtils.getConnection();
        try{
            String command = "UPDATE Agency SET agency_name=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,elem.getAgency_name());
            statement.setString(2, elem.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }*/

    @Override
    public void delete(String s) {
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Agency WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(s));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }

    @Override
    public String getName(String id) {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        try {
            String command = "select * from Agency where id=?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,id);
            try(ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    String agency_name = result.getString("agency_name");
                    return agency_name;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println(dbUtils.getConnection());
            throw new RuntimeException("Error: getNameAjutor");
        }

        logger.traceExit();
        return null;
    }
}