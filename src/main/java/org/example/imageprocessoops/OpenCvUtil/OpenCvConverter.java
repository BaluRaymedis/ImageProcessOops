package org.example.imageprocessoops.OpenCvUtil;

import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;

public class OpenCvConverter {
    public static Mat toMat(Image image){
        BufferedImage bufferImage = SwingFXUtils.fromFXImage(image, null);
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferImage, "png", baos);
            MatOfByte byteMat = new MatOfByte(baos.toByteArray());
            return Imgcodecs.imdecode(byteMat,Imgcodecs.IMREAD_UNCHANGED);
        }catch (Exception e){
            throw new RuntimeException("Image to mat conversion failed",e);
        }
    }

    public static Image matToFxImage(Mat mat){
        Mat converted = new Mat();

        if (mat.channels() == 1) {
            Imgproc.cvtColor(mat, converted, Imgproc.COLOR_GRAY2BGR);
        } else if (mat.channels() == 4) {
            Imgproc.cvtColor(mat, converted, Imgproc.COLOR_BGRA2BGR);
        } else {
            converted = mat; // already 3-channel BGR, no conversion needed
        }

        BufferedImage bufferedImage = new BufferedImage(
                converted.width(), converted.height(), BufferedImage.TYPE_3BYTE_BGR);

        byte[] imageData = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        converted.get(0, 0, imageData);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
