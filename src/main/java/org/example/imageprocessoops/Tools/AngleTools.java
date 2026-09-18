package org.example.imageprocessoops.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.NoOperationTools.NoOperationTools;

public class AngleTools extends NoOperationTools {
    private double firstX, firstY;      // point 1 (arm end)
    private double vertexX, vertexY;    // point 2 (vertex)
    private int clickCount = 0;         // ఎన్ని points click అయ్యాయో track చేయడానికి

    private Color lineColor = Color.BLUE;
    private static final double POINT_RADIUS = 4;

    @Override
    public void onMousePressed(MouseEvent event, ImageWorkSpace imageWorkSpace) {
        GraphicsContext gc = imageWorkSpace.getOverlayGraphicsContext();

        if (clickCount == 0) {

            firstX = event.getX();
            firstY = event.getY();
            clickCount = 1;

            gc.setFill(lineColor);
            gc.fillOval(firstX - POINT_RADIUS, firstY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);

        } else if (clickCount == 1) {

            vertexX = event.getX();
            vertexY = event.getY();
            clickCount = 2;

            gc.setFill(lineColor);
            gc.fillOval(vertexX - POINT_RADIUS, vertexY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);

            gc.setStroke(lineColor);
            gc.setLineWidth(2);
            gc.strokeLine(firstX, firstY, vertexX, vertexY);

        } else {

            double thirdX = event.getX();
            double thirdY = event.getY();

            gc.setFill(lineColor);
            gc.fillOval(thirdX - POINT_RADIUS, thirdY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);

            gc.setStroke(lineColor);
            gc.setLineWidth(2);
            gc.strokeLine(vertexX, vertexY, thirdX, thirdY);

            double angle = calculateAngle(firstX, firstY, vertexX, vertexY, thirdX, thirdY);
            String angleText = String.format("%.2f°", angle);

            gc.setFill(Color.GREENYELLOW);
            gc.fillText(angleText, vertexX + 10, vertexY - 10);

            // reset — next angle measurement కి ready
            clickCount = 0;
        }
    }

    @Override
    public void onMouseMoved(MouseEvent event, ImageWorkSpace imageWorkSpace) {
        if (clickCount == 0){
            return;
        }

        imageWorkSpace.clearOverlay();
        GraphicsContext gc = imageWorkSpace.getOverlayGraphicsContext();


        gc.setFill(lineColor);
        gc.fillOval(firstX - POINT_RADIUS, firstY - POINT_RADIUS,
                POINT_RADIUS * 2, POINT_RADIUS * 2);

        if (clickCount == 1) {

            gc.setStroke(lineColor);
            gc.setLineWidth(1.5);
            gc.strokeLine(firstX, firstY, event.getX(), event.getY());

        } else if (clickCount == 2) {

            gc.fillOval(vertexX - POINT_RADIUS, vertexY - POINT_RADIUS,
                    POINT_RADIUS * 2, POINT_RADIUS * 2);

            gc.setStroke(lineColor);
            gc.setLineWidth(2);
            gc.strokeLine(firstX, firstY, vertexX, vertexY);

            gc.setLineWidth(1.5);
            gc.strokeLine(vertexX, vertexY, event.getX(), event.getY());


            double liveAngle = calculateAngle(firstX, firstY, vertexX, vertexY, event.getX(), event.getY());
            gc.setFill(Color.GREENYELLOW);
            gc.fillText(String.format("%.2f°", liveAngle), vertexX + 10, vertexY - 10);
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event, ImageWorkSpace imageWorkSpace) {
    }

    @Override
    public void onMouseReleased(MouseEvent event, ImageWorkSpace imageWorkSpace) {
    }

    private double calculateAngle(double x1, double y1, double vx, double vy, double x2, double y2) {
        double v1x = x1 - vx, v1y = y1 - vy;
        double v2x = x2 - vx, v2y = y2 - vy;

        double angle1 = Math.atan2(v1y, v1x);
        double angle2 = Math.atan2(v2y, v2x);

        double angleDeg = Math.toDegrees(angle2 - angle1);
        if (angleDeg < 0) angleDeg += 360;
        return angleDeg;
    }
}
