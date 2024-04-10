package gui;


import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;



public class GuiListView1Client extends Application {
    public GuiListView1Client() {
    }

    BufferedReader in;
    PrintWriter out;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("ListView Demo1");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane, 1050, 850);
        stage.setScene(scene);
        stage.show();

        // connect to the server
        Socket socket = new Socket("localhost", 1234);

        // Input and output streams
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);


        // Start a new thread to handle client output
        Thread clientOutput = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            }
        });
        clientOutput.start();
    }

    // -------------------------------------------------------------------------
    Button Drikkevarebtn = new Button("Drikkevare");
    Button Chokoladebtn = new Button("Chokolade");
    Button Madbtn = new Button("Mad");
    Button Slikbtn = new Button("Slik");
    TextField antaltxf = new TextField("1");
    Button Bestilbtn = new Button("Bestil");
    Label label = new Label();

    String[] navn = new String[27];
    int[] pris = new int[27];


    ScrollPane scroll = new ScrollPane();
    FlowPane flowPane = new FlowPane();
    ToggleGroup toggleGroup = new ToggleGroup();
    ToggleButton btn = new ToggleButton();

    private void initContent(GridPane pane) throws IOException {
        pane.setGridLinesVisible(false);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        VBox leftBox = new VBox(10);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setPrefWidth(150);

        // Set background color for the buttons
        Drikkevarebtn.setPrefSize(100, 50);
        Drikkevarebtn.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white;");
        Drikkevarebtn.setOnAction(event -> {
            try {
                this.Drikkevarelist();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        });

        Chokoladebtn.setPrefSize(100, 50);
        Chokoladebtn.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white;");
        Chokoladebtn.setOnAction(event -> {
            try {
                this.Chokoladelist();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        });

        leftBox.getChildren().addAll(Drikkevarebtn, Chokoladebtn);

        VBox rightBox = new VBox(10);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPrefWidth(150);

        // Set background color for the buttons
        Madbtn.setPrefSize(100, 50);
        Madbtn.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #2ecc71; -fx-text-fill: white;");
        Madbtn.setAlignment(Pos.TOP_CENTER);

        Slikbtn.setPrefSize(100, 50);
        Slikbtn.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #2ecc71; -fx-text-fill: white;");
        Slikbtn.setAlignment(Pos.TOP_CENTER);

        rightBox.getChildren().addAll(Madbtn, Slikbtn);

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.TOP_CENTER);

        antaltxf.setPrefWidth(50);
        antaltxf.setAlignment(Pos.CENTER);

        // Set background color for the button
        Bestilbtn.setPrefWidth(100);
        Bestilbtn.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        Bestilbtn.setOnAction(event -> {
            try {
                this.BestilKnap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Set text color for the label
        label.setPrefWidth(200);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: #f1c40f;");

        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPrefWrapLength(800);

        scroll.setPrefSize(800, 850);
        scroll.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        scroll.setContent(flowPane);

        centerBox.getChildren().addAll(antaltxf, Bestilbtn, label, scroll);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF, #DDDDDD);");
        hbox.getChildren().addAll(leftBox, centerBox, rightBox);



        pane.add(hbox, 0, 0);

    }


        private void BestilKnap () throws IOException {
            String labelText = null;
            if (toggleGroup.getSelectedToggle() != null) {
                labelText = (String) toggleGroup.getSelectedToggle().getUserData();
                System.out.println("Selected label: " + labelText);
            }
            String antalString = "PC1 --> " + labelText + " Antal: " + antaltxf.getText();
            out.println(antalString);
            antaltxf.setText("1");

            for (Toggle toggle : toggleGroup.getToggles()) {
                if (!toggle.equals(toggleGroup.getSelectedToggle())) {
                    ((ToggleButton) toggle).setDisable(false);
                }
            }
        }


        private void Drikkevarelist () throws FileNotFoundException {
            navn = new String[]{"K", "Lion", "M&M'S", "Oreo Milky", "Oreo Orignal", "RitterSport", "K", "Lion", "M&M'S", "Oreo Milky", "Oreo Orignal", "RitterSport", "K", "Lion",
                    "M&M'S", "Oreo Milky", "Oreo Orignal", "RitterSport", "K", "Lion", "M&M'S", "Oreo Milky", "Oreo Orignal", "RitterSport", "Oreo Milky", "Oreo Orignal", "RitterSport"};
            pris = new int[]{0, 25, 30, 45, 60, 85, 45, 0, 25, 30, 45, 60, 85, 45, 0, 25, 30, 45, 60, 85, 45, 25, 30, 45, 60, 85, 45};
            System.out.println(navn.length);
            System.out.println(pris.length);
            flowPane.getChildren().clear();
            for (int i = 0; i <= 5; i++) {
                File file = new File("C:\\Users\\abdel\\Onedrive\\Skrivebord\\drik\\Soda\\img1 (" + i + ").png");
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Label label = new Label(navn[i] + " " + pris[i] + ".-");
                label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                label.setAlignment(Pos.CENTER);

                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(imageView, label);

                ToggleButton btn = new ToggleButton(); // create a new ToggleButton for each iteration
                btn.setGraphic(vBox);
                btn.setToggleGroup(toggleGroup);
                btn.setUserData(navn[i] + " " + pris[i] + ".-");
                btn.setOnAction(event -> {
                    // Disable all other buttons in the ToggleGroup
                    for (Toggle toggle : toggleGroup.getToggles()) {
                        if (!toggle.equals(toggleGroup.getSelectedToggle())) {
                            ((ToggleButton) toggle).setDisable(true);
                        }
                    }
                });
                flowPane.getChildren().add(btn); // add the current ToggleButton to the flowPane
            }
        }


        private void Chokoladelist () throws FileNotFoundException {
            String[] navn = {"Peanuts", "Lion", "M&M'S", "Oreo Milky", "Oreo Orignal", "RitterSport",};
            int[] pris = {0, 25, 30, 45, 60, 85, 45};
            flowPane.getChildren().clear();
            for (int i = 0; i <= 5; i++) {
                File file = new File("C:\\Users\\abdel\\Onedrive\\Skrivebord\\drik\\Chokolade\\img1 (" + i + ").png");
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);


                Label label = new Label(navn[i] + " " + pris[i] + ".-");
                label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                label.setAlignment(Pos.CENTER);

                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(imageView, label);

                ToggleButton btn = new ToggleButton();
                btn.setGraphic(vBox);
                btn.setToggleGroup(toggleGroup);
                btn.setUserData(navn[i] + " " + pris[i] + ".-");
                btn.setOnAction(event -> {
                    // Disable all other buttons in the ToggleGroup
                    for (Toggle toggle : toggleGroup.getToggles()) {
                        if (!toggle.equals(toggleGroup.getSelectedToggle())) {
                            ((ToggleButton) toggle).setDisable(true);
                        }
                    }
                });
                flowPane.getChildren().add(btn);
            }
        }
    }









