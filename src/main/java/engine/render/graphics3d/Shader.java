package engine.render.graphics3d;

import engine.logic.services.*;

import static org.lwjgl.opengl.GL45.*;

public class Shader {
    private int program, vertexShader, fragmentShader;

    public Shader(String vsFilename, String fsFilename) {
        int shader = glCreateProgram();

        int vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, ResourceLoader.loadFile(vsFilename));
        glCompileShader(vertex);
        if (glGetShaderi(vertex, GL_COMPILE_STATUS) != 1) {
            throw new RuntimeException(glGetShaderInfoLog(vertex));
        }

        int fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragment, ResourceLoader.loadFile(fsFilename));
        glCompileShader(fragment);
        if (glGetShaderi(fragment, GL_COMPILE_STATUS) != 1) {
            throw new RuntimeException(glGetShaderInfoLog(fragment));
        }

        glAttachShader(shader, vertex);
        glAttachShader(shader, fragment);

        glBindAttribLocation(shader, 0,"vertices");
        glBindAttribLocation(shader, 1, "textures");

        glLinkProgram(shader);

        this.program = shader;
        this.vertexShader = vertex;
        this.fragmentShader = fragment;
    }

    public void bind() {
        glUseProgram(this.program);
    }

    public int getProgram() {
        return program;
    }

    public int getVertexShader() {
        return vertexShader;
    }

    public int getFragmentShader() {
        return fragmentShader;
    }
}
