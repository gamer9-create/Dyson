package engine.logic.services.gl;

import engine.logic.services.ResourceLoader;

import static org.lwjgl.opengl.GL30.*;

public class GLResourceCreator {

    public static int[] createFramebuffer(int width, int height) {
        // Generate framebuffer
        int fboID = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);

        // Create the texture to render the data to, and attach it to our framebuffer
        int texture = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, texture);

        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height,
                0, GL_RGB, GL_UNSIGNED_BYTE, 0);

        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D,
                texture, 0);

        // Create renderbuffer store the depth info
        int rboID = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, rboID);
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT32, width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, rboID);

        if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            System.out.println("Error: Framebuffer is not complete");
        }
        glBindFramebuffer(GL_FRAMEBUFFER, 0);

        return new int[] {fboID, rboID, texture, width, height};
    }

    public static int createShader(String vsFilename, String fsFilename) {
        int shader = glCreateProgram();

        int vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, ResourceLoader.loadFile(vsFilename));
        glCompileShader(vertex);
        if (glGetShaderi(vertex, GL_COMPILE_STATUS) != 1) {
            throw new RuntimeException(glGetShaderInfoLog(vertex));
        }

        int fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragment, ResourceLoader.loadFile(fsFilename));
        glCompileShader(fragment);
        if (glGetShaderi(fragment, GL_COMPILE_STATUS) != 1) {
            throw new RuntimeException(glGetShaderInfoLog(fragment));
        }

        glAttachShader(shader, vertex);
        glAttachShader(shader, fragment);

        glBindAttribLocation(shader, 0,"vertices");
        glBindAttribLocation(shader, 1, "textures");
        glBindAttribLocation(shader, 2, "light");

        glLinkProgram(shader);

        return shader;
    }

}
