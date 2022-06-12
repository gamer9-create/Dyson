package engine.info;

import engine.logic.systems.shape.*;
import org.joml.*;

public class Constants {

    public static final String windowTitle = "DysonEngineGame";
    public static final String windowSize = "1600x800";
    public static final Vector4f windowColor = new Vector4f(0, (1f/255) * 204, 1, 1);

    public static final int textureSizeBuffer = 3;

    public static final int rendererObjectLimit = 4101;
    public static final int rendererSamplerLimit = 8;
    public static final float rendererNearPlane_intendedFor3D = 0.01f;
    public static final float rendererFarPlane_intendedFor3D = 1000f;

    public static String rendererShader = "basic";
    public static int rendererVertexArraySize = 12;
    public static int rendererTextureCoordinateArraySize = 8;
    public static int[] rendererElementArray = ShapeProcessing.generateIndices(rendererObjectLimit);

    public static int[] getWindowSizeAsINT() {
        int lastWidth, lastHeight;
        String[] s = Constants.windowSize.split("x");
        lastWidth = Integer.parseInt(s[0]);
        lastHeight = Integer.parseInt(s[1]);
        return new int[] {lastWidth, lastHeight};
    }
}
