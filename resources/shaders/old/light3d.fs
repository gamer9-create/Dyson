#version 330

struct Light {
    vec3 lightColor;
    float lightIntense;
};

struct DirectionLight {
    Light light;
    vec3 lightDirection;
};

uniform sampler2D sampleTexture;
uniform DirectionLight dlight;

out vec4 fragColor;

in vec2 texture;
in vec3 fragPos;
in vec3 fragNormal;

vec4 rawCalucateLight(vec3 myColor, vec3 lightPos) {
    myColor.x += 0.000000000000000000001;
    myColor.y += 0.000000000000000000001;
    myColor.z += 0.000000000000000000001;

    vec3 thisPos = fragPos;

    float lightdistanceX = abs(thisPos.x - lightPos.x);
    float lightdistanceY = abs(thisPos.y - lightPos.y);
    float lightdistanceZ = abs(thisPos.z - lightPos.z);
    float lightdistance = lightdistanceX + lightdistanceY + lightdistanceZ;

    vec4 result = vec4(myColor.x/lightdistance,
                  myColor.y/lightdistance,
                  myColor.z/lightdistance, 1);
    return result;
}

vec4 calucateLight(Light light, vec3 direction, vec3 normal) {
    float diffuse = dot(normal, -direction);

    vec4 diffuseColor = vec4(0, 0, 0, 0);

    if (diffuse > 0) {
        diffuseColor = vec4(light.lightColor, 1) * light.lightIntense * diffuse;
    }

    return diffuseColor;
}

vec4 directionLightCalucationPreset(DirectionLight light, vec3 normal) {
    return calucateLight(light.light, -light.lightDirection, normal);
}

void main() {
    vec4 tcolor = vec4(1, 1, 1, 1);

    vec4 texColor = texture2D(sampleTexture, texture);
    vec3 normal = normalize(fragNormal);

    tcolor += directionLightCalucationPreset(dlight, normal);

    fragColor = texColor * tcolor;
}