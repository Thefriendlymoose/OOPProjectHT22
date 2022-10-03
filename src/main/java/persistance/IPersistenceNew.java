package persistance;

import java.util.List;
import java.util.Map;

//@todo refaktorisera namnet senare

public interface IPersistenceNew<T> {
    void Save(T o); // Svara med boolean?
    List<T> getAll();
    Map<Long, T> getAllMap(); // K,V?
    long getNextId();
    T findById(long id);

}
