package engine.render.graphics3d;

import static org.lwjgl.opengl.GL45.*;

public class Renderer {

    public static void renderModel(Model model) {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, model.getVertexBuffer());
        glBufferSubData(GL_ARRAY_BUFFER, 0, model.getVertices());

        glBindBuffer(GL_ARRAY_BUFFER, model.getTextureCoordinateBuffer());
        glBufferSubData(GL_ARRAY_BUFFER, 0, model.getTextureCoordinates());

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, model.getIndexBuffer());

        glBindBuffer(GL_ARRAY_BUFFER, model.getVertexBuffer());

        glDrawElements(GL_TRIANGLES, model.getIndices().length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);;
    }

    public static void unbind() {
        glUseProgram(0);
    }

}
