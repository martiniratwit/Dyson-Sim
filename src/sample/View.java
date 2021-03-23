package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Stack;

public class View extends Application implements EventHandler<ActionEvent>{

    private Controller control = new Controller();
    static double costPerSqFootage = 10;
    static double sqFootage = 500;
    static double fuelTons = 3;
    static double fuelCost = 1270.5;
    static double productionCost = 1000000;
    static int satelliteNumber = 1;
    
    ArrayList<Box> visualSats = new ArrayList<>();
    ArrayList<Satellite> satellites = new ArrayList<>();

    Camera camera = new PerspectiveCamera(true);

    private final Sphere sun = sun();
    private final Sphere mercury = mercury();
    private final Sphere milkyWay = milkyWay();

    private double timeSpeed = 1;

    private final Button addSatellite = addSatellite();

    Group animationGroup = new Group(milkyWayIV(), new Group(sun, mercury));

    HBox pane = new HBox();
    VBox pane2 = new VBox();

    @Override
    public void handle(ActionEvent t) {
        Box satellite = satellite();
        visualSats.add(satellite);
        satellites.add(new Satellite(costPerSqFootage, sqFootage, fuelCost, fuelTons, productionCost, 1));
        
        animationGroup.getChildren().add(satellite);
        satellite.getTransforms().add(new Translate(12.5,0,125));
        Label launchCost = new Label("Satellite" + satelliteNumber + " Cost: " + control.getTotalCost(satellites.get(satelliteNumber-1)));
        pane2.getChildren().add(launchCost);



        AnimationTimer Rotation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                satellite.rotateProperty().set(satellite.getRotate() - 1);


            }
        };
        Rotation.start();

        row+=10;
        tilt+=10;
        satelliteNumber+=1;
    }

    private Sphere sun() {
        Sphere sun = new Sphere(100);

        PhongMaterial photosphereMaterial = new PhongMaterial();
        Image sunTexture = new Image(getClass().getResourceAsStream("/resources/Photosphere Texture.jpg"));
        photosphereMaterial.setDiffuseMap(sunTexture); //Initialize Texture to Material
        photosphereMaterial.setSelfIlluminationMap(sunTexture); //Initialize Illumination to Material
        sun.setMaterial(photosphereMaterial); //Add material to Sun

        sun.setRotationAxis(Rotate.Y_AXIS);

        return sun;

    }

    private Sphere mercury() {
        Sphere mercury = new Sphere(10);

        PhongMaterial mercuryMaterial = new PhongMaterial();
        Image mercuryTexture = new Image(getClass().getResourceAsStream("/resources/Mercury Texture.jpg"));
        mercuryMaterial.setDiffuseMap(mercuryTexture); //Initialize Texture to Material
        mercuryMaterial.setSelfIlluminationMap(mercuryTexture); //Initialize Illumination to Material
        mercury.setMaterial(mercuryMaterial); //Add material to Mercury

        mercury.getTransforms().add(new Translate(-150,0,-200));

        mercury.setRotationAxis(Rotate.Y_AXIS);

        return mercury;

    }

    private Sphere milkyWay() {
        Sphere milkyWay = new Sphere(1000);
        milkyWay.setCullFace(CullFace.NONE); //Remove culling to texture faces inside sphere

        PhongMaterial milkyWayMaterial = new PhongMaterial();
        Image milkyWayTexture = new Image(getClass().getResourceAsStream("/resources/Milky Way Texture.jpg"));
        milkyWayMaterial.setDiffuseMap(milkyWayTexture); //Initialize Texture to Material
        milkyWayMaterial.setSelfIlluminationMap(milkyWayTexture); //Initialize Illumination to Material
        milkyWay.setMaterial(milkyWayMaterial); //Add material to Mercury

        return milkyWay;
    }

    private ImageView milkyWayIV(){
        Image milkyWayTexture = new Image(getClass().getResourceAsStream("/resources/Milky Way Texture.jpg"));
        ImageView milkyWayImageView = new ImageView(milkyWayTexture);
        milkyWayImageView.setPreserveRatio(true);
        milkyWayImageView.getTransforms().add(new Translate(-milkyWayTexture.getWidth()/2,-milkyWayTexture.getHeight()/2, 2000));
        return milkyWayImageView;
    }

    private Button addSatellite() {
        Button button = new Button();
        button.setLayoutX(-150);
        button.setLayoutX(200);
        button.setText("Add Satellite");
        button.setOnAction(this);


        return button;

    }

    int row = 50;
    int tilt = 10;
    private Box satellite() {
        Box satellite = new Box(10,10,1);
        //satellite.getTransforms().add(new Translate(0,tilt,0));

        satellite.setRotationAxis(Rotate.Y_AXIS);

        return satellite;
    }

    private void Roation() {

        AnimationTimer Rotation = new AnimationTimer() {
            @Override
            public void handle(long l) {

                mercury.rotateProperty().set(mercury.getRotate()-.05 * timeSpeed);
                mercury.setRotate(mercury.getRotate()-.25 * timeSpeed);

                sun.rotateProperty().set(sun.getRotate() - 0.025 * timeSpeed);

            }
        };
        Rotation.start();
    }

    private Pane animationPane() {
        Pane animationPane = new Pane();
        SubScene subScene = new SubScene(animationGroup, 1000,700);
        animationPane.getChildren().add(subScene);

        return animationPane;

    }
    
    private void setText(TextField text, double field, double value) {
		text.textProperty().setValue(Double.toString(value));
		field = value;
    }
    
    private void setInputPane() {

    	//Panel Cost Text Field
        VBox sPCBox = new VBox();
        Label sPCLabel = new Label("Panel Cost (Per Square Footage)");
        TextField sPCText = new TextField("10");
        sPCBox.getChildren().addAll(sPCLabel,sPCText);
        sPCText.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        		if(sPCText.textProperty().getValueSafe().contains("f") ||
        				sPCText.textProperty().getValueSafe().contains("d")) {
            		setText(sPCText, costPerSqFootage, 10);
        		}
        		costPerSqFootage = Double.parseDouble(newValue);
        	} 
        	catch(Exception ex) {
        		setText(sPCText, costPerSqFootage, 10);
        	}
        });
        pane2.getChildren().add(sPCBox);

        //Square Footage Text Field
        VBox sFBox = new VBox();
        Label sFLabel = new Label("Square Footage");
        TextField sFText = new TextField("500");
        sFBox.getChildren().addAll(sFLabel,sFText);
        sFText.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        		if(sFText.textProperty().getValueSafe().contains("f") ||
        				sFText.textProperty().getValueSafe().contains("d")) {
            		setText(sFText, sqFootage, 500);
        		}
        		sqFootage = Double.parseDouble(newValue);
        	}
        	catch(Exception ex) 
        	{
        		setText(sFText, sqFootage, 500);
        	}
        });
        pane2.getChildren().add(sFBox);

        //Production Cost Text Field
        VBox pCBox = new VBox();
        Label pCLabel = new Label("Production Cost");
        TextField pCText = new TextField("1000000");
        pCBox.getChildren().addAll(pCLabel,pCText);
        pCText.textProperty().addListener((observable, oldValue, newValue) -> {
        	try {
        		if(pCText.textProperty().getValueSafe().contains("f") ||
        				pCText.textProperty().getValueSafe().contains("d")) {
            		setText(pCText, productionCost, 1000000);
        		}
        		productionCost = Double.parseDouble(newValue);
        	}
        	catch(Exception ex) 
        	{
        		setText(pCText, productionCost, 1000000);
        	}
        });
        pane2.getChildren().add(pCBox);
             
        //Fuel Cost Text Field
        VBox fCBox = new VBox();
        Label fCLabel = new Label("Fuel Cost (Per Ton) ");
        TextField fCText = new TextField("1270.5");
        fCBox.getChildren().addAll(fCLabel,fCText);
        fCText.textProperty().addListener((observable,oldValue,newValue) -> {
        	try {
        		if(fCText.textProperty().getValueSafe().contains("f") ||
        				fCText.textProperty().getValueSafe().contains("d")) {
            		setText(fCText, fuelCost, 1270.5);
        		}
        	
        		fuelCost = Double.parseDouble(newValue);
        	}
        	catch(Exception ex) {
        		setText(fCText, fuelCost, 1270.5);
        	}
        });
        pane2.getChildren().add(fCBox);

        VBox aSBox = new VBox();
        aSBox.getChildren().add(addSatellite());
        aSBox.alignmentProperty().set(Pos.CENTER);
        pane2.getChildren().add(aSBox);


    }



    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane animationPane = new Pane();
        SubScene subScene = new SubScene(animationGroup, 1000,700, true,SceneAntialiasing.BALANCED); //Add Z-buffer
        animationPane.getChildren().add(subScene);

        setInputPane();
        StackPane inputPane = new StackPane();
        inputPane.getChildren().add(pane2);
        inputPane.setPrefWidth(200);

        pane.getChildren().addAll(animationPane, inputPane);
        HBox.setHgrow(animationPane,Priority.ALWAYS);

        Scene scene = new Scene(pane);

        camera.setNearClip(1);
        camera.setFarClip(10000);
        subScene.setCamera(camera);
        camera.translateZProperty().set(-1000);

        primaryStage.setTitle("Dyson Sim");
        primaryStage.setScene(scene);
        primaryStage.show();

        subScene.heightProperty().bind(animationPane.heightProperty());
        subScene.widthProperty().bind(animationPane.widthProperty());

        Roation();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
