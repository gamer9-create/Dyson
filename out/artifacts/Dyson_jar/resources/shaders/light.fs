#version 330

vec4 calucateLight(vec3 myColor, vec3 lightPos) {

    vec2 thisPos = vec2(0, 0);

    float lightdistanceX = abs(thisPos.x - lightPos.x);
    float lightdistanceY = abs(thisPos.y - lightPos.y);
    float lightdistance = lightdistanceX + lightdistanceY;

    vec4 result = vec4(myColor.x/lightdistance,
                       myColor.y/lightdistance,
                       myColor.z/lightdistance, 1);
    return result;
}

void main() {
    gl_FragColor = calucateLight(vec3(1,1,1), vec3(0, 0, 0));

    /*
    if (resultColor.x > 0 && resultColor.y > 0 && resultColor.z > 0 && resultColor.w > 0) {
        gl_FragColor = resultColor;
    } else {
        gl_FragColor = background;
    }
    */
}