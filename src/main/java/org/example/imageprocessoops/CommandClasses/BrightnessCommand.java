package org.example.imageprocessoops.CommandClasses;

import javafx.scene.image.Image;
import org.example.imageprocessoops.CommandInterface.Command;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.OpenCvUtil.OpenCvConverter;
import org.opencv.core.Mat;

public class BrightnessCommand implements Command {
    private final ImageWorkSpace imageWorkSpace;
    private double sliderValue = 50;

    public BrightnessCommand(ImageWorkSpace imageWorkSpace){
        this.imageWorkSpace = imageWorkSpace;
    }

    public void setSliderValue(double sliderValue){

        this.sliderValue = sliderValue;
    }
    @Override
    public void execute() {
        Image originalImage = imageWorkSpace.getOriginalImage();
        if(originalImage == null){
            System.out.println("Image is not uploaded for brightness");
            return;
        }
        double brightnessOffset = (sliderValue - 50)*2;
        Mat mat = OpenCvConverter.toMat(originalImage);
        Mat adjusted = new Mat();
        mat.convertTo(adjusted,-1, 1.0,brightnessOffset);

        Image resultImage = OpenCvConverter.matToFxImage(adjusted);
        imageWorkSpace.getImageView().setImage(resultImage);
    }
}
