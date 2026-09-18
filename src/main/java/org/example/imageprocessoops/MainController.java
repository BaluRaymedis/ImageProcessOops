package org.example.imageprocessoops;

import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.imageprocessoops.CommandClasses.*;
import org.example.imageprocessoops.CommandInterface.Command;

import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.ToolManager.ToolManager;
import org.example.imageprocessoops.Tools.*;



public class MainController {
    @FXML
    private StackPane imageContainer;
    @FXML
    private Label sliderLabelValue;
    @FXML
    private ImageView imageView;
    @FXML
    private Canvas canvasImage;
    @FXML
    private MFXSlider slider;
    @FXML
    private HBox sliderHbox;
    @FXML
    private VBox buttonsBox;
    @FXML
    private VBox imageListBox;

    private boolean showingButtons = true;

    private ImageWorkSpace imageWorkSpace;
    private ToolManager toolManager;

    private Command imageUploadCommand;
    private Command exitCommand;
    private Command minimizeCommand;
    private BrightnessCommand brightnessCommand;
    private Command saveImageCommand;

    private final ObservableList<Image> savedImagesList = FXCollections.observableArrayList();
    @FXML
    public void toggleFlip(ActionEvent actionEvent) {
        VBox currentView = showingButtons ? buttonsBox : imageListBox;
        VBox newView = showingButtons ? imageListBox : buttonsBox;

        RotateTransition hideRotate = new RotateTransition(Duration.millis(500),currentView);
        hideRotate.setAxis(Rotate.Y_AXIS);
        hideRotate.setFromAngle(0);
        hideRotate.setToAngle(90);

        RotateTransition showRotate = new RotateTransition(Duration.millis(500),newView);
        showRotate.setAxis(Rotate.Y_AXIS);
        showRotate.setFromAngle(-90);
        showRotate.setToAngle(0);

        hideRotate.setOnFinished(event -> {
            currentView.setVisible(false);
            newView.setVisible(true);
            showRotate.play();
        });

        hideRotate.play();
        showingButtons = !showingButtons;
    }

    @FXML
    public void initialize() {
        imageWorkSpace = new ImageWorkSpace(imageView, canvasImage, imageContainer);
        toolManager = new ToolManager(imageWorkSpace, canvasImage);

        exitCommand = new ExitCommand();
        brightnessCommand = new BrightnessCommand(imageWorkSpace);


//        angleTool = new AngleMeasurementTool(angle -> angleLabel.setText(String.format("Angle: %.2f°", angle)));
//        canvasImage.setOnMouseClicked(e ->
//                angleTool.addPoint(new Point2D(e.getX(), e.getY()))
//        );
    }

    public void attachStage(Stage stage) {
        imageUploadCommand = new ImageUploadCommand(imageWorkSpace, stage);
        minimizeCommand = new MinimizeCommand(stage);
        saveImageCommand = new SaveCommand(imageWorkSpace,stage);
    }
    public void miniBtn(ActionEvent actionEvent) {
        minimizeCommand.execute();
    }

    public void exitBtn(ActionEvent actionEvent) {
        exitCommand.execute();
    }

    public void uploadImageBtn(ActionEvent actionEvent) {
        imageUploadCommand.execute();
    }

    public void fitBtn(ActionEvent actionEvent) {
        toolManager.setTool(new FitTools());
    }

    public void zoomBtn(ActionEvent actionEvent) {
        toolManager.setTool(new ZoomTools());
    }

    public void panBtn(ActionEvent actionEvent) {
        toolManager.setTool(new PanTools());
    }

    public void lineBtn(ActionEvent actionEvent) {
        toolManager.setTool(new LineTools());
    }

    public void rotateBtn(ActionEvent actionEvent) {
        toolManager.setTool(new RotateTools());
    }

    public void antiRotateBtn(ActionEvent actionEvent) {
        toolManager.setTool(new AntiRotateTools());
    }

    public void brightnessBtn(ActionEvent actionEvent) {
        sliderHbox.setVisible(true);
        slider.setValue(50);
        slider.setOnMouseReleased(e->{
            brightnessCommand.setSliderValue(slider.getValue());
            brightnessCommand.execute();
        });
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double val = newVal.doubleValue();
            sliderLabelValue.setText(String.format("%.0f", newVal.doubleValue()));
            if(val < 50){
                sliderLabelValue.setTextFill(Color.web("#FF0000"));
            }else {
                sliderLabelValue.setTextFill(Color.web("#0000CD"));
            }
        });
    }

    public void contrastBtn(ActionEvent actionEvent) {
    }

    public void flipBtn(ActionEvent actionEvent) {
        toolManager.setTool(new FlipTools());
    }

    public void angleBtn(ActionEvent actionEvent) {
        toolManager.setTool(new AngleTools());
    }

    public void saveBtn(ActionEvent actionEvent) {
//        saveImageCommand.execute();

        Image snapshot = imageWorkSpace.captureSnapshot();
        savedImagesList.add(snapshot);
        ImageView thumbnail = new ImageView(snapshot);
        thumbnail.setFitWidth(180);
        thumbnail.setFitHeight(180);
        thumbnail.setPreserveRatio(true);
        thumbnail.setOnMouseClicked(e->imageWorkSpace.uploadImage(snapshot));
        imageListBox.getChildren().add(thumbnail);
    }

    public void ellipseBtn(ActionEvent actionEvent) {
    }

    public void resetBtn(ActionEvent actionEvent) {
        toolManager.setTool(new ResetTools());
    }

    public void deleteBtn(ActionEvent actionEvent) {
    }
}
