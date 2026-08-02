package com.mycompany.librarymanagementsystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Dashboard {

    private Library library;
    private BorderPane root;

    public Dashboard(Library library) {

        this.library = library;

        root = new BorderPane();

        createUI();

    }
    private void createUI() {

        Label title = new Label("Library Management System");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1a252f;");

        Label subtitle = new Label("Welcome to Admin Dashboard — Select an Operation");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555; -fx-font-style: italic;");

     
        VBox headerBox = new VBox(6); 
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20, 0, 10, 0)); 
        headerBox.getChildren().addAll(title, subtitle);
        root.setTop(headerBox);

        Button addBookButton =new Button("Add Book");
        Button viewBooksButton = new Button("View Books");
        Button memberButton =new Button("Manage Members");
        Button issueReturnButton = new Button("Issue / Return Book");
        Button saveButton =new Button("Save Data");

       VBox menu = new VBox(12);
       menu.setAlignment(Pos.CENTER);
       menu.setPadding(new Insets(15,0,0,0));
       addBookButton.setPrefSize(200, 40);
       viewBooksButton.setPrefSize(200, 40);
       memberButton.setPrefSize(200, 40);
       issueReturnButton.setPrefSize(200, 40);
       saveButton.setPrefSize(200, 40);

       menu.getChildren().addAll( addBookButton,viewBooksButton,memberButton,issueReturnButton,saveButton);
        root.setCenter(menu);

        addBookButton.setOnAction(e -> {

            AddBookView view =new AddBookView(library,this);
            root.setCenter(view.getView());
        });

        viewBooksButton.setOnAction(e -> {

            ViewBooksView view = new ViewBooksView(library,this);
            root.setCenter(view.getView());
        });

        memberButton.setOnAction(e -> {

            MemberView view =new MemberView(library,this);
            root.setCenter(view.getView());
        });
        
        issueReturnButton.setOnAction(e -> {
            
            IssueReturnView view = new IssueReturnView(library,this);
            root.setCenter(view.getView());
        });
        
        saveButton.setOnAction(e -> {  
        FileManager.saveBooks(library.getBooks());
        FileManager.saveMembers(library.getMembers());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Data saved sucessfully");
        alert.showAndWait();

        });
    }
    public void showDashboard()
    {
        createUI();
    }
    public BorderPane getView() 
    {
        return root;

    }

}
