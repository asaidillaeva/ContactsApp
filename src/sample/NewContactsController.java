package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;

import static java.lang.Integer.parseInt;


public class NewContactsController{

    public Button uploadImageButton;
    @FXML
    private ImageView contactImageView;

    @FXML
    private TextField fistNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button saveButton;
    @FXML private AnchorPane newContactsAnchorPane;

    private File file;
    private Contacts contacts;
    private OnContactChangedListener contactChangedListener;


    @FXML
    void uploadImageClicked(ActionEvent actionEvent) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        uploadImageButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            try {
                                Image image= new Image(new FileInputStream(file), 0, 0, true, true);
                                contactImageView.setImage(image);

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

    }
    public void setContact(Contacts contact) {
        if (contact == null) {
            contactImageView.setImage(Contacts.bye);
            return;
        }
        this.contacts = contact;
        contact.setLastName(lastNameTextField.getText());
        contact.setFirstName(fistNameTextField.getText());
        contact.setPhoneNumber(parseInt(phoneNumberTextField.getText()));
        contact.setEmail(emailTextField.getText());
        contact.setImage(contactImageView.getImage());
    }
    private File file2 = new File("/home/aliya/Study/OOP/ch12/ContactsApp/src/sample/img/bug.png");

    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException {
        if (contactChangedListener != null) {
            if (contacts == null) {
                contacts = new Contacts();
            }
        }
        setContact(contacts);
        Stage stage = (Stage) newContactsAnchorPane.getScene().getWindow();
        stage.close();
        if(file == null){
            file =new File("/sample/img/bug.png");
        }else {
            File file1 = File.createTempFile("Image File", "*.jpg", new File("/sample/img/"));

            File file2 = new File(file1 + contacts.getLastName());
            copy(file, file2);
        }
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("contactApp.fxml"));
            Stage newContactStage = new Stage();
            newContactStage.setTitle("Contacts App");
            newContactStage.setScene(new Scene(root, 600, 400));
            newContactStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void copy(File src, File dest) throws IOException {
            InputStream is = null;
            OutputStream os = null;
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
    }
   public interface OnContactChangedListener {
        void onChanged(Contacts contact);
    }
}



