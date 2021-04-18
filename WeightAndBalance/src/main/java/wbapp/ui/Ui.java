package wbapp.ui;

import java.sql.*;
import java.util.ArrayList;
import wbapp.dao.AcDataDao;
import wbapp.domain.AircraftData;
import wbapp.domain.WBCalculator;
import wbapp.domain.Aircraft;
import wbapp.domain.User;
import wbapp.domain.AircraftList;
import wbapp.dao.UserDataDao;
import wbapp.dao.DbDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.EventListener;
import javafx.event.EventType;
/**
 *
 * @author santeripitkanen
 */
public class Ui extends Application {
    
    private Connection db;
    private User user;
    private AircraftList acList;
    private Scene loginScene;
    private Scene mainScene;
    private AcDataDao acData;
    private Aircraft plane;
    private double[][] acDataList;
    private AircraftData data;
    private int count;
    private ArrayList<String> items;
    private GridPane grid;
    private double[] weightList;
    
    @Override
    public void init() throws Exception {
        DbDao dbdao = new DbDao();
        db = dbdao.createConnection();
        
        UserDataDao usrData = new UserDataDao(db);
        this.user = new User(usrData);
        
        acData = new AcDataDao(db);
        this.acList = new AircraftList(acData);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weight & Balance");
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        Label username = new Label("Name:");
        gridPane.add(username, 0, 1);
        
        TextField userName = new TextField();
        gridPane.add(userName, 1, 1);
        
        Label pswd = new Label("Password:");
        gridPane.add(pswd, 0, 2);
        
        PasswordField pswdText = new PasswordField();
        gridPane.add(pswdText, 1, 2);
        
        Label loginMessage = new Label();
        HBox b2Loc = new HBox(10);
        b2Loc.setAlignment(Pos.BOTTOM_CENTER);
        b2Loc.getChildren().add(loginMessage);
        gridPane.add(b2Loc, 1, 5);
        gridPane.add(loginMessage, 0, 0);
        
        Button loginButton = new Button("Login");
        HBox b1Loc = new HBox(10);
        b1Loc.setAlignment(Pos.BOTTOM_RIGHT);
        b1Loc.getChildren().add(loginButton);
        gridPane.add(b1Loc, 1, 4);
        loginButton.setOnAction(e -> {
            String name = userName.getText();
            String password = pswdText.getText();
            
            if (this.user.login(name, password)) {
               primaryStage.setScene(mainScene);
            } else {
                loginMessage.setText("Väärä käyttäjätunnus tai salasana!");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        ComboBox<Aircraft> cbox = new ComboBox<>();
        cbox.setItems(this.acList.getAcNameList());
        cbox.getSelectionModel().selectFirst();
        
        grid = new GridPane();
        grid.add(cbox, 0, 0);
        
        Button calcButton = new Button("Calculate");
        grid.add(calcButton, 5, count + 2);
        /*calcButton.setOnAction(e -> {
            ObservableList<Node> nodes = grid.getChildren();
        });*/
        
        plane = cbox.getSelectionModel().getSelectedItem();
        redrawWBSheet(cbox, calcButton);
        
        cbox.setOnAction(e -> {
            plane = cbox.getSelectionModel().getSelectedItem();
            redrawWBSheet(cbox, calcButton);
        });
        
        mainScene = new Scene(grid, 480, 640);
        loginScene = new Scene(gridPane, 480, 640);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    public void redrawWBSheet(ComboBox cbox, Button calcButton) {
        grid.getChildren().clear();
        weightList = new double[count];
        data = new AircraftData(acData, plane.getAcId());
        acDataList = data.getData();
        count = data.getCount();
        items = data.getFullItemList();
        
        Label item = new Label("ITEM");
        Label arm = new Label("ARM in");
        Label weight = new Label("WEIGHT lbs");
        Label moment = new Label("MOM/100");
        
        grid.add(cbox, 0, 0);
        grid.add(item, 0, 1);
        grid.add(arm, 1, 1);
        grid.add(weight, 2, 1);
        grid.add(moment, 3, 1);
        
        for (int i = 0; i < count; i++) {
            Label itemLabel = new Label(items.get(i));
            Label armLabel = new Label(String.valueOf(acDataList[i][0]));
            Label weightLabel = new Label(String.valueOf(acDataList[i][1]));
            TextField weightInput = new TextField();
            
            /*weightInput.re
            weightInput.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldV, Boolean newV) -> {
                focus(newV, i, weightInput);
            });*/
            
            Label momentLabel = new Label(String.valueOf(acDataList[i][2]));
            grid.add(itemLabel, 0, i + 2);
            grid.add(armLabel, 1, i +2);
            
            if (acDataList[i][4] == 0) {
                grid.add(weightLabel, 2, i + 2);
            } else {
                grid.add(weightInput, 2, i + 2);
            }
            
            grid.add(momentLabel, 3, i + 2);
        }
        
        grid.add(calcButton, 5, count + 2);
        
        try {
            double[][] chart = data.getChartData(plane.getAcId());
            NumberAxis x = new NumberAxis(chart[0][0], chart[0][1], chart[0][4]);
            x.setLabel("Center of gravity-inches aft of datum");

            NumberAxis y = new NumberAxis(chart[0][2], chart[0][3], chart[0][5]);
            y.setLabel("Weight-lbs");

            ScatterChart<Double, Double> chartTemplate = new ScatterChart(x, y);
        grid.add(chartTemplate, 1, count + 3);
        } catch (SQLException e) {
            
        }
    }
}

