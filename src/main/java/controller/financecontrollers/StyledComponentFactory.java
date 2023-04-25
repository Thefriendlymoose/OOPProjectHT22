package controller.financecontrollers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Responsibility: provide functionality to easily create JavaFX components with a style from
 *                 "stylesheet.css"
 * Uses: Button, Label, VBox
 * Used by: FinanceMainController, SiteFinanceController
 * */

public class StyledComponentFactory {

    public static Button getStyledButton(){
        return getStyledButton("");
    }

    /**
     * Returns a button with the "generic-button" style
     * @param label what is written on the button
     * */
    public static Button getStyledButton(String label){
        Button b = new Button(label);
        b.getStyleClass().add("generic-button");
        return b;
    }

    /**
     * Returns a label with the "generic-form-label" style
     * @param labelText what is written on the label
     * */
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
