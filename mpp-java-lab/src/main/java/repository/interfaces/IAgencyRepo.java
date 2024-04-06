package repository.interfaces;

import domain.Agency;

public interface IAgencyRepo extends Repository<String, Agency> {
    public String getName(String id);
}
