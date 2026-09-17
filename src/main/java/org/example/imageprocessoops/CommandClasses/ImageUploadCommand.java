package org.example.imageprocessoops.CommandClasses;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.imageprocessoops.CommandInterface.Command;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class ImageUploadCommand implements Command {
    private final ImageWorkSpace imageWorkSpace;
    private final Stage stage;

    public ImageUploadCommand(ImageWorkSpace imageWorkSpace, Stage stage){
        this.imageWorkSpace = imageWorkSpace;
        this.stage = stage;
    }
    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp","*.png","*.jpg","*.jpeg"));
        File file = fileChooser.showOpenDialog(stage);
        if(file == null) {
            return;
        }
        Mat mat = Imgcodecs.imread(file.getAbsolutePath());
        try {
            if (mat.empty()) {
                throw new IllegalArgumentException("Unable to read image: " + file);
            }
            imageWorkSpace.uploadImage(new Image(file.toURI().toString()));
        } finally {
            mat.release();
        }

    }
}
