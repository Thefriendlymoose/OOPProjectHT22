import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowTest extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("WMS");
        MenuBar menuBar = new MenuBar();
        BorderPane borderPane = new BorderPane();

        VBox vBox = new VBox(menuBar);

        Menu file = new Menu("File");
        MenuItem add = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem logIn = new MenuItem("Log In");
        MenuItem logOut = new MenuItem("Log Out");
        MenuItem info = new MenuItem("Info");

        file.getItems().add(add);
        file.getItems().add(delete);
        file.getItems().add(separator);
        file.getItems().add(logIn);
        file.getItems().add(logOut);
        file.getItems().add(info);

        Menu edit = new Menu("Edit");
        Menu view = new Menu("View");
        Menu help = new Menu("Help");

        menuBar.getMenus().add(file);
        menuBar.getMenus().add(edit);
        menuBar.getMenus().add(view);
        menuBar.getMenus().add(help);

        TestForm testForm = new TestForm();

        borderPane.setTop(vBox);
        borderPane.setCenter(testForm);
        Scene scene = new Scene(borderPane, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}
