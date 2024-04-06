package repository.interfaces;

import java.util.List;

public interface Repository<ID,T> {
    void add(T elem);
    void update(ID id, int x);
    void delete(ID id);
    List<T> findAll();
}
