#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texture_coord;
layout (location = 2) in vec3 normals;

uniform mat4 transformWorld;
uniform mat4 transformObject;
uniform mat4 cameraProjection;

uniform vec3 vertPos;

out vec2 texture;
out vec3 fragPos;
out vec3 fragNormal;

void main() {
    texture = texture_coord;
    fragPos = vertPos+position;//vec3(vertPos.x, vertPos.y, vertPos.z);
    fragNormal = (transformObject * vec4(normals, 0.0)).xyz;
    gl_Position = cameraProjection * transformWorld * transformObject * vec4(position, 1);
}