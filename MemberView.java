package com.mycompany.librarymanagementsystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MemberView {

    private Library library;
    private Dashboard dashboard;

    private BorderPane root;

    private TableView<Member> table;

    private ObservableList<Member> memberList;

    private TextField nameField;
    private TextField phoneField;

    private Button addButton;
    private Button clearButton;
    private Button backButton;
    private Button deleteButton;


    public MemberView(Library library,Dashboard dashboard) {

        this.library = library;
        this.dashboard=dashboard;
        root = new BorderPane();

        createUI();
    }
    
    private void createUI()
    {
        Label heading = new Label("Member Management");


        heading.setStyle( "-fx-font-size:24px;" + "-fx-font-weight:bold;" );
        BorderPane.setAlignment(heading, Pos.CENTER );
        root.setTop(heading);

        GridPane form = new GridPane();
        form.setPadding(new Insets(20));
        form.setHgap(15);
        form.setVgap(15);
        form.setAlignment(Pos.CENTER);
        
        Label nameLabel = new Label("Member Name:");
        Label phoneLabel = new Label("Phone Number:");

        nameField = new TextField();
        phoneField = new TextField();
        nameField.setPromptText( "Enter member name" );
        phoneField.setPromptText("Enter phone number");

        addButton = new Button("Add Member");
        clearButton = new Button("Clear");
        backButton=new Button("Back");
        deleteButton=new Button("Delete");

        form.add(nameLabel,0,0);
        form.add(nameField,1,0);
        form.add(phoneLabel,0,1);
        form.add(phoneField,1,1);
        
        javafx.scene.layout.HBox buttonBox = new javafx.scene.layout.HBox(10); 
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addButton, clearButton, deleteButton, backButton);
        form.add(buttonBox, 0, 2, 2, 1);

        table = new TableView<>();
        TableColumn<Member,Integer> idColumn =new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("memberId")
        );

        TableColumn<Member,String> nameColumn = new TableColumn<>("Name");


        nameColumn.setCellValueFactory( new PropertyValueFactory<>("name") );
        
        TableColumn<Member,String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        table.getColumns().addAll(idColumn,nameColumn,phoneColumn);

        memberList =FXCollections.observableArrayList();
        memberList.addAll(library.getMembers());
        
        table.setItems(memberList);

        VBox layout =new VBox(20);
        layout.setPadding(new Insets(20));


        layout.getChildren().addAll(form,table);
        
        root.setCenter(layout);
        addButton.setOnAction(e -> {

            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            
            if(name.isEmpty() || phone.isEmpty()) 
            {
                showAlert( Alert.AlertType.ERROR, "Please fill all fields.");
                return;
            }
            Member member =new Member(name, phone);

            library.addMember(member);
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Member added successfully.");
            clearFields();

        });

        clearButton.setOnAction(e -> {
            clearFields();
        });
        
        deleteButton.setOnAction(e -> {

            Member selectedMember =table.getSelectionModel().getSelectedItem();
           if(selectedMember == null)
            {
                showAlert(Alert.AlertType.WARNING,"Please select a member first.");
                 return;
            }
           boolean deleted = library.removeMember(selectedMember.getMemberId());
           if(deleted)
           {
             refreshTable();
            showAlert( Alert.AlertType.INFORMATION, "Member deleted successfully.");
            }
           else 
           {
             showAlert(Alert.AlertType.ERROR, "Cannot delete member. Return all issued books first.");

           }
        });
        
      backButton.setOnAction(e->{
        dashboard.showDashboard();
    });
    }

    private void clearFields() {

        nameField.clear();
        phoneField.clear();
    }

    private void refreshTable() 
    {
        memberList.clear();
        memberList.addAll(library.getMembers());
        table.setItems(memberList);
    }

    private void showAlert(Alert.AlertType type,String message)
    {
        Alert alert =new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public BorderPane getView() 
    {
        return root;

    }

}
