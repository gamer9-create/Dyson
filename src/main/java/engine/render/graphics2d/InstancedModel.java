package engine.render.graphics2d;

import engine.logic.services.gl.GLResourceLoader;
import engine.logic.services.image.*;
import engine.logic.systems.shape.Rectangle;
import engine.logic.systems.shape.ShapeProcessing;

import java.io.*;

public class InstancedModel implements Serializable {

    private float[] vertices;
    private float[] textureCoordinates;

    private Rectangle rectangle;
    private int[] texture;

    private String texName = "";

    public InstancedModel(float[] vertices, float[] textureCoordinates, Rectangle rectangle) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.rectangle = rectangle;
    }

    public InstancedModel(float[] vertices, float[] textureCoordinates) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
    }

    public InstancedModel(float[] vertices, Rectangle rectangle) {
        this.vertices = vertices;
        this.rectangle = rectangle;
    }

    public InstancedModel(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.vertices = ShapeProcessing.generateSpriteShape(this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getW(), this.rectangle.getH());
        updateVertices();
    }
    public InstancedModel(Rectangle rectangle, float[] textureCoordinates) {
        this.rectangle = rectangle;
        this.vertices = ShapeProcessing.generateSpriteShape(this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getW(), this.rectangle.getH());
        this.textureCoordinates = textureCoordinates;
        updateVertices();
    }

    public void updateVertices() {
        ShapeProcessing.translateSpriteShape(this.vertices, (int)this.rectangle.getX(), (int)this.rectangle.getY(), (int)this.rectangle.getW(), (int)this.rectangle.getH());
    }

    public void loadModelImage(ImageResource imageResource) {
        this.texture = GLResourceLoader.createTextureResource(imageResource);
    }

    public void loadModelImage(ImageResource imageResource, String texName) {
        this.texName = texName;
        this.loadModelImage(imageResource);
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public float[] getTextureCoordinates() {
        return textureCoordinates;
    }

    public void setTextureCoordinates(float[] textureCoordinates) {
        this.textureCoordinates = textureCoordinates;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int[] getTexture() {
        return texture;
    }

    public void setTexture(int[] texture) {
        this.texture = texture;
    }

    public void setTexture(int[] texture, String texName) {
        this.texName = texName;
        this.setTexture(texture);
    }

    public String getTexName() {
        return texName;
    }

    public void setTexRenderID(int id) {
        getVertices()[2] = id;
        getVertices()[5] = id;
        getVertices()[8] = id;
        getVertices()[11] = id;
    }
}
