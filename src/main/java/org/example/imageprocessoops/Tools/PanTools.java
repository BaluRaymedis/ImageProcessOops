package org.example.imageprocessoops.Tools;

import javafx.scene.input.MouseEvent;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.NoOperationTools.NoOperationTools;



public class PanTools extends NoOperationTools{
    private double lastX;
    private double lastY;

    @Override
    public void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace) {
        lastX = event.getX();
        lastY = event.getY();
    }

    @Override
    public void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace) {
        double deltaX = event.getX() - lastX;
        double deltaY = event.getY() - lastY;
        imageWorkSpace.pan(deltaX, deltaY);
        lastX = event.getX();
        lastY = event.getY();
    }
}
