package engine.logic.systems.shape;

import org.joml.*;

public class ShapeProcessing {

    // Generate indices for 2d shapes
    public static int[] generateIndices(int shapes) {
        int[] elementArray = new int[shapes * 6];

        for (int i=0; i < shapes; i++) {
            loadElementIndices(elementArray, i);
        }

        return elementArray;
    }

    // Load element indices for 2d shapes
    private static void loadElementIndices(int[] elements, int index) {
        int offsetArrayIndex = 6 * index;
        int offset = 4 * index;

        // 3, 2, 0, 0, 2, 1        7, 6, 4, 4, 6, 5
        // Triangle 1
        elements[offsetArrayIndex] = offset + 3;
        elements[offsetArrayIndex + 1] = offset + 2;
        elements[offsetArrayIndex + 2] = offset;

        // Triangle 2
        elements[offsetArrayIndex + 3] = offset;
        elements[offsetArrayIndex + 4] = offset + 2;
        elements[offsetArrayIndex + 5] = offset + 1;
    }

    // Generate indices for 3d shapes
    public static int[] generateIndices3D(int shapes) {
        int[] elementArray = new int[shapes*36];

        for (int i=0; i < shapes; i++) {
            loadElementIndices3D(elementArray, i);
        }

        return elementArray;
    }

    // Load element indices for 3d shapes
    private static void loadElementIndices3D(int[] elements, int index) {
        int offsetArrayIndex = 36 * index;
        int offset = (6 * 9) * index;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // First line
        elements[offsetArrayIndex] = offset;
        elements[offsetArrayIndex + 1] = offset + 1;
        elements[offsetArrayIndex + 2] = offset + 3;

        elements[offsetArrayIndex + 3] = offset + 3;
        elements[offsetArrayIndex + 4] = offset + 1;
        elements[offsetArrayIndex + 5] = offset + 2;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // Second line
        elements[offsetArrayIndex + 6] = offset + 8;
        elements[offsetArrayIndex + 7] = offset + 10;
        elements[offsetArrayIndex + 8] = offset + 11;

        elements[offsetArrayIndex + 9] = offset + 9;
        elements[offsetArrayIndex + 10] = offset + 8;
        elements[offsetArrayIndex + 11] = offset + 11;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // Third line
        elements[offsetArrayIndex + 12] = offset + 12;
        elements[offsetArrayIndex + 13] = offset + 13;
        elements[offsetArrayIndex + 14] = offset + 7;

        elements[offsetArrayIndex + 15] = offset + 5;
        elements[offsetArrayIndex + 16] = offset + 12;
        elements[offsetArrayIndex + 17] = offset + 7;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // Fourth line
        elements[offsetArrayIndex + 18] = offset + 14;
        elements[offsetArrayIndex + 19] = offset + 15;
        elements[offsetArrayIndex + 20] = offset + 6;

        elements[offsetArrayIndex + 21] = offset + 4;
        elements[offsetArrayIndex + 22] = offset + 14;
        elements[offsetArrayIndex + 23] = offset + 6;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // Fifth line
        elements[offsetArrayIndex + 24] = offset + 16;
        elements[offsetArrayIndex + 25] = offset + 18;
        elements[offsetArrayIndex + 26] = offset + 19;

        elements[offsetArrayIndex + 27] = offset + 17;
        elements[offsetArrayIndex + 28] = offset + 16;
        elements[offsetArrayIndex + 29] = offset + 19;

        /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

        // Sixth line
        elements[offsetArrayIndex + 30] = offset + 4;
        elements[offsetArrayIndex + 31] = offset + 6;
        elements[offsetArrayIndex + 32] = offset + 7;

        elements[offsetArrayIndex + 33] = offset + 5;
        elements[offsetArrayIndex + 34] = offset + 4;
        elements[offsetArrayIndex + 35] = offset + 7;
    }

    /*
    0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
     */

    // Generate 2d sprites
    public static float[] generateSpriteShape(float x, float y, float w, float h) {

        float[] vertices = new float[] {
                -0.05f, 0.1f, 0,
                0.05f, 0.1f, 0,
                0.05f, -0.1f, 0,
                -0.05f, -0.1f, 0};

        vertices[0] = x;
        vertices[1] = y;

        vertices[3] = x+ (float) w;
        vertices[4] = y;

        vertices[6] = x+ (float) w;
        vertices[7] = y+ (float) h;

        vertices[9] = x;
        vertices[10] = y+ (float) h;

        return vertices;
    }

    // Translate 2d sprites
    public static void translateSpriteShape(float[] vertices, int x, int y, int w, int h) {

        vertices[0] = x;
        vertices[1] = y;

        vertices[3] = x+ (float) w;
        vertices[4] = y;

        vertices[6] = x+ (float) w;
        vertices[7] = y+ (float) h;

        vertices[9] = x;
        vertices[10] = y+ (float) h;

    }


    // Generate 3d sprites
    public static float[] generateSpriteShape3D(Vector3f cubePosition, Vector3f cubeSize) {
        return new float[] {
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
        };
    }

    /*
    // Translate 3d sprites
    public static void translateSpriteShape3D(float[] vertices, Vector3f cubePosition, Vector3f cubeSize) {
        vertices = new float[] {
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y+cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z-cubeSize.z,
                cubePosition.x-cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
                cubePosition.x+cubeSize.x, cubePosition.y-cubeSize.y, cubePosition.z+cubeSize.z,
        };
    }

     */

}
