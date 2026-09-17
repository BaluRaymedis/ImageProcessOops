package org.example.imageprocessoops.ToolManager;

import javafx.scene.canvas.Canvas;
import org.example.imageprocessoops.ImageTools.ImageTools;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;

public class ToolManager {
    private ImageTools currentTool;
    private final ImageWorkSpace imageWorkSpace;

    public ToolManager(ImageWorkSpace imageWorkSpace, Canvas canvas) {
        this.imageWorkSpace = imageWorkSpace;
        canvas.setMouseTransparent(false);
        canvas.setOnMousePressed(event->{

                currentTool.onMousePressed(event, imageWorkSpace);

        });
        canvas.setOnMouseDragged(event->{

                currentTool.onMouseDragged(event, imageWorkSpace);

        });
        canvas.setOnMouseReleased(event->{

                currentTool.onMouseReleased(event, imageWorkSpace);

        });

    }

    public void setTool(ImageTools tools){
        this.currentTool = tools;

            tools.toolActivate(imageWorkSpace);

    }
}
