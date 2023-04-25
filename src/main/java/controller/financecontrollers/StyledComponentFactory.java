package controller.financecontrollers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StyledComponentFactory {


    public static Button getStyledButton(){
        return getStyledButton("");
    }

    public static Button getStyledButton(String label){
        Button b = new Button(label);
        b.getStyleClass().add("generic-button");
        return b;
    }

    public static Label getStyledLabel(String labelText){
        Label l = new Label(labelText);
        l.getStyleClass().add("generic-form-label");
        return l;
    }

    public static VBox getStyledVBox(){
        VBox vb = new VBox();
        vb.getStyleClass().add("sub-menu.box");
        return vb;
    }
}
