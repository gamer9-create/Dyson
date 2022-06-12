package engine.logic.input;

import org.joml.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.awt.*;
import static org.lwjgl.glfw.GLFW.*;

public class Mouse {
    public static Vector2f MOUSE_POSITION;
    public static int MOUSE_BUTTON;
    public static GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            MOUSE_POSITION.x = (float) xpos;
            MOUSE_POSITION.y = (float) ypos;
        }
    };
    public static GLFWMouseButtonCallback cursorButtonCallback = new GLFWMouseButtonCallback() {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            MOUSE_BUTTON = button;
        }
    };

    static {
        MOUSE_POSITION = new Vector2f(0, 0);
        MOUSE_BUTTON = -1;
    }

    public static void inputMousePosition(long window) {
        glfwSetCursorPosCallback(window, cursorPosCallback);
    }

    public static void inputMouseButton(long window) {
        MOUSE_BUTTON = -1;
        glfwSetMouseButtonCallback(window, cursorButtonCallback);
    }

}
