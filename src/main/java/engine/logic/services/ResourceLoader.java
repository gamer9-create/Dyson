package engine.logic.services;

import engine.logic.services.image.ImageResource;
import org.lwjgl.*;

import java.io.*;
import java.nio.*;

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
    
}
