#version 330

attribute vec3 vertices;
attribute vec2 textures;
attribute vec3 light;

varying vec2 tex_coords;
uniform mat4 projection;

out vec3 fragVertices;
out vec3 fragLight;

void main() {
    fragVertices = vertices;
    fragLight = light;
    tex_coords = textures;
    gl_Position = projection * vec4(vertices, 1);
}