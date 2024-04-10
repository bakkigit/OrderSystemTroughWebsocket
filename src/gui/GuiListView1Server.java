package gui;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GuiListView1Server extends Application {

    public GuiListView1Server() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("ListView Demo1");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane, 1050, 800);
        stage.setScene(scene);
        stage.show();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                modtagFraServer();
                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();


    }
    // -------------------------------------------------------------------------

    ListView<String> ordreList = new ListView<>();
    ArrayList<String> ordredisplay = new ArrayList<>();

    Button fjernbtn = new Button("Fjern Bestilling");


    private void initContent(GridPane pane) throws IOException {
        // set horizontal and vertical gaps between components
        pane.setHgap(10);
        pane.setVgap(10);



        fjernbtn.setOnAction(event -> this.fjernKnap());


        // create a VBox container to hold the components
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // add the components to the container
        vbox.getChildren().addAll(ordreList,fjernbtn);

        // set the preferred size of the container
        vbox.setPrefSize(1000, 700);

        // create a ScrollPane to display the container
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1050, 800);
        scrollPane.setContent(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // add the ScrollPane to the GridPane
        pane.add(scrollPane, 0, 0);
    }

    private void fjernKnap() {
        String selectedItem = ordreList.getSelectionModel().getSelectedItem();
        ordredisplay.remove(selectedItem);
        ordreList.getItems().clear();
        ordreList.getItems().addAll(ordredisplay);

    }














    private void modtagFraServer () throws IOException {
                ServerSocket serverSocket = new ServerSocket(1234);
                System.out.println("Server is listening on port 1234");

                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String clientMessage = in.readLine();
                    if (clientMessage == null || clientMessage.equals("exit")) {
                        break;
                    }
                    System.out.println(clientMessage);

                    // Add the client message to the list view and the ArrayList
                    Platform.runLater(() -> {
                        ordreList.getItems().clear();
                        ordredisplay.add(clientMessage);
                        ordreList.getItems().addAll(ordredisplay);
                    });

                    // Send a response back to the client
                    out.println("Received message: " + clientMessage);
                }

                socket.close();
                serverSocket.close();
            }
        }










