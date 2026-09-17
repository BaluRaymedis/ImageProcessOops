package org.example.imageprocessoops.ImageTools;

import javafx.scene.input.MouseEvent;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
/**
 * Interface representing image tools that can be used in an image workspace.
 * @ToolInterface : ImageTools
 * @Author : Balaji
 * @Description : This interface defines the methods that must be implemented by any image tool. It includes methods for activating the tool and handling mouse events such as pressing, dragging, and releasing.
 */
public interface ImageTools {

    //toolActivate method is called when the tool is selected or activated in the image workspace.
    void toolActivate(ImageWorkSpace imageWorkSpace);
    void onMouseMoved(MouseEvent event, ImageWorkSpace imageWorkSpace);
    //onMousePressed method is called when the mouse is pressed while the tool is active. It takes a MouseEvent and an ImageWorkSpace as parameters.
    void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace);

    //onMouseDragged method is called when the mouse is dragged while the tool is active. It takes a MouseEvent and an ImageWorkSpace as parameters.
    void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace);

    //onMouseReleased method is called when the mouse is released while the tool is active. It takes a MouseEvent and an ImageWorkSpace as parameters.
    void onMouseReleased(MouseEvent event, ImageWorkSpace imageWorkSpace);
}
