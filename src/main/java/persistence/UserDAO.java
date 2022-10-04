package persistence;

import com.google.gson.Gson;
import model.User;
import persistence.pojos.UserJSON;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class UserDAO implements IPersistence<User> {

    private static UserDAO instance;

    private final String file = "src/main/resources/users.json";
    private Map<Long, User> users;
    private Gson gson = new Gson();
    private long nextFreeId = 0;
    private UserDAO(){
        this.users = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(Path.of(file));
            List<UserJSON> userJSONS = Arrays.asList(gson.fromJson(reader, UserJSON[].class));

            for (UserJSON uj : userJSONS){
                User user = new User(uj.getUserId(), uj.getUserName(), uj.getPassword(), uj.getName(), uj.isStatus(), uj.getPermissions());
                users.put(uj.getUserId(), user);
            }
            if (users.size() > 0){
                nextFreeId = Collections.max(users.keySet()) + 1;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static UserDAO getInstance(){
        if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public void save(User o) {

    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Map<Long, User> getAllMap() {
        return users;
    }

    @Override
    public long getNextId() {
        return nextFreeId;
    }

    @Override
    public User findById(long id) {
        return users.get(id);
    }
}
