package controller.orderControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.site.Site;
import persistance.IPersistance;
import persistance.JSONDao;

import java.util.List;

public class OrderCreateModalController {

    @FXML
    private Button continueButton;

    @FXML
    private ListView<Site> siteListView;

    private Site current;
    private IPersistance jsonDao = new JSONDao();

    public void initialize(){

        siteListView.getItems().addAll(jsonDao.loadAllSites());

        siteListView.setCellFactory(param -> new ListCell<Site>(){
            @Override
            protected void updateItem(Site s, boolean empty){
                super.updateItem(s, empty);

                if(empty || s == null || s.getSiteName() == null){
                    setText(null);
                } else {
                    setText(s.getSiteName());
                }
            }
        });


        siteListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
            current = siteListView.getSelectionModel().getSelectedItem();
            System.out.println(current.getSiteName());
        });
    }

}
