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

import java.util.Stack;

public class View extends Application implements EventHandler<ActionEvent>{

    private Controller control = new Controller();
    static double costPerSqFootage;
    static double sqFootage;
    static double fuelTons;
    static double productionCost;
    static int satelliteNumber = 1;

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
        animationGroup.getChildren().add(satellite);
        satellite.getTransforms().add(new Translate(12.5,0,125));
        Label launchCost = new Label("Satellite" + satelliteNumber + " Cost: " + control.getLaunchCost(costPerSqFootage,sqFootage,fuelTons,productionCost,satelliteNumber));
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

    private void setInputPane() {

        VBox sPCBox = new VBox();
        Label sPCLabel = new Label("Panel Cost (Per Square Footage)");
        Slider sPCSlider = new Slider(1, 10, 1);
        sPCSlider.setMinorTickCount(1);
        sPCSlider.setShowTickLabels(true);
        sPCSlider.setShowTickMarks(true);
        sPCBox.getChildren().addAll(sPCLabel,sPCSlider);
        Label testLabel = new Label("" + sPCSlider.getValue());
        sPCSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                costPerSqFootage = sPCSlider.getValue();
            }
        });
        pane2.getChildren().add(sPCBox);

        VBox sFBox = new VBox();
        Label sFLabel = new Label("Square Footage");
        Slider sFSlider = new Slider(1, 10, 1);
        sFSlider.setMinorTickCount(1);
        sFSlider.setShowTickLabels(true);
        sFSlider.setShowTickMarks(true);
        sFBox.getChildren().addAll(sFLabel,sFSlider);
        sFSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                sqFootage = sFSlider.getValue();
            }
        });
        pane2.getChildren().add(sFBox);

        VBox pCBox = new VBox();
        Label pCLabel = new Label("Production Cost (Millions) ");
        Slider pCSlider = new Slider(0, 1, .1);
        pCSlider.setMinorTickCount(1);
        pCSlider.setShowTickLabels(true);
        pCSlider.setShowTickMarks(true);
        pCBox.getChildren().addAll(pCLabel,pCSlider);
        pCSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                fuelTons = pCSlider.getValue();
            }
        });
        pane2.getChildren().add(pCBox);

        Label spaceLabel = new Label("");

        VBox fCBox = new VBox();
        Label fCLabel = new Label("Fuel Cost (Tons) ");
        Slider fCSlider = new Slider(1, 10, 1);
        fCSlider.setMinorTickCount(1);
        fCSlider.setShowTickLabels(true);
        fCSlider.setShowTickMarks(true);
        fCBox.getChildren().addAll(fCLabel,fCSlider,spaceLabel);
        fCSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                productionCost = fCSlider.getValue();
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
