package persistence;

import java.util.List;
import java.util.Map;

/**
 * Interface for storage and retrieval of business object data to and from json files
 * @param <T> Business object class to be stored on disk
 */
public interface IPersistence<T> {
    void save(List<T> items); // Svara med boolean?
    Map<Long, T> getAllMap(); // K,V?

}
