package persistance;

import java.util.List;
import java.util.Map;

//@todo refaktorisera namnet senare

public interface IPersistenceNew<T> {
    void Save(T o);
    List<T> getAll();
    Map<Long, T> getAllMap();
    long getNextId(T o);
    T findById(long id);
}
