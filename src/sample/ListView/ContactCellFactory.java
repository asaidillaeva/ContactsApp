package sample.ListView;

import sample.Contacts;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class ContactCellFactory implements Callback<ListView<Contacts>, ListCell<Contacts>> {

    @Override
    public ListCell<Contacts> call(ListView<Contacts> contactListView) {
        return new ImageTextCell();
    }
}
