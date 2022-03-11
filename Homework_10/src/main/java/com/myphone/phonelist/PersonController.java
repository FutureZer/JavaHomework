package com.myphone.phonelist;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class PersonController {

    @FXML
    private DatePicker birthField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField numberField;


    @FXML
    private TextField streetField;

    Person handledPerson = null;
    Stage dialog = null;

    public void setPerson(Person per) {
        handledPerson = per;
        if (per != null) {
            firstNameField.setText(handledPerson.getFirstName());
            lastNameField.setText(handledPerson.getLastName());
            streetField.setText(handledPerson.getStreet());
            cityField.setText(handledPerson.getCity());
            numberField.setText(String.valueOf(handledPerson.getNumber()));
            birthField.setValue(handledPerson.getBirth());
        }
    }

    public Person getPerson() {
        return handledPerson;
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
        // Force numberField to be numeric only
        numberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void initialize() {
    }

    @FXML
    void cancelChanges() {
        dialog.close();
    }

    @FXML
    void saveChanges() {
        if (handledPerson == null) {
            handledPerson = new Person();
        }
        if (Objects.equals(firstNameField.getText(), "") || Objects.equals(lastNameField.getText(), "") ||
                Objects.equals(numberField.getText(), "")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setHeaderText("Impossible to create person with empty first, last name or number");
            error.setContentText("Fill must have fields!");
            error.showAndWait();
        } else {
            handledPerson.setFirstName(firstNameField.getText());
            handledPerson.setLastName(lastNameField.getText());
            handledPerson.setStreet(streetField.getText());
            handledPerson.setCity(cityField.getText());
            handledPerson.setNumber(Integer.parseInt(numberField.getText()));
            handledPerson.setBirth(birthField.getValue());
            dialog.close();
        }
    }


}
