package org.example.imageprocessoops.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.NoOperationTools.NoOperationTools;


public class LineTools extends NoOperationTools {
    private double firstX, firstY;
    private boolean firstPointSet = false;
    private Color lineColor = Color.BLUE;
    private static final double POINT_RADIUS = 4;

//    @Override
//    public void onMouseMoved(MouseEvent event, ImageWorkSpace imageWorkSpace) {
//
//        if (firstPointSet) {
//            imageWorkSpace.clearOverlay();
//            GraphicsContext gc = imageWorkSpace.getOverlayGraphicsContext();
//
//            // point 1 marker మళ్ళీ draw చేయాలి (clearOverlay తీసేసింది కాబట్టి)
//            gc.setFill(lineColor);
//            gc.fillOval(firstX - POINT_RADIUS, firstY - POINT_RADIUS,
//                    POINT_RADIUS * 2, POINT_RADIUS * 2);
//
//            // temporary preview line — save చేయకూడదు, ప్రతిసారి redraw మాత్రమే
//            gc.setStroke(lineColor);
//            gc.setLineWidth(1.5);
//            gc.strokeLine(firstX, firstY, event.getX(), event.getY());
//        }
//    }
    @Override
    public void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace) {
        GraphicsContext gc = imageWorkSpace.getOverlayGraphicsContext();

        if (!firstPointSet) {
            // ఇది మొదటి click — point 1 save చేసి, marker draw చేయి
            firstX = event.getX();
            firstY = event.getY();
            firstPointSet = true;

            gc.setFill(lineColor);
            gc.fillOval(firstX - POINT_RADIUS, firstY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);
        } else {
            // ఇది రెండో click — point 2, line, distance draw చేయి
            double secondX = event.getX();
            double secondY = event.getY();

            gc.setFill(lineColor);
            gc.fillOval(secondX - POINT_RADIUS, secondY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);

            gc.setStroke(lineColor);
            gc.setLineWidth(3);
            gc.strokeLine(firstX, firstY, secondX, secondY);

            double distance = Math.sqrt(Math.pow(secondX - firstX, 2)
                    + Math.pow(secondY - firstY, 2));
            String distanceText = String.format("%.2f px", distance);

            double midX = (firstX + secondX) / 2;
            double midY = (firstY + secondY) / 2;
            gc.setFill(Color.GREENYELLOW);
            gc.fillText(distanceText, midX + 5, midY - 5);

            // reset — next pair of clicks కి ready
            firstPointSet = false;
        }
    }
        @Override
        public void onMouseMoved(MouseEvent event, ImageWorkSpace imageWorkSpace) {
            if (firstPointSet) {
                imageWorkSpace.clearOverlay();
                GraphicsContext gc = imageWorkSpace.getOverlayGraphicsContext();

                gc.setFill(lineColor);
                gc.fillOval(firstX - POINT_RADIUS, firstY - POINT_RADIUS,
                        POINT_RADIUS * 2, POINT_RADIUS * 2);

                gc.setStroke(lineColor);
                gc.setLineWidth(1.5);
                gc.strokeLine(firstX, firstY, event.getX(), event.getY());
            }
        }
    @Override
    public void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace){

    }

    @Override
    public void onMouseReleased(MouseEvent event, ImageWorkSpace imageWorkSpace){

    }
}
