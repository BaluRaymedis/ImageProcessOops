package org.example.imageprocessoops.Tools;

import javafx.scene.input.MouseEvent;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.NoOperationTools.NoOperationTools;
/**
 * @author : Balaji
 * @ToolName : ZoomTools
 * @Description : This class is responsible for implementing the zoom functionality in the image workspace.
 * It extends the NoOperationTools class and overrides the onMousePressed and onMouseDragged methods to handle mouse events for zooming in and out of the image.
 * The zoom factor is calculated based on the vertical movement of the mouse, and the image workspace is updated accordingly.
 * ZoomTools class that extends NoOperationTools and implements zoom functionality.
 */
public class ZoomTools extends NoOperationTools{

    private double lastY;

    @Override
    public void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace){

        lastY = event.getY();
        System.out.println("ZoomTools: onMousePressed - lastY: " + lastY);
    }

    @Override
    public void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace) {

        double deltaY = event.getY() - lastY;
        double zoomFactor =(deltaY * 0.01);
        imageWorkSpace.zoom(zoomFactor);
        lastY = event.getY();
    }
}
