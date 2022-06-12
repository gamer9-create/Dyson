#version 330

uniform sampler2D sampler0;
uniform sampler2D sampler1;
uniform vec4 background;
varying vec2 tex_coords;

void main() {
    vec4 resultColor0 = texture2D(sampler0, tex_coords);
    vec4 resultColor1 = texture2D(sampler1, tex_coords);
    vec4 resultColor = vec4(0,0,0,1);

    if (resultColor0 == resultColor1) {
        resultColor = resultColor0;
    } else {
        resultColor = resultColor1;
    }

    gl_FragColor = resultColor;
}