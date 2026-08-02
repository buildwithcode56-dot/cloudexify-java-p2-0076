package com.mycompany.librarymanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AddBookView {

    private Library library;
    private Dashboard dashboard;
    private BorderPane root;

    private TextField titleField;
    private TextField authorField;
    private TextField categoryField;
    private TextField isbnField;
    private TextField quantityField;

    private Button addButton;
    private Button clearButton;
    private Button backButton;

    public AddBookView(Library library,Dashboard dashboard) {

        this.library = library;
        this.dashboard=dashboard;
        root = new BorderPane();
      
        createUI();

    }

    private void createUI() {

        Label heading = new Label("Add New Book");
        heading.setStyle( "-fx-font-size:24px;" + "-fx-font-weight:bold;");
        BorderPane.setAlignment(heading, Pos.CENTER);
        root.setTop(heading);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(15);
        
        Label titleLabel = new Label("Title");
        Label authorLabel = new Label("Author");
        Label categoryLabel = new Label("Category");
        Label isbnLabel = new Label("ISBN");
        Label quantityLabel = new Label("Quantity");
        
        titleField = new TextField();
        authorField = new TextField();
        categoryField = new TextField();
        isbnField = new TextField();
        quantityField = new TextField();
        titleField.setPromptText("Enter Book Title");
        authorField.setPromptText("Enter Author");
        categoryField.setPromptText("Enter Category");
        isbnField.setPromptText("Enter ISBN");
        quantityField.setPromptText("Enter Quantity");

        grid.add(titleLabel,0,0);
        grid.add(titleField,1,0);

        grid.add(authorLabel,0,1);
        grid.add(authorField,1,1);

        grid.add(categoryLabel,0,2);
        grid.add(categoryField,1,2);

        grid.add(isbnLabel,0,3);
        grid.add(isbnField,1,3);

        grid.add(quantityLabel,0,4);
        grid.add(quantityField,1,4);

        addButton = new Button("Add Book");
        clearButton = new Button("Clear");
        backButton=new Button("Back");
        addButton.setPrefWidth(120);
        clearButton.setPrefWidth(120);

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addButton,clearButton,backButton);

        grid.add(buttonBox,1,5);
        root.setCenter(grid);
        
       addButton.setOnAction(e -> {

            try {

                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String category = categoryField.getText().trim();
                String isbn = isbnField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText());
                if(title.isEmpty() || author.isEmpty() || category.isEmpty() || isbn.isEmpty()) 
                {
                    showAlert( Alert.AlertType.ERROR, "All fields are required.");
                    return;
                }
                
                Book book = new Book(title,author,category, isbn,quantity);
                library.addBook(book);

                showAlert(Alert.AlertType.INFORMATION,"Book added successfully.");
                clearFields();
            }
            catch(NumberFormatException ex)
            {
                showAlert( Alert.AlertType.ERROR,"Quantity must be a valid number.");
            }
        });

        clearButton.setOnAction(e -> clearFields());
        backButton.setOnAction(e->{
        dashboard.showDashboard();
    });
    }
   
    private void clearFields(){

        titleField.clear();
        authorField.clear();
        categoryField.clear();
        isbnField.clear();
        quantityField.clear();
    }

    private void showAlert(Alert.AlertType type,String message){

        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public BorderPane getView()
    {
        return root;
    }
}
