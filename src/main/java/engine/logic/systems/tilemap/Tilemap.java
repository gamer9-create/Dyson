package engine.logic.systems.tilemap;

import engine.logic.systems.shape.Rectangle;
import engine.render.graphics2d.*;

import java.io.*;
import java.util.HashMap;

public class Tilemap {

    // Tilemap
    private int[][] tilemap;

    // Row and column
    private int row, column, width, height;

    // Initialize function
    public void initialize(int worldWidth, int worldHeight) {
        this.width = worldWidth;
        this.height = worldHeight;
        tilemap = new int[worldHeight][worldWidth];
    }

    // Add tile function
    public void addTile(int tile) {
        tilemap[row][column] = tile;
        column += 1;
        if (column >= this.width) {
            row += 1;
            if (row >= this.height) {
                row = this.height;
            }
            column = 0;
        }
    }

    // Load tilemap function
    public void loadTilemap(String map) {
        File file = new File(map);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] StringIDS = line.trim().split(","); // Generates a data format that's easy to work with

                for (String id : StringIDS) { // Collects and adds the data to the base tilemap.
                    if (!id.isEmpty()) addTile(Integer.parseInt(id));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get tilemap

    public int[][] getTilemap() {
        return tilemap;
    }

    // Locate value in list
    public boolean isValueInList(int value, int[] values) {
        boolean d = false;

        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) {
                d = true;
            }
        }

        return d;
    }

    // Generate Model Array
    public InstancedModel[] getTilemapAsModelArray(HashMap<Integer, float[]> textures, int[] exception_list, int mapPlaceOffsetX, int mapPlaceOffsetY, int tileWidth, int tileHeight, int texIndex) {
        InstancedModel[] models = new InstancedModel[width * height];
        int i = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tilemap[y][x] != 0 || tilemap[y][x] != -1) {
                    if (!isValueInList(tilemap[y][x], exception_list)) {
                        models[i] = new InstancedModel(new Rectangle((x * tileWidth) + mapPlaceOffsetX, (-y * tileHeight) + mapPlaceOffsetY, tileWidth, tileHeight), textures.get(tilemap[y][x]));
                        models[i].getVertices()[2] = texIndex;
                        models[i].getVertices()[5] = texIndex;
                        models[i].getVertices()[8] = texIndex;
                        models[i].getVertices()[11] = texIndex;
                    } else {
                        models[i] = new InstancedModel(new Rectangle((x * tileWidth) + mapPlaceOffsetX, (-y * tileHeight) + mapPlaceOffsetY, tileWidth, tileHeight), textures.get(0));
                        models[i].getVertices()[2] = texIndex;
                        models[i].getVertices()[5] = texIndex;
                        models[i].getVertices()[8] = texIndex;
                        models[i].getVertices()[11] = texIndex;
                    }
                }
                i += 1;
            }
        }

        return models;
    }

    // Get width
    public int getWidth() {
        return width;
    }

    // Get height
    public int getHeight() {
        return height;
    }

}
