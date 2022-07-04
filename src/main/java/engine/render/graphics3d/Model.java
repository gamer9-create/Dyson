package engine.render.graphics3d;

import static org.lwjgl.opengl.GL45.*;

public class Model {
    private int vertexBuffer, textureCoordinateBuffer, indexBuffer;
    private float[] vertices, textureCoordinates;
    private int[] indices;

    public void Model(float[] verts, float[] texCoords, int[] indices) {
        this.vertices = verts;
        this.textureCoordinates = texCoords;
        this.indices = indices;

        vertexBuffer = glGenBuffers();
        textureCoordinateBuffer = glGenBuffers();
        indexBuffer = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, verts, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindBuffer(GL_ARRAY_BUFFER, textureCoordinateBuffer);
        glBufferData(GL_ARRAY_BUFFER, texCoords, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_DYNAMIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public int getVertexBuffer() {
        return vertexBuffer;
    }

    public int getTextureCoordinateBuffer() {
        return textureCoordinateBuffer;
    }

    public int getIndexBuffer() {
        return indexBuffer;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextureCoordinates() {
        return textureCoordinates;
    }

    public int[] getIndices() {
        return indices;
    }
}
