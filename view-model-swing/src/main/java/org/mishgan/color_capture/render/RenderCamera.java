package org.mishgan.color_capture.render;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RenderCamera {
    private double x;
    private double y;
    private double scale = 1.0;
    private int width;
    private int height;

    public AffineTransform getTransform() {
        AffineTransform transform = new AffineTransform();
        transform.translate(x + width / 2, y + height / 2);
        transform.scale(scale, scale);
        return transform;
    }

    public void increaseScale() {
        if (scale <= 1.9) {
            scale += 0.1;
        }
    }

    public void decreaseScale() {
        if (scale >= 0.6) {
            scale -= 0.1;
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point getPosition() {
        return new Point((int)x, (int)y);
    }

    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
