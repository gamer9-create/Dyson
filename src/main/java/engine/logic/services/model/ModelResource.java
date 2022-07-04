package engine.logic.services.model;

public class ModelResource {
    private float[] verts, texCoords;
    private int[] indices;

    public ModelResource(float[] verts, float[] texCoords, int[] indices) {
        this.verts = verts;
        this.texCoords = texCoords;
        this.indices = indices;
    }

    public float[] getVerts() {
        return verts;
    }

    public void setVerts(float[] verts) {
        this.verts = verts;
    }

    public float[] getTexCoords() {
        return texCoords;
    }

    public void setTexCoords(float[] texCoords) {
        this.texCoords = texCoords;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }
}
