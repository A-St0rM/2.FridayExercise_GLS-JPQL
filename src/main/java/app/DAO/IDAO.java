package app.DAO;

import java.util.List;

public interface IDAO<T>{

    T findById(int id);
    List<T> findAll();
    void save(T t);
    void delete(T t);
    T update(T t);
}
