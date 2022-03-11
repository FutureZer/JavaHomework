package com.myphone.phonelist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TableView<Person> contactList;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellDat -> cellDat.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonInfo(null);

        contactList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonInfo(newValue));
    }

    /**
     * Method which displays info of selected person
     *
     * @param per selected person
     */
    private void showPersonInfo(Person per) {
        if (per != null) {
            firstNameLabel.setText(per.getFirstName());
            lastNameLabel.setText(per.getLastName());
            streetLabel.setText(per.getStreet());
            cityLabel.setText(per.getCity());
            numberLabel.setText(String.valueOf(per.getNumber()));
            birthLabel.setText(per.getBirth() != null ? per.getBirth().format(per.getFormatter()) : "");
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            numberLabel.setText("");
            birthLabel.setText("");
        }
    }

    @FXML
    void createNewPerson() {
        showSinglePersonDialog(null);
    }

    @FXML
    void deletePerson() {
        int selectedIndex = contactList.getSelectionModel().getSelectedIndex();
        if (selectedIndex > -1) {
            contactList.getItems().remove(selectedIndex);
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setHeaderText("Nobody selected");
            error.setContentText("You need to select a person before deletion");
            error.showAndWait();
        }
    }

    @FXML
    void editPerson() {
        int selectedIndex = contactList.getSelectionModel().getSelectedIndex();
        if (selectedIndex > -1) {
            showSinglePersonDialog(contactList.getItems().get(selectedIndex));
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setHeaderText("Nobody selected");
            error.setContentText("You need to select a person before edit");
            error.showAndWait();
        }
    }

    /**
     * Creates new window (dialog) for addition or editing
     *
     * @param person null if dialog for addition otherwise dialog for addition
     */
    void showSinglePersonDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("edit-person.fxml"));
            AnchorPane page = loader.load();

            Stage dialog = new Stage();
            dialog.setTitle("Person info");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Scene stage = new Scene(page);
            dialog.setScene(stage);

            PersonController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setPerson(person);

            // Show dialog
            dialog.showAndWait();

            // If person was null, we would have created new one, so we need to add it to table
            if (person == null && controller.getPerson() != null) {
                person = controller.getPerson();
                contactList.getItems().add(person);
            }

            // Display new or edited person info
            showPersonInfo(person);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
