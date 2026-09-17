package org.example.imageprocessoops.NoOperationTools;

import javafx.scene.input.MouseEvent;
import org.example.imageprocessoops.ImageTools.ImageTools;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;

/**
 * @author: Balaji
 * @Title: NoOperationTools
 * @Description: This class implements the ImageTools interface and provides empty implementations for its methods.
 * It serves as a no-operation tool that does not perform any actions when the methods are called.
 * @Date: 2024-06-10
 * NoOperationTools is a class that implements the ImageTools interface.
 * It provides empty implementations for the methods defined in the interface,
 * effectively making it a no-operation tool.
 */
public class NoOperationTools implements ImageTools {

    @Override
    public void toolActivate(ImageWorkSpace imageWorkSpace) {

    }
    @Override
   public void onMouseMoved(MouseEvent event, ImageWorkSpace imageWorkSpace){

    }

    @Override
    public void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace) {

    }

    @Override
    public void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace) {

    }

    @Override
    public void onMouseReleased(MouseEvent event, ImageWorkSpace imageWorkSpace) {

    }


}
