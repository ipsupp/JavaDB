package repository.interfaces;

import domain.TCompany;

public interface ITCompanyRepo extends Repository<String, TCompany> {
    public String getName(String id);
}
