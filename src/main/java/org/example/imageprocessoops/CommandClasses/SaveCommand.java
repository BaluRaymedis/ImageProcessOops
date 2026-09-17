package org.example.imageprocessoops.CommandClasses;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageprocessoops.CommandInterface.Command;
import org.example.imageprocessoops.FileChoose.FileChooseFactory;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.OpenCvUtil.OpenCvConverter;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class SaveCommand implements Command {
    private final ImageWorkSpace imageWorkSpace;
    private final Stage stage;

    public SaveCommand(ImageWorkSpace imageWorkSpace, Stage stage){
        this.imageWorkSpace = imageWorkSpace;
        this.stage = stage;
    }
    @Override
    public void execute(){
        ImageView imageView = imageWorkSpace.getImageView();
        if(imageView.getImage() == null){
            System.out.println("No Image Loaded to save.");
            return;
        }

        FileChooser fileChooser = FileChooseFactory.createImageFileChooser("Save Image", "edited_image.png");
        File saveFile = fileChooser.showSaveDialog(stage);

        if (saveFile == null) {
            return;   // user cancel చేశారు
        }

        // Rotate/flip/zoom/effects అన్నీ కలిపి, ప్రస్తుతం కనిపిస్తున్నదాన్ని "photograph" తీయడం
        WritableImage snapshot = imageView.snapshot(new SnapshotParameters(), null);

        Mat mat = OpenCvConverter.toMat(snapshot);
        boolean success = Imgcodecs.imwrite(saveFile.getAbsolutePath(), mat);

        if (success) {
            System.out.println("Image saved to: " + saveFile.getAbsolutePath());
        } else {
            System.out.println("Failed to save image.");
        }
    }
}
