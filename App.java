package com.mycompany.librarymanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;

public class App extends Application {

    public static Library library;

    public void start(Stage stage) 
    {
        library = new Library();
        library.getBooks().addAll(FileManager.loadBooks());
        library.getMembers().addAll(FileManager.loadMembers());
        
        Dashboard dashboard = new Dashboard(library);
        Scene scene = new Scene(dashboard.getView(),800,500);
        stage.setTitle("Welcome To Library Management System");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            
            FileManager.saveBooks(library.getBooks() );
            FileManager.saveMembers(library.getMembers() );
        });

    }
    public static void main(String[] args) {

        launch(args);

    }

}
