#version 330

attribute vec3 vertices;
attribute vec2 textures;

varying vec2 tex_coords;
uniform mat4 projection;
uniform mat4 camera;

out vec3 fragVertices;

void main() {
    mat4 resultMat = (projection * camera);
    fragVertices = vertices;
    tex_coords = textures;
    gl_Position = resultMat * vec4(vertices, 1);
}