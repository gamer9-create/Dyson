package game;

import engine.info.*;
import engine.render.*;
import engine.render.graphics3d.*;

import static org.lwjgl.glfw.GLFW.*;

public class Main3D {
    public static void main(String[] args) throws Exception {
        Window.initialize();
        initialize();

        while (!glfwWindowShouldClose(Window.getWindow())) {
            Window.beginWindow();

            Window.update();
            update();

            Window.render();
            render();

            Window.stopWindow();
        }

        dispose();
        Window.dispose();
    }

    public static void initialize() throws Exception {
        Dyson.initializeDyson();


    }

    public static void render() {

    }

    public static void update() {
        Dyson.updateDyson();
    }

    public static void dispose() {
        Dyson.disposeDyson();
    }
}