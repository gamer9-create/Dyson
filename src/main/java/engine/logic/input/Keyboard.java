package engine.logic.input;

import engine.render.*;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {

    public static boolean isKeyDown(int key) {
        return glfwGetKey(Window.getWindow(), key) == 1;
    }

    public static boolean isKeyUp(int key) {
        return glfwGetKey(Window.getWindow(), key) == 0;
    }

    public static boolean isKeyDown(long window, int key) {
        return glfwGetKey(window, key) == 1;
    }

    public static boolean isKeyUp(long window, int key) {
        return glfwGetKey(window, key) == 0;
    }

}
