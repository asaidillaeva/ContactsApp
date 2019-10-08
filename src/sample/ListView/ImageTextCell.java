package sample.ListView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import sample.Contacts;


public class ImageTextCell extends ListCell<Contacts> {
    private HBox hBox = new HBox(8.0);
    public ImageView avatar = new ImageView();
    private Label label = new Label();
    public ImageTextCell() {
        hBox.getChildren().add(avatar);
        hBox.getChildren().add(label);

        avatar.setPreserveRatio(true);
        avatar.setFitHeight(35.0);
        avatar.setFitWidth(35.0);
    }
    @Override
    public void updateItem(Contacts contact, boolean empty){
        super.updateItem(contact, empty);

        if (empty || contact == null) {
            setGraphic(null);
        } else {
            avatar.setImage(contact.getImage());
            label.setText(contact.getLastName());

            setGraphic(hBox);
        }
    }
}
