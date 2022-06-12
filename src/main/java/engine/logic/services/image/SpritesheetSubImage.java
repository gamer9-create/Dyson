package engine.logic.services.image;

import engine.logic.systems.shape.Rectangle;

public class SpritesheetSubImage {
    private Rectangle imageLocation;

    public SpritesheetSubImage(Rectangle imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Rectangle getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(Rectangle imageLocation) {
        this.imageLocation = imageLocation;
    }

    public float[] generateTextureCoordinates(int imageW, int imageH) {
        float x_Cal = ((1f / imageW) * imageLocation.getX()) + ((1f / imageW) * imageLocation.getW());
        float y_Cal = ((1f / imageH) * imageLocation.getY()) + ((1f / imageH) * imageLocation.getH());

        return new float[] {
                (1f/imageW)*imageLocation.getX(), y_Cal,
                x_Cal, y_Cal,
                x_Cal, (1f/imageH)*imageLocation.getY(),
                (1f/imageW)*imageLocation.getX(), (1f/imageH)*imageLocation.getY()
        };
    }
}
