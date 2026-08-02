package com.mycompany.librarymanagementsystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewBooksView {

    private Library library;
    private Dashboard dashboard;

    private BorderPane root;

    private TableView<Book> table;

    private ObservableList<Book> bookList;

    private TextField searchField;

    private Button searchButton;
    private Button showAllButton;
    private Button deleteButton;
    private Button backButton;



    public ViewBooksView(Library library,Dashboard dashboard) 
    {
        this.library = library;
        this.dashboard=dashboard;
        root = new BorderPane();

        createUI();
    }
    private void createUI()
    {
        Label heading = new Label("Library Books");
        heading.setStyle("-fx-font-size:24px;" +"-fx-font-weight:bold;");
        BorderPane.setAlignment(heading, Pos.CENTER);
        root.setTop(heading);
        
        table = new TableView<>();
        TableColumn<Book,Integer> idColumn =new TableColumn<>("ID");
        idColumn.setCellValueFactory( new PropertyValueFactory<>("bookId"));
        idColumn.setPrefWidth(80);

        TableColumn<Book,String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(180);

        TableColumn<Book,String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory( new PropertyValueFactory<>("author"));
        authorColumn.setPrefWidth(150);

        TableColumn<Book,String> categoryColumn =new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setPrefWidth(150);

        TableColumn<Book,String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory( new PropertyValueFactory<>("isbn"));
        isbnColumn.setPrefWidth(150);

        TableColumn<Book,Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory( new PropertyValueFactory<>("quantity"));
        quantityColumn.setPrefWidth(100);

        table.getColumns().addAll( idColumn,titleColumn,authorColumn,categoryColumn, isbnColumn, quantityColumn );
        bookList = FXCollections.observableArrayList();
        bookList.addAll(library.getBooks());

        table.setItems(bookList);
        searchField = new TextField();
        searchField.setPromptText("Search by Title");

        searchButton = new Button("Search");
        showAllButton = new Button("Show All");
        deleteButton = new Button("Delete Book");
        backButton=new Button("Back");

        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15));
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(searchField,searchButton, showAllButton,deleteButton,backButton);

        VBox centerBox = new VBox(15);
        centerBox.setPadding(new Insets(20));
        centerBox.getChildren().addAll(topBox,table);

        root.setCenter(centerBox);
        searchButton.setOnAction(e -> {

            String text = searchField.getText().trim();
            if(text.isEmpty())
            {
                showAlert( Alert.AlertType.WARNING,"Enter book title to search.");
                return;
            }
            ObservableList<Book> result =FXCollections.observableArrayList();
            result.addAll(library.searchBook(text));
            table.setItems(result);
        });

        showAllButton.setOnAction(e -> {

            refreshTable();
        });

        deleteButton.setOnAction(e -> {

            Book selectedBook =table.getSelectionModel().getSelectedItem();

            if(selectedBook == null)
            {
                showAlert( Alert.AlertType.WARNING, "Please select a book first." );
                return;
            }
            library.removeBook(selectedBook.getBookId() );
            refreshTable();

            showAlert(Alert.AlertType.INFORMATION,"Book deleted successfully." );
        });

      backButton.setOnAction(e->{
        dashboard.showDashboard();
    });
    }

    private void refreshTable() 
    {
        bookList.clear();
        bookList.addAll(library.getBooks());
        table.setItems(bookList);
    }

    private void showAlert(Alert.AlertType type, String message) 
    {
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
