import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TestForm extends GridPane {

    public TestForm(){

        DataBaseAdapter testAdapter = new DataBaseAdapter(DataBaseConnection.getInstance());

        this.setAlignment(Pos.CENTER);

        this.setStyle("-fx-border-style: solid inside;"+
                      "-fx-border-width: 2;"+
                      "-fx-border-color: #000000");

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25,25,25,25));
        this.add(new Text("Welcome"), 0,0,2,1);
        this.add(new Label("Name:"),0,1);
        TextField name = new TextField();
        this.add(name,1,1);

        this.add(new Label("Age:"), 0,2);
        TextField age = new TextField();
        this.add(age,1,2);

        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        this.add(hbBtn,1,4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                testAdapter.createTestUser(new TestUser(name.getText(), age.getText()));
                name.clear();
                age.clear();
            }
        });
    }
}
