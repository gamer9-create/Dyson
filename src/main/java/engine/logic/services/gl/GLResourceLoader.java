package engine.logic.services.gl;

import engine.info.*;
import engine.logic.services.image.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class GLResourceLoader {

    public static int[] createTextureResource(ImageResource resource) {
        int texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        if (resource.getTexture() != null) {
            if (resource.getChannels().get(0) == 3) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, resource.getWidth().get(0), resource.getHeight().get(0),
                        0, GL_RGB, GL_UNSIGNED_BYTE, resource.getTexture());
            } else if (resource.getChannels().get(0) == 4) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, resource.getWidth().get(0), resource.getHeight().get(0),
                        0, GL_RGBA, GL_UNSIGNED_BYTE, resource.getTexture());
            } else {
                System.out.println("Unable to load (channels issue) texture.");
            }
        } else {
            System.out.println("Unable to load texture.");
        }

        stbi_image_free(resource.getTexture());

        int[] returnV = new int[Constants.textureSizeBuffer];
        returnV[0] = texID;
        returnV[1] = resource.getWidth().get(0);
        returnV[2] = resource.getHeight().get(0);

        return returnV;
    }

}
