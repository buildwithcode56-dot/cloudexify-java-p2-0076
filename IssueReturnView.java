package com.mycompany.librarymanagementsystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IssueReturnView {


    private Library library;
     private Dashboard dashboard;

    private BorderPane root;

    private TextField bookIdField;
    private TextField memberIdField;
    
    private Button issueButton;
    private Button returnButton;
    private Button clearButton;
    private Button backButton;

    public IssueReturnView(Library library,Dashboard dashboard) 
    {
        this.library = library;
        this.dashboard=dashboard;

        root = new BorderPane();
        createUI();
    }

    private void createUI()
    {
        Label heading =new Label("Issue / Return Book");
        heading.setStyle( "-fx-font-size:24px;" + "-fx-font-weight:bold;" );
        
        BorderPane.setAlignment(heading,Pos.CENTER);
        root.setTop(heading);
        
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(20, 0, 0, 0)); 
        grid.setHgap(15);
        grid.setVgap(15);

        Label bookLabel=new Label("Book ID:");
        Label memberLabel=new Label("Member ID:");
        bookIdField = new TextField();
        memberIdField = new TextField();

        bookIdField.setPromptText("Enter Book ID");
        memberIdField.setPromptText("Enter Member ID");

        issueButton = new Button("Issue Book");
        returnButton = new Button("Return Book");
        clearButton = new Button("Clear");
        backButton = new Button("Back");

        grid.add(bookLabel, 0, 0);
        grid.add(bookIdField, 1, 0);

        grid.add(memberLabel, 0, 1);
        grid.add(memberIdField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        double btnWidth = 105;
        issueButton.setPrefWidth(btnWidth);
        returnButton.setPrefWidth(btnWidth);
        clearButton.setPrefWidth(btnWidth);
        backButton.setPrefWidth(btnWidth);

        buttonBox.getChildren().addAll(issueButton, returnButton, clearButton, backButton);

        grid.add(buttonBox, 0, 2, 2, 1);

        VBox box = new VBox(20);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(20, 0, 0, 0));
        box.getChildren().add(grid);

        root.setCenter(box);

        issueButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText().trim());
                int memberId = Integer.parseInt(memberIdField.getText().trim());

                boolean result = library.issueBook(bookId, memberId);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Book issued successfully.");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Unable to issue book. Check Book ID and Member ID.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Please enter valid numeric IDs.");
            }
        });

        returnButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText().trim());
                int memberId = Integer.parseInt(memberIdField.getText().trim());

                boolean result = library.returnBook(bookId, memberId);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Book returned successfully.");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Unable to return book.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Please enter valid numeric IDs.");
            }
        });

        clearButton.setOnAction(e -> clearFields());


        backButton.setOnAction(e -> 
                dashboard.showDashboard());
    }

    private void clearFields() {
        bookIdField.clear();
        memberIdField.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public BorderPane getView() {
        return root;
    }
}
