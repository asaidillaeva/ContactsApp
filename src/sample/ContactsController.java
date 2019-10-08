package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ListView.ContactCellFactory;

import java.io.IOException;
import java.util.Comparator;


public class ContactsController {
    protected static ObservableList<Contacts> contacts =
            FXCollections.observableArrayList();
    @FXML private VBox vbDetail;
    @FXML protected ListView<Contacts> mainListView;
    @FXML private ImageView contactImageView;
    @FXML private TextField fistNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private Button newContactButton;
    @FXML private AnchorPane mainAnchorPane;

    public final ChangeListener<Number> contactChangeListener=(observableValue, oldValue, newValue) -> {
        if (contacts.size() != 0 && newValue.intValue() != -1) {
            updateListView(contacts.get(newValue.intValue()));
        }
    };
    @FXML
    void newContactButtonPressed(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("newContacts.fxml"));
            Stage newContactStage = new Stage();
            newContactStage.setTitle("Contacts App");
            newContactStage.setScene(new Scene(root, 600, 400));
            newContactStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
    @FXML
    void onDeleteClicked(ActionEvent event) {
        contacts.remove(mainListView.getSelectionModel().getSelectedItem());
        if (contacts.size() > 0) {
            mainListView.getSelectionModel().select(0);
        }
    }

    @FXML
    public void initialize(){
       contacts.add(new Contacts("Saidillaeva","Aliia","aliia.saidillaeva@iaau.edu.kg",708771864,"/sample/img/Snapchat-1441627139.jpg"));
       contacts.add(new Contacts("Kulbaeva", "Aiym","Aiymbubu.kulbaeva@iaau.edu.kg",707207137,"/sample/img/bug.png"));
       contacts.add(new Contacts("Abdimamatova", "Tattygul","tattygul.abdimamatova@iaau.edu.kg",707070707,"/sample/img/taty.jpg"));
       mainListView.setCellFactory(new ContactCellFactory());
       mainListView.setItems(contacts);
       sortContacts();
        mainListView.getSelectionModel().selectedIndexProperty()
                .addListener(contactChangeListener);

    }
    private void sortContacts() {
        contacts.sort(Comparator.comparing(Contacts::getLastName));
    }
    private void updateListView(Contacts contacts){
        lastNameTextField.setText(contacts.getLastName());
        fistNameTextField.setText(contacts.getFirstName());
        phoneNumberTextField.setText(contacts.getPhoneNumber());
        emailTextField.setText(contacts.getEmail());
        contactImageView.setImage(contacts.getImage());
    }
}


