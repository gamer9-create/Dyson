#version 330

attribute vec3 vertices;
attribute vec2 textures;

uniform mat4 projection;

void main() {
    gl_Position = projection * vec4(vertices, 1);
}