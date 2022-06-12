package engine.logic.services.gl;

import static org.lwjgl.opengl.GL45.*;

public class GLResourceManager {

    public static void switchTextureSlot(int slot) {
        glActiveTexture(GL_TEXTURE0 + slot);
    }

    public static void bindTexture(int slot, int[] texture) {
        glBindTextureUnit(slot, texture[0]);
    }

    public static void bindTexture(int[] texture) {
        glBindTexture(GL_TEXTURE_2D, texture[0]);
    }

    public static void unbindTexture() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static void bindFramebuffer(int[] framebuffer) {
        glBindTexture(GL_TEXTURE_2D, 0);
        glBindFramebuffer(GL_FRAMEBUFFER, framebuffer[0]);
        glViewport(0, 0, framebuffer[3], framebuffer[4]);
    }

    public static void unbindFramebuffer(int[] framebuffer) {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0, 0, framebuffer[3], framebuffer[4]);
    }

    public static void unbindFramebuffer() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public static void deleteFramebuffer(int[] framebuffer) {
        glDeleteFramebuffers(framebuffer[0]);
    }

    public static void bindShader(int shader) {
        glUseProgram(shader);
    }

    public static void unbindShader() {
        glUseProgram(0);
    }

    public static void disposeModel(int[] model) {
        glDeleteBuffers(model);
    }

}
