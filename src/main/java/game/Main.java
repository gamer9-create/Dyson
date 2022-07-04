package game;

import engine.info.*;
import engine.logic.input.*;
import engine.logic.services.*;
import engine.logic.services.gl.*;
import engine.logic.services.image.*;
import engine.logic.systems.shape.*;
import engine.logic.systems.tilemap.*;
import engine.render.*;
import engine.render.graphics2d.*;
import org.joml.*;
import org.lwjgl.*;

import java.lang.Runtime;
import java.nio.*;
import java.util.*;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    static Matrix4f worldMatrixMat;
    static FloatBuffer worldMatrix;
    static InstancedModel model, model2, ground, cover, sun;
    static InstancedModel[] tilemapma;
    static int camMoveX, camMoveY, camSpeed = 400, mSpeed = 20 / 4;
    static Tilemap tilemap;


    public static void main(String[] args) throws Exception {
        Window.initialize();
        initialize();

        while (!glfwWindowShouldClose(Window.getWindow())) {
            Window.beginWindow();

            Window.update();
            update();

            Window.render();
            render();

            Window.stopWindow();
        }

        dispose();
        Window.dispose();
    }

    public static void initialize() throws Exception {
        Dyson.initializeDyson();

        Renderer.initialize();

        //ParticleManager.initialize();

        //Vector2f defaultScale = new Vector2f((0.01f/1.8f/4.4f), (0.01f/4f));
        Vector2f defaultScale = new Vector2f(1f/(Window.getWindowFramebuffer()[3]/2f), 1f/(Window.getWindowFramebuffer()[4]/2f));

        worldMatrix = BufferUtils.createFloatBuffer(16);

        worldMatrixMat = new Matrix4f().scale(defaultScale.x, defaultScale.y, 0);
        worldMatrixMat.get(worldMatrix);

        model = new InstancedModel(new Rectangle(0, 0, 100, 100));
        model.setTextureCoordinates(new float[] {
                0, 1,
                1, 1,
                1, 0,
                0, 0
        });

        model.getVertices()[2] = 0;
        model.getVertices()[5] = 0;
        model.getVertices()[8] = 0;
        model.getVertices()[11] = 0;

        model2 = new InstancedModel(new Rectangle(99, 0, 100, 100));
        model2.setTextureCoordinates(new float[] {
                0, 1,
                1, 1,
                1, 0,
                0, 0
        });

        model2.getVertices()[2] = 0;
        model2.getVertices()[5] = 0;
        model2.getVertices()[8] = 0;
        model2.getVertices()[11] = 0;

        ground = new InstancedModel(new Rectangle(-800, -400, 1600, 350));
        ground.setTextureCoordinates(new float[] {
                0, 1,
                1, 1,
                1, 0,
                0, 0
        });

        ground.getVertices()[2] = 1;
        ground.getVertices()[5] = 1;
        ground.getVertices()[8] = 1;
        ground.getVertices()[11] = 1;

        cover = new InstancedModel(new Rectangle(-300, 200, 1600, 350));
        cover.setTextureCoordinates(new float[] {
                0, 1,
                1, 1,
                1, 0,
                0, 0
        });

        cover.getVertices()[2] = 1;
        cover.getVertices()[5] = 1;
        cover.getVertices()[8] = 1;
        cover.getVertices()[11] = 1;

        sun = new InstancedModel(new Rectangle(600, 200, 200, 200));
        sun.setTextureCoordinates(new float[] {
                0, 1,
                1, 1,
                1, 0,
                0, 0
        });

        sun.getVertices()[2] = 2;
        sun.getVertices()[5] = 2;
        sun.getVertices()[8] = 2;
        sun.getVertices()[11] = 2;

        ImageResource resource = ResourceLoader.loadImageResource("resources/textures/tex.png");

        int[] tex = GLResourceLoader.createTextureResource(resource);

        ImageResource sunTextureResource = ResourceLoader.loadImageResource("resources/textures/sun.png");

        int[] sunTexture = GLResourceLoader.createTextureResource(sunTextureResource);

        ImageResource groundTexResource = ResourceLoader.loadImageResource("resources/textures/ground.png");

        int[] groundTex = GLResourceLoader.createTextureResource(groundTexResource);

        ImageResource tilemapTexResource = ResourceLoader.loadImageResource("resources/textures/block_sheet.png");

        int[] tilemapTex = GLResourceLoader.createTextureResource(tilemapTexResource);

        model.setTexture(tex);
        model2.setTexture(tex);

        ground.setTexture(groundTex);
        cover.setTexture(groundTex);

        sun.setTexture(sunTexture);

        Renderer.popImageInSlot(0, tex);
        Renderer.popImageInSlot(1, groundTex);
        Renderer.popImageInSlot(2, sunTexture);
        Renderer.popImageInSlot(3, tilemapTex);

        System.out.println(Arrays.toString(model.getVertices()));

        SpritesheetSubImage sprite1;

        int player_spawn_tile_id = 6;
        int enemy_wall_tile_id = 7;
        int enemy_spawn_tile_id = 8;

        int[] exception_list = new int[3];
        exception_list[0] = player_spawn_tile_id;
        exception_list[1] = enemy_spawn_tile_id;
        exception_list[2] = enemy_wall_tile_id;

        tilemap = new Tilemap();

        tilemap.initialize(64, 64);
        tilemap.loadTilemap("resources/tilemaps/Game_Map3.csv");

        HashMap<Integer, float[]> hmap = new HashMap<>();
        //hmap.put(1, sprite1.generateTextureCoordinates(tilemapTexResource.getWidth().get(0), tilemapTexResource.getHeight().get(0)));
        for (int i = 1; i < (tilemapTexResource.getWidth().get(0)/50); i++) {
            sprite1 = new SpritesheetSubImage(new Rectangle(50 * i, 0, 50, 50));
            hmap.put(i, sprite1.generateTextureCoordinates(tilemapTexResource.getWidth().get(0), tilemapTexResource.getHeight().get(0)));
        }

        tilemapma = tilemap.getTilemapAsModelArray(hmap, exception_list, 300, 300, 16, 16, 3);

        //System.out.println(n o);
        //Runtime.getRuntime().exec(n o);

        //76.4 MB

        //Particle particle = new Particle(new Rectangle(0, 0, 50, 50), sunTexture, 2,
        //        new ParticleBuffer(100, new int[] {
        //                0, 10
        //        })
        //);
        //ParticleManager.getParticles()[0] = particle;

        //ResourceLoader.loadSpritesheet("resources/spritesheet/gametexturesheet.json");
    }

    public static void render() {
        //model.getRectangle().setX(model.getRectangle().getX() + 1);
        //model.updateVertices();
        //model2.updateVertices();
        //worldMatrixMat.translate(-3, 0, 0);

        //worldMatrixMat.get(worldMatrix);

        Renderer.renderModel(model);
        Renderer.renderModel(model2);
        //Renderer.renderModel(ground);
        Renderer.renderModel(cover);
        Renderer.renderModel(sun);
        Renderer.renderModels(tilemapma, tilemap.getWidth() * tilemap.getHeight());
        //Renderer.getLightPositionArray()[0] += 200f*5;
        //System.out.println(Arrays.toString(Renderer.getLightPositionArray()));
        //Renderer.renderModels(ParticleManager.getParticles());
        //Renderer.renderModel(backgroundModel);

        //ParticleManager.render();
        Renderer.bufferToScreen(worldMatrix);

    }

    public static void update() {
        //System.out.println("Total memory = " + String.valueOf((((Runtime.getRuntime().totalMemory()/1024)/1024))) + "MB");
        //System.out.println("Memory used = "+(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024) / 1024) + "MB");

        //System.out.println(Math.floor(1.0f / Window.getDeltaTime()));

        if (Keyboard.isKeyDown(GLFW_KEY_W)) {
            camMoveY -= camSpeed * mSpeed * Window.getDeltaTime();
            worldMatrixMat.translate(0, -camSpeed * Window.getDeltaTime(), 0);
        }

        if (Keyboard.isKeyDown(GLFW_KEY_S)) {
            camMoveY += camSpeed * mSpeed * Window.getDeltaTime();
            worldMatrixMat.translate(0, camSpeed * Window.getDeltaTime(), 0);
        }

        if (Keyboard.isKeyDown(GLFW_KEY_A)) {
            camMoveX += camSpeed * mSpeed * Window.getDeltaTime();
            worldMatrixMat.translate(camSpeed * Window.getDeltaTime(), 0, 0);
        }

        if (Keyboard.isKeyDown(GLFW_KEY_D)) {
            camMoveX -= camSpeed * mSpeed * Window.getDeltaTime();
            worldMatrixMat.translate(-camSpeed * Window.getDeltaTime(), 0, 0);
        }

        worldMatrixMat.get(worldMatrix);

        Dyson.updateDyson();
        Renderer.update();
    }

    public static void dispose() {
        //ParticleManager.dispose();
        Renderer.dispose();
        Dyson.disposeDyson();
    }
}