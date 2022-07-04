package engine.logic.services;

import engine.logic.services.image.*;
import engine.logic.services.model.*;
import org.lwjgl.*;
import org.lwjgl.assimp.*;

import java.io.*;
import java.nio.*;
import java.util.ArrayList;
import java.util.Objects;

import static org.lwjgl.stb.STBImage.*;

public class ResourceLoader {

    public static String loadFile(String filename) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static ImageResource loadImageResource(String filepath) {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath, width, height, channels, 0);
        return new ImageResource(image, width, height, channels, filepath);
    }

    public static ModelResource loadModelResource(String filepath) {
        float[] verts = new float[1], texCoords = new float[1];
        int[] indices = new int[1];

        AIScene aiScene = Assimp.aiImportFile(filepath, Assimp.aiProcess_Triangulate);

        PointerBuffer buffer = aiScene.mMeshes();

        for (int i = 0; i < Objects.requireNonNull(buffer).limit(); i++) {
            AIMesh mesh = AIMesh.create(buffer.get(i));

            AIVector3D.Buffer vectors = mesh.mVertices();

            int vin = 0;

            verts = new float[vectors.limit() * 3];

            for (int d = 0; d < vectors.limit(); d++) {
                verts[vin] = vectors.get(d).x();
                vin += 1;
                verts[vin] = vectors.get(d).y();
                vin += 1;
                verts[vin] = vectors.get(d).z();
                vin += 1;
            }

            AIVector3D.Buffer texCoord = mesh.mTextureCoords(0);

            int tin = 0;

            texCoords = new float[texCoord.limit() * 2];

            for (int d = 0; d < texCoord.limit(); d++) {
                texCoords[tin] = texCoord.get(d).x();
                tin += 1;
                texCoords[tin] = texCoord.get(d).y();
                tin += 1;
            }

            AIVector3D.Buffer normals = mesh.mNormals();

            int nin = 0;

            indices = new int[normals.limit() * 2];

            for (int d = 0; d < normals.limit(); d++) {
                indices[nin] = (int) normals.get(d).x();
                nin += 1;
                indices[nin] = (int) normals.get(d).y();
                nin += 1;
            }
        }

        return new ModelResource(verts, texCoords, indices);
    }

}
