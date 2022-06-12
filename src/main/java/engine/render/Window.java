package engine.render;

import engine.info.*;
import engine.logic.services.gl.*;
import engine.logic.input.*;
import org.joml.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import java.lang.Math;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45.*;

public class Window {

    private static long window;
    private static int lastWidth = 0;
    private static int lastHeight = 0;
    private static Vector4f backgroundColor;

    private static float frameBase;
    private static float beginTime;
    private static float dt;

    private static float lastMouseX;
    private static float lastMouseY;

    private static GLFWVidMode vidMode;

    private static int[] windowFramebuffer;

    public static void initialize() {
        frameBase = System.nanoTime();

        cv();

        if (!glfwInit()) {
            throw new RuntimeException("GLFW ERROR: CANNOT INITIALIZE.");
        }

        window = glfwCreateWindow(lastWidth, lastHeight, Constants.windowTitle, 0, 0);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwSwapInterval(1);

        vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        assert vidMode != null;
        glfwSetWindowPos(window, (vidMode.width()/2)-lastWidth/2, (vidMode.height()/2)-lastHeight/2);

        //Main.initialize();

        glfwSetWindowSizeCallback(window, (window, width, height) -> {
            GLResourceManager.deleteFramebuffer(windowFramebuffer);
            windowFramebuffer = GLResourceCreator.createFramebuffer(width, height);
        });

        backgroundColor = Constants.windowColor;

        windowFramebuffer = GLResourceCreator.createFramebuffer(lastWidth, lastHeight);

        Mouse.inputMousePosition(window);
        Mouse.inputMouseButton(window);

        //glEnable(GL_CULL_FACE);
        //glCullFace(GL_BACK);

        beginTime = getTime();
    }

    private static void cv() {
        String[] s = Constants.windowSize.split("x");
        lastWidth = Integer.parseInt(s[0]);
        lastHeight = Integer.parseInt(s[1]);
    }

    public static long getWindow() {
        return window;
    }

    public static GLFWVidMode getVideoMode() {
        return vidMode;
    }

    private static float getTime() { return (float) ((System.nanoTime() - frameBase) * 1E-9); }

    public static float getDeltaTime() { return dt; }

    public static float getLastMouseX() {
        return lastMouseX;
    }

    public static float getLastMouseY() {
        return lastMouseY;
    }

    public static int[] getWindowFramebuffer() {
        return windowFramebuffer;
    }

    public static Vector4f getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Vector4f backgroundColor) {
        Window.backgroundColor = backgroundColor;
    }

    public static void lockCursor() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public static void unlockCursor() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public static void beginWindow() {
        glfwPollEvents();
    }

    public static void stopWindow() {
        lastMouseX = Mouse.MOUSE_POSITION.x;
        lastMouseY = Mouse.MOUSE_POSITION.y;

        glfwSwapBuffers(window);

        float stopTime = getTime();
        dt = Math.abs(beginTime - stopTime);
        beginTime = stopTime;
    }

    public static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);
    }

    public static void update() {

    }

    public static void dispose() {
        //Main.dispose();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

}
