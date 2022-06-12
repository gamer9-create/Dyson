#version 330

uniform sampler2D[8] sampler;
uniform vec4 background;
varying vec2 tex_coords;

in vec3 fragVertices;
in vec3 fragLight;

vec4 calucateLight(vec3 myColor, vec3 lightPos) {
    myColor.x += 0.000000000000000000001;
    myColor.y += 0.000000000000000000001;
    myColor.z += 0.000000000000000000001;

    vec2 thisPos = vec2(fragVertices.x/200, fragVertices.y/200);

    float lightdistanceX = abs(thisPos.x - lightPos.x);
    float lightdistanceY = abs(thisPos.y - lightPos.y);
    float lightdistance = lightdistanceX + lightdistanceY;

    vec4 result = vec4(myColor.x/lightdistance,
                       myColor.y/lightdistance,
                       myColor.z/lightdistance, 1);
    return result;
}

void main() {
    vec4 resultColor = vec4(1, 1, 1, 1);//texture2D(sampler[int(fragVertices.z)], tex_coords);//vec4(fragVertices.z, fragVertices.z, fragVertices.z, 1);

    gl_FragColor = resultColor;

    /*
    if (resultColor.x > 0 && resultColor.y > 0 && resultColor.z > 0 && resultColor.w > 0) {
        gl_FragColor = resultColor;
    } else {
        gl_FragColor = background;
    }
    */
}