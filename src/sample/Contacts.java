package sample;

import javafx.scene.image.Image;


public class Contacts{
    public static Image bye = new Image("/sample/img/bug.png");
    private static long contactsId = 0;
    private long id;
    private String lastName;
    private String firstName;
    private String email;
    private int phoneNumber;
    private Image image;
    public Contacts() {
        this.id = contactsId++;
    }

    public Contacts(String lastName, String firstName, String email, int phoneNumber, String image) {
        this.lastName=lastName;
        this.firstName=firstName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.image=new Image(image);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImage(Image image) {

        this.image = image;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Image getImage() {
        return image;
    }

    public String getPhoneNumber() {
        return String.valueOf(phoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
