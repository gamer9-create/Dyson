package engine.render.ui2d;

import engine.logic.services.image.*;
import engine.logic.systems.shape.Rectangle;

import java.util.*;

public class FontMaps {

    public static HashMap<Character, float[]> getDefaultCharacterMap(int imageWidth, int imageHeight) {
        SpritesheetSubImage lettergen = new SpritesheetSubImage(new Rectangle(0, 0, 15, 15));

        HashMap<Character, float[]> c = new HashMap<>();
        c.put('a', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(17);
        c.put('b', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(0);
        lettergen.getImageLocation().setY(17);
        c.put('c', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(17);
        c.put('d', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(33);
        lettergen.getImageLocation().setY(0);
        c.put('e', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(33);
        lettergen.getImageLocation().setY(17);
        c.put('f', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(49);
        lettergen.getImageLocation().setY(0);
        c.put('g', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        lettergen.getImageLocation().setX(49);
        c.put('h', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(65);
        c.put('i', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('j', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(81);
        c.put('k', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('l', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(97);
        c.put('m', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('n', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(113);
        lettergen.getImageLocation().setY(0);
        c.put('o', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('p', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(129);
        lettergen.getImageLocation().setY(0);
        c.put('q', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('r', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(145);
        c.put('s', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('t', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(161);
        c.put('u', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('v', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(177);
        c.put('w', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('x', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(0);
        lettergen.getImageLocation().setX(193);
        c.put('y', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(17);
        c.put('z', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setY(33);
        lettergen.getImageLocation().setX(0);
        c.put('1', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(17);
        c.put('2', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(33);
        c.put('3', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(49);
        c.put('4', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(65);
        c.put('5', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(81);
        c.put('6', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(97);
        c.put('7', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(113);
        c.put('8', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(129);
        c.put('9', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        lettergen.getImageLocation().setX(145);
        c.put('0', lettergen.generateTextureCoordinates(imageWidth, imageHeight));

        return c;
    }

}
