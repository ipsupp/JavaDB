package repository;
import domain.Agency;
import domain.TCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.ITCompanyRepo;
import utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TCompanyRepo implements ITCompanyRepo{
    private jdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();


    public TCompanyRepo(Properties props) {
        logger.info("Initializing TCompanyDBRepository with properties: {} ",props);
        dbUtils=new jdbcUtils(props);
    }

    @Override
    public List<TCompany> findAll() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<TCompany> comps = new ArrayList<>();
        try {
            String command = "select * from TCompany";
            PreparedStatement statement = connection.prepareStatement(command);
            try(ResultSet result = statement.executeQuery()){

                while (result.next()) {
                    String id = result.getString("id");
                    String company_name = result.getString("company_name");
                    TCompany com = new TCompany(id, company_name);
                    comps.add(com);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: FindAll");
        }

        logger.traceExit();
        if (comps.getFirst() == null)
            return null;
        return comps;
    }

    @Override
    public void add(TCompany elem) {
        logger.traceEntry("saving task{}", elem);
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preStmt = connection.prepareStatement("insert into " +
                "TCompany(id, company_name) values (?,?)")) {
            preStmt.setString(1,elem.getId());
            preStmt.setString(2,elem.getCompany_name());
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

    public void update(String integer, TCompany elem) {
        Connection connection = dbUtils.getConnection();
        try{
            String command = "UPDATE TCompany SET company_name WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,elem.getId());
            statement.setString(2, integer);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(String id) {
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM TCompany WHERE id = ?")) {
            statement.setString(1, id);
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
            String command = "select * from TCompany where id=";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,id);
            try(ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    String company_name = result.getString("company_name");
                    return company_name;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: getName");
        }

        logger.traceExit();
        return null;
    }
}
