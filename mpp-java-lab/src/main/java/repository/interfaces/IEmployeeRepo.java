package repository.interfaces;

import domain.Employee;

public interface IEmployeeRepo extends Repository<String, Employee>{
    public boolean user_validation(String login_user, String login_pswd);
}
