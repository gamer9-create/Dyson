package engine.logic.services.image;

import java.nio.*;

public class ImageResource {
    private ByteBuffer texture;
    private IntBuffer width, height, channels;
    private String texName;

    public ImageResource(ByteBuffer texture, IntBuffer width, IntBuffer height, IntBuffer channels, String texName) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.channels = channels;
        this.texName = texName;
    }

    public ByteBuffer getTexture() {
        return texture;
    }

    public IntBuffer getChannels() {
        return channels;
    }

    public IntBuffer getWidth() {
        return width;
    }

    public IntBuffer getHeight() {
        return height;
    }

    public void setTexture(ByteBuffer texture) {
        this.texture = texture;
    }

    public void setWidth(IntBuffer width) {
        this.width = width;
    }

    public void setHeight(IntBuffer height) {
        this.height = height;
    }

    public void setChannels(IntBuffer channels) {
        this.channels = channels;
    }

    public String getTexName() {
        return texName;
    }

    public void setTexName(String texName) {
        this.texName = texName;
    }
}
