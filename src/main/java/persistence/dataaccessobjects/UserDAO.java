package persistence.dataaccessobjects;

import com.google.gson.Gson;
import model.user.User;
import persistence.IPersistence;
import persistence.SerializeBuilder;
import persistence.WriterHelper;
import persistence.pojos.UserJSON;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Data Access Object which handles loading/saving user data from/to JSON
 */
public class UserDAO implements IPersistence<User> {

    private static UserDAO instance;
    private final String file = "src/main/resources/users.json";
    private Map<Long, User> users;
    private Gson gson = new Gson();

    private UserDAO(){
        this.users = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(Path.of(file));
            List<UserJSON> userJSONS = Arrays.asList(gson.fromJson(reader, UserJSON[].class));

            for (UserJSON uj : userJSONS){
                User user = new User(uj.getUserId(), uj.getUserName(), uj.getPassword(), uj.getName(), uj.isStatus(), uj.getPermissions());
                users.put(uj.getUserId(), user);
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

    /**
     * Serializes and saves a list of users into a JSON file
     * @param list
     */
    @Override
    public void save(List<User> list) {
        SerializeBuilder sb = new SerializeBuilder();
        WriterHelper<User> wh = new WriterHelper<>();
        wh.writeToFileSerializer(file, list, sb.getGson());
    }


    @Override
    public Map<Long, User> getAllMap() {
        return users;
    }

}
