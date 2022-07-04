package engine.info;

import engine.render.*;

import static org.lwjgl.opengl.GL45.*;

public class Dyson {
    public static String DYSON_VERSION, DYSON_BUILD;

    static {
        DYSON_VERSION = "v0.1";
        DYSON_BUILD = "PROTOTYPE";
    }

    public static void initializeDyson() {
        System.out.println("\n" + "Initializing Dyson" + "\n");

        System.out.println("Dyson Version: " + DYSON_VERSION + " | Dyson Build: " + DYSON_BUILD);

        System.out.println("GPU: " + glGetString(GL_RENDERER) + " | GPU Vendor: " + glGetString(GL_VENDOR) + "\n");
    }

    public static void updateDyson() {

    }

    public static void disposeDyson() {

    }

}
