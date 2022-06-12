package engine.render.graphics2d;

import engine.info.*;
import engine.logic.services.gl.*;
import engine.render.*;
import org.lwjgl.*;

import java.nio.*;
import java.util.*;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {
    private static int
            shader,
            vbo,
            tbo,
            ebo,
            lbo,

            nextModelIndex,

            samplerUniformLoc,
            backgroundUniformLoc,
            projectionUniformLoc;

    private static float[] vertexArray, textureCoordinateArray;

    private static int[] samplers;

    private static InstancedModel[] models;

    private static HashMap<Integer, int[]> textures;

    public static int[] createVertexBufferObject(float[] vertices, float[] textureCoordinates, int[] element, float[] lights) {
        int v = glGenBuffers();
        int t = glGenBuffers();
        int e = glGenBuffers();
        int l = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, v);
        glBufferData(GL_ARRAY_BUFFER, (vertices), GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, t);
        glBufferData(GL_ARRAY_BUFFER, (textureCoordinates), GL_DYNAMIC_DRAW);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, l);
        glBufferData(GL_ARRAY_BUFFER, lights, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, e);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, (element), GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        return new int[] {v,t,e,l};
    }

    public static void initialize() {
        System.out.println("Initializing Dyson Renderer" + "\n");

        shader = GLResourceCreator.createShader("resources/shaders/" + Constants.rendererShader + ".vs", "resources/shaders/" + Constants.rendererShader + ".fs");

        int amount = Constants.rendererObjectLimit;

        textures = new HashMap<>();

        models = new InstancedModel[amount];
        nextModelIndex = 0;

        vertexArray = new float[amount * 12];
        textureCoordinateArray = new float[amount * 8];

        int[] bufferObject = createVertexBufferObject(vertexArray, textureCoordinateArray, Constants.rendererElementArray, new float[1]);

        vbo = bufferObject[0];
        tbo = bufferObject[1];
        ebo = bufferObject[2];
        lbo = bufferObject[3];

        projectionUniformLoc = glGetUniformLocation(shader, "projection");
        backgroundUniformLoc = glGetUniformLocation(shader, "background");
        samplerUniformLoc = glGetUniformLocation(shader, "sampler");

        samplers = new int[Constants.rendererSamplerLimit];
        for (int i = 0; i < samplers.length; i++) {
            samplers[i] = i;
        }

        System.out.println("LWJGL Version: " + Version.getVersion() + " | LWJGL BUILD: " + Version.BUILD_TYPE + "\n");

        System.out.println("OpenGL Information");

        int[] textureSlots = new int[1];
        glGetIntegerv(GL_MAX_TEXTURE_IMAGE_UNITS, textureSlots);
        System.out.println("Max Texture Slots: " + textureSlots[0]);
    }

    public static void renderModels(InstancedModel[] models, int length) {
        for (int i = 0; i < length; i++) {
            InstancedModel model = models[i];
            if (model != null) {
                renderModel(model);
            }
        }
    }

    public static void renderModel(InstancedModel model) {
        models[nextModelIndex] = model;
        nextModelIndex += 1;
    }

    public static void popImageInSlot(int slot, int[] image) {
        GLResourceManager.switchTextureSlot(slot);
        GLResourceManager.bindTexture(image);
    }

    /*
    @Deprecated
    public static void renderModelsIfVisible(InstancedModel[] models, int windowWidth, int windowHeight, int offsetX, int offsetY) {
        for (InstancedModel model : models) {
            if (model != null) {
                renderModelIfVisible(model, windowWidth, windowHeight, offsetX, offsetY);
            }
        }
    }

    @Deprecated
    public static void renderModelIfVisible(InstancedModel model, int windowWidth, int windowHeight, int offsetX, int offsetY) {
        int v = 12;
        if (model.getRectangle().getX() > ((-windowWidth/2) - (offsetX / v)) && model.getRectangle().getX() < ((windowWidth/2) - (offsetX / v))
        && model.getRectangle().getY() < (windowHeight/2) && model.getRectangle().getY() > (-windowHeight/2)) {
            renderModel(model);
      }
     */

    public static void bufferToScreen(FloatBuffer matrix) {
        GLResourceManager.bindShader(shader);

        int shaderlocation = samplerUniformLoc;
        glUniform1iv(shaderlocation, samplers);

        shaderlocation = backgroundUniformLoc;
        glUniform4f(shaderlocation, Window.getBackgroundColor().x, Window.getBackgroundColor().y, Window.getBackgroundColor().z, Window.getBackgroundColor().w);

        shaderlocation = projectionUniformLoc;
        glUniformMatrix4fv(shaderlocation, false, matrix);

        Arrays.fill(vertexArray, 0);
        for (int i = 0; i < nextModelIndex; i++) {
            if (models[i] != null && models[i].getVertices() != null) {
                float[] array = models[i].getVertices();
                System.arraycopy(array, 0, vertexArray, i * Constants.rendererVertexArraySize, array.length);
            }
        }

        Arrays.fill(textureCoordinateArray, 0);
        for (int i = 0; i < nextModelIndex; i++) {
            if (models[i] != null && models[i].getTextureCoordinates() != null) {
                float[] array = models[i].getTextureCoordinates();
                System.arraycopy(array, 0, textureCoordinateArray, i * Constants.rendererTextureCoordinateArraySize, array.length);
            }
        }

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertexArray);

        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferSubData(GL_ARRAY_BUFFER, 0, textureCoordinateArray);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glDrawElements(GL_TRIANGLES, Constants.rendererElementArray.length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        Arrays.fill(models, null);
        nextModelIndex = 0;
    }

    public static void update() {

    }

    public static void dispose() {
        glDeleteBuffers(vbo);
        glDeleteBuffers(tbo);
        glDeleteBuffers(ebo);
        glDeleteShader(shader);
    }
}
