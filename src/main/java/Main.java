import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistance.*;


public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(OrderDAO.getInstance().getAll());
        System.out.println(CustomersDAO.getInstance().getAll());
        System.out.println(ArticlesDAO.getInstance().getAll());
        System.out.println(SitesDAO.getInstance().getAll());
        System.out.println(UserDAO.getInstance().getAll());

        Parent root = FXMLLoader.load(getClass().getResource("fxml/template.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("WMS");
        stage.setScene(scene);
        stage.show();
    }
}
