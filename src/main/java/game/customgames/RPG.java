package game.customgames;

import engine.info.*;
import engine.logic.services.gl.GLResourceLoader;
import engine.logic.services.ResourceLoader;
import engine.logic.systems.shape.Rectangle;
import engine.render.*;
import engine.render.graphics2d.*;
import org.joml.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class RPG {
    private static Matrix4f gameRenderingMatrix;
    private static FloatBuffer gameRenderingMatrixFloatBuffer;

    private static RPGEntity player;

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
        Renderer.initialize();
        Dyson.initializeDyson();

        player = new RPGEntity(new RPGEntityInfo(100, 100, 10, 100), new InstancedModel(new Rectangle(0, 0, 100, 100), new float[] {
                0.5f, 1,
                1, 1,
                1, 0.5f,
                0.5f, 0.5f
        }));
        player.getModel().setTexture(GLResourceLoader.createTextureResource(ResourceLoader.loadImageResource("resources/textures/EpicSpriteSheet.png")));

        gameRenderingMatrix = new Matrix4f().scale(1f/(Window.getWindowFramebuffer()[3]/2f), 1f/(Window.getWindowFramebuffer()[4]/2f), 0);
        gameRenderingMatrixFloatBuffer = BufferUtils.createFloatBuffer(16);
        gameRenderingMatrix.get(gameRenderingMatrixFloatBuffer);
    }

    public static void render() throws Exception {
        Renderer.renderModel(player.getModel());
        Renderer.bufferToScreen(gameRenderingMatrixFloatBuffer);
    }

    public static void update() throws Exception {
    }

    public static void dispose() throws Exception {
        Renderer.dispose();
    }
}

enum AnimState {
    IDLE, WALKING, RUNNING, ATTACKING
}

class RPGEntityInfo {
    private int Health, Hunger, Damage, Speed;
    private AnimState animationState;

    public RPGEntityInfo(int health, int hunger, int damage, int speed) {
        Health = health;
        Hunger = hunger;
        Damage = damage;
        Speed = speed;
        this.animationState = AnimState.IDLE;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getHunger() {
        return Hunger;
    }

    public void setHunger(int hunger) {
        Hunger = hunger;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public AnimState getAnimationState() {
        return animationState;
    }

    public void setAnimationState(AnimState animationState) {
        this.animationState = animationState;
    }
}

class RPGEntity {
    private RPGEntityInfo information;
    private InstancedModel model;

    public RPGEntity(RPGEntityInfo information, InstancedModel model) {
        this.information = information;
        this.model = model;
    }

    public void update() {


        model.updateVertices();
    }

    public RPGEntityInfo getInformation() {
        return information;
    }

    public void setInformation(RPGEntityInfo information) {
        this.information = information;
    }

    public InstancedModel getModel() {
        return model;
    }

    public void setModel(InstancedModel model) {
        this.model = model;
    }
}