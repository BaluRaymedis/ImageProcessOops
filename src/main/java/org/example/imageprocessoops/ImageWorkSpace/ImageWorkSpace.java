package org.example.imageprocessoops.ImageWorkSpace;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageWorkSpace {
    private final ImageView imageView;
    private final Canvas canvas;
    private final StackPane imageContainer;

    private Image originalImage;
    private double rotationAngle = 0;
    private double zoomFactor = 1.0;
    private boolean flippedH = false;
    private boolean flippedV = false;

    public ImageWorkSpace(ImageView imageView, Canvas canvas, StackPane container) {
        this.imageView = imageView;
        this.canvas = canvas;
        this.imageContainer = container;

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(container.widthProperty());
        clip.heightProperty().bind(container.heightProperty());
        container.setClip(clip);
    }

    public Image captureSnapshot(){
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageContainer.snapshot(params,null);
    }
    private void syncOverlayTransform() {
        canvas.setTranslateX(imageView.getTranslateX());
        canvas.setTranslateY(imageView.getTranslateY());
        canvas.setRotate(rotationAngle);
        canvas.setScaleX(zoomFactor * (flippedH ? -1 : 1));
        canvas.setScaleY(zoomFactor * (flippedV ? -1 : 1));
    }
    public void uploadImage(Image image){
        this.originalImage = image;
        imageView.setImage(image);
        fitToContainer();
    }
    public void fitToContainer(){
        imageView.setFitWidth(imageContainer.getWidth());
        imageView.setFitHeight(imageContainer.getHeight());
        zoomFactor=1.0;
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        rotationAngle=0;
        imageView.setRotate(0);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        flippedH = false;

        syncOverlayTransform();
    }
    public void zoom(double zoomValue){
        zoomFactor = Math.max(0.1,Math.min(8.0,zoomFactor+zoomValue));
        imageView.setFitWidth(originalImage.getWidth() * zoomFactor);
        imageView.setFitHeight(originalImage.getHeight() * zoomFactor);
        syncOverlayTransform();
    }
    public void pan(double x, double y){
        imageView.setTranslateX(imageView.getTranslateX()+x);
        imageView.setTranslateY(imageView.getTranslateY()+y);


        syncOverlayTransform();
    }
    public void rotate(double angle){
        rotationAngle += angle;
        imageView.setRotate(rotationAngle);
        syncOverlayTransform();
    }
    public void antiRotate(double angle){
        rotationAngle -= angle;
        imageView.setRotate(rotationAngle);
        syncOverlayTransform();
    }
    public void hFlip(){
        imageView.setScaleX(imageView.getScaleX()*-1);
        flippedH = !flippedH;
        syncOverlayTransform();
    }
//    public void vFlip(){
//        imageView.setScaleY(imageView.getScaleY()*-1);
//    }
    public void applyBrightness(double brightness){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(brightness);

        imageView.setEffect(colorAdjust);
    }

    public void applyContrast(double contrast){
        ColorAdjust colorAdjust = new ColorAdjust();

        colorAdjust.setContrast(contrast);
        imageView.setEffect(colorAdjust);
    }

    public GraphicsContext getOverlayGraphicsContext(){
        return canvas.getGraphicsContext2D();
    }

    public void clearOverlay(){
        GraphicsContext gc = getOverlayGraphicsContext();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void reset(){
        imageView.setImage(originalImage);
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        imageView.setRotate(0);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        imageView.setEffect(null);
        rotationAngle = 0;
        zoomFactor = 1.0;
        flippedH=false;
        fitToContainer();
       clearOverlay();
       syncOverlayTransform();
    }
    public Image getOriginalImage(){
        return originalImage;
    }
    public ImageView getImageView(){
        return imageView;
    }

}
