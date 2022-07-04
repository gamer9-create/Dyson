package engine.render.gui;

import engine.logic.systems.shape.Rectangle;
import engine.render.graphics2d.*;

import java.util.*;

public class TextCreator {

    public static InstancedModel[] bakeText(String string, InstancedModel[] text, int[] textAtlas, HashMap<Character, float[]> characterMap, float textSpacing, float x, float y, float width, float height) {
        string = string.toLowerCase();

        for (int i = 0; i < text.length; i++) {
            char c = string.toCharArray()[i];
            if (c != ' ') {
                InstancedModel instancedModel = new InstancedModel(new Rectangle((int) (x + (i * (int) width + (int) textSpacing)), (int) y, (int) width, (int) height));
                instancedModel.setTextureCoordinates(characterMap.get(c));
                instancedModel.setTexture(textAtlas);
                text[i] = instancedModel;
            }
        }

        return text;
    }

}
