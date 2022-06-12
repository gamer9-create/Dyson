package engine.logic.systems.shape;

import java.io.*;

public class Rectangle implements Serializable {
    private float x,y,w,h;

    public Rectangle(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    private boolean point_collision(Rectangle rectangle, float locationX, float locationY) {
        boolean f = false;

        boolean collisionX = false;
        boolean collisionY = false;

        if (locationX > rectangle.getX() && locationX < (rectangle.getX() + rectangle.getW())) {
            collisionX = true;
        }

        if (locationY > rectangle.getY() && locationY < (rectangle.getY() + rectangle.getH())) {
            collisionY = true;
        }

        if (collisionX && collisionY) {
            f = true;
        }

        return f;
    }

    public boolean collision(Rectangle rectangle) {
        boolean v = false;

        if (point_collision(rectangle, x, y)) {
            v = true;
        }

        if (point_collision(rectangle, x + rectangle.w, y)) {
            v = true;
        }

        if (point_collision(rectangle, x, y + rectangle.h)) {
            v = true;
        }

        if (point_collision(rectangle, x + rectangle.w, y + rectangle.h)) {
            v = true;
        }

        return v;
    }

    public Rectangle clone() {
        return new Rectangle(x, y, w, h);
    }

}
