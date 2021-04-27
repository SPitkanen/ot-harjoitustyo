package wbapp.ui;

import java.sql.*;
import java.util.ArrayList;
import wbapp.dao.*;
import wbapp.domain.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.EventListener;
import javafx.event.EventType;
import java.awt.event.FocusEvent;
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
    private WBCalculator calculator;
    private ComboBox<Aircraft> cbox;
    private Button calcButton;
    private BorderPane borderPane;
    private TextField userName;
    private PasswordField pswdText;
    private Stage primaryStage;
    private Label loginMessage;
    private LineChart<Number, Number> chartTemplate;
    
    @Override
    public void init() throws Exception {
        DbDao dbdao = new DbDao();
        db = dbdao.createConnection();
        
        UserDataDao usrData = new UserDataDao(db);
        this.user = new User(usrData);
        
        acData = new AcDataDao(db);
        this.acList = new AircraftList(acData);
        
        calculator = new WBCalculator();
        
        borderPane = new BorderPane();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Weight & Balance");
        
        login();
    }
    
    public void showScene(Pane pane) {
        Scene newScene = new Scene(pane, 480, 640);
        this.primaryStage.setScene(newScene);
        this.primaryStage.show();
    }
    
    public GridPane userInputScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        Label username = new Label("Name:");
        gridPane.add(username, 0, 1);
        
        userName = new TextField();
        gridPane.add(userName, 1, 1);
        
        Label pswd = new Label("Password:");
        gridPane.add(pswd, 0, 2);
        
        pswdText = new PasswordField();
        gridPane.add(pswdText, 1, 2);
        
        loginMessage = new Label();
        HBox b2Loc = new HBox(10);
        b2Loc.setAlignment(Pos.CENTER_LEFT);
        b2Loc.getChildren().add(loginMessage);
        gridPane.add(b2Loc, 1, 5);
        gridPane.add(loginMessage, 0, 0);
        
        return gridPane;
    }
    
    public void login() {
        GridPane gridPane = userInputScene();
        Button loginButton = new Button("Login");
        HBox b1Loc = new HBox(10);
        b1Loc.setAlignment(Pos.BOTTOM_RIGHT);
        b1Loc.getChildren().add(loginButton);
        gridPane.add(b1Loc, 1, 4);
        gridPane.add(loginButton, 1, 3);
        loginButton.setOnAction(e -> {
            String name = userName.getText();
            String password = pswdText.getText();
            
            if (this.user.login(name, password)) {
                initWBSheet();
                redrawWBSheet();
            } else {
                loginMessage.setText("Väärä käyttäjätunnus tai salasana!");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        Button signupButton = new Button("Signup");
        HBox b3Loc = new HBox(10);
        b3Loc.setAlignment(Pos.BOTTOM_LEFT);
        b3Loc.getChildren().add(signupButton);
        gridPane.add(b3Loc, 0, 4);
        gridPane.add(signupButton, 0, 3);
        signupButton.setOnAction(e -> {
            signup();
        });
        showScene(gridPane);
    }
    
    public void signup() {
        GridPane gridPane = userInputScene();
        
        Button signupButton = new Button("Signup");
        HBox b3Loc = new HBox(10);
        b3Loc.setAlignment(Pos.BOTTOM_RIGHT);
        b3Loc.getChildren().add(signupButton);
        gridPane.add(b3Loc, 1, 4);
        gridPane.add(signupButton, 0, 3);
        signupButton.setOnAction(e -> {
            String name = userName.getText();
            String password = pswdText.getText();
            
            if (this.user.signup(name, password)) {
                initWBSheet();
                redrawWBSheet();
            } else {
                loginMessage.setText("Käyttäjätunnus tai salasana on jo olemassa!");
                loginMessage.setTextFill(Color.RED);
            }
        });
        showScene(gridPane);
    }
    
    public void initWBSheet() {
        grid = new GridPane();
        
        cbox = new ComboBox<>();
        cbox.setItems(this.acList.getAcNameList());
        cbox.getSelectionModel().selectFirst();
        
        calcButton = new Button("Calculate");
        grid.add(calcButton, 5, count + 2);
        calcButton.setOnAction(e -> {
            ObservableList<Node> nodes = grid.getChildren();
            for (Node node : nodes) {
                int x = GridPane.getColumnIndex(node);
                int y = GridPane.getRowIndex(node);
                if (x == 2 && node instanceof TextField) {
                    double k = Double.valueOf(((TextField)node).getText());
                    data.addWeights(k, y - 2);
                }
            }
            data.setData(calculator.calculateData(data.getData(), count));
            double[][] s = data.getData();
            redrawWBSheet();
        });
        
        plane = cbox.getSelectionModel().getSelectedItem();
        data = new AircraftData(acData, plane.getAcId());
        redrawWBSheet();
        
        cbox.setOnAction(e -> {
            plane = cbox.getSelectionModel().getSelectedItem();
            data = new AircraftData(acData, plane.getAcId());
            redrawWBSheet();
        });
    }
    
    public void redrawInputSheet() {
        grid.getChildren().clear();
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
        grid.add(calcButton, 3, count + 2);
        double[] lista = new double[count];
        for (int i = 0; i < count; i++) {
            Label itemLabel = new Label(items.get(i));
            Label armLabel = new Label(String.valueOf(acDataList[i][0]));
            Label weightLabel = new Label(String.valueOf(acDataList[i][1]));
            TextField weightInput = new TextField(String.valueOf(acDataList[i][1]));
            Label momentLabel = new Label(String.valueOf(acDataList[i][2]));
            
            grid.add(itemLabel, 0, i + 2);
            grid.add(armLabel, 1, i +2);
            
            if (acDataList[i][1] > acDataList[i][3] || acDataList[i][1] < 0) {
                weightLabel.setTextFill(Color.RED);
                weightInput.setText("Min 0, Max " + acDataList[i][3] + "!");
            }
            if (acDataList[i][4] == 0) {
                grid.add(weightLabel, 2, i + 2);
            } else {
                final double k = 0;
                grid.add(weightInput, 2, i + 2);
            }
            
            grid.add(momentLabel, 3, i + 2);
        }
    }
    
    public void redrawEnvelope() {
        try {
            double[][] chartAxis = data.getChartData(plane.getAcId());
            NumberAxis x = new NumberAxis(chartAxis[0][0], chartAxis[0][1], chartAxis[0][4]);
            x.setLabel("Center of gravity-inches aft of datum");

            NumberAxis y = new NumberAxis(chartAxis[0][2], chartAxis[0][3], chartAxis[0][5]);
            y.setLabel("Weight-lbs");

            chartTemplate = new LineChart<>(x, y);
            XYChart.Series envelope = new XYChart.Series<>();
            
            double[][] coordinates = data.getEnvelopeData(plane.getAcId());
            for (int i = 0; i < data.getCoordinateCount(plane.getAcId()); i++) {
                envelope.getData().add(new XYChart.Data<>(coordinates[i][0], coordinates[i][1]));
            }
            XYChart.Series<Number, Number> envelope2 = new XYChart.Series<>();
            envelope2.getData().add(new XYChart.Data<>(acDataList[4][0], acDataList[4][1]));
            envelope2.getData().add(new XYChart.Data<>(acDataList[8][0], acDataList[8][1]));
            envelope2.getData().add(new XYChart.Data<>(acDataList[10][0], acDataList[10][1]));
            //envelope.getData().add(new XYChart.Data<>(coordinates[0][0], coordinates[0][1]));
            chartTemplate.getData().add(envelope);
            chartTemplate.getData().add(envelope2);
            chartTemplate.setCreateSymbols(false);
        } catch (SQLException e) {
            
        }
    }
    
    public void redrawWBSheet() {
        BorderPane borderPane = new BorderPane();
        redrawInputSheet();
        borderPane.setCenter(grid);
        redrawEnvelope();
        borderPane.setBottom(chartTemplate);
        showScene(borderPane);
    }
}

