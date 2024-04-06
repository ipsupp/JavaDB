package repository;
import domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.IEmployeeRepo;
import utils.jdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class EmployeeRepo implements IEmployeeRepo{

    private jdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();


    public EmployeeRepo(Properties props) {
        logger.info("Initializing EmployeeDBRepository with properties: {} ",props);
        dbUtils=new jdbcUtils(props);
    }

    public boolean user_validation(String login_user, String login_pswd){
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        try {
            String command = "select * from Employee where login_user=?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,login_user);
            try(ResultSet result = statement.executeQuery()){

                while (result.next()) {
                    String login_userdb = result.getString("login_user");
                    String login_pswddb = result.getString("login_pswd");
                    if (Objects.equals(login_pswddb, login_pswd) & Objects.equals(login_userdb, login_user)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: Validate user.");
        }
    }

    @Override
    public List<Employee> findAll() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Employee> emps = new ArrayList<>();
        try {
            String command = "select * from Employee";
            PreparedStatement statement = connection.prepareStatement(command);
            try(ResultSet result = statement.executeQuery()){

                while (result.next()) {
                    String id = result.getString("id");
                    String id_agency = result.getString("id_agency");
                    String login_user = result.getString("login_user");
                    String login_pswd = result.getString("login_pswd");
                    Employee emp = new Employee(id,id_agency,login_user,login_pswd);
                    emp.setId(id);
                    emps.add(emp);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Error: FindAll");
        }

        logger.traceExit();
        if (emps.getFirst() == null)
            return null;
        return emps;
    }

    @Override
    public void add(Employee elem) {
        logger.traceEntry("saving task{}", elem);
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preStmt = connection.prepareStatement("insert into Employee(id,id_agency," +
                "login_user,login_pswd) values (?,?,?,?)")) {
            preStmt.setString(1,elem.getId());
            preStmt.setString(2,elem.getId_agency());
            preStmt.setString(3,elem.getLogin_user());
            preStmt.setString(4,elem.getLogin_pswd());
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

    public void update(String integer, Employee elem) {
        Connection connection = dbUtils.getConnection();
        try{
            //nu folosim
            String command = "UPDATE Employee SET login_pswd=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1,elem.getLogin_pswd());
            statement.setString(2, integer);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(String id) {
        //nu folosim
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.traceExit();
    }

}
