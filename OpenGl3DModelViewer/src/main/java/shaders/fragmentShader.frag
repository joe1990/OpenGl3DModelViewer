#version 400 core

in vec3 color;
in vec3 surfaceNormal;
in vec3 toLightVector;

uniform vec3 lightColor;

void main()
{

    vec3 unitNormal = normalize(surfaceNormal); //normalize: vec länge auf 1 setzen
    vec3 unitToLightVector = normalize(toLightVector);


    float nDot = dot(unitNormal, unitToLightVector);
    float brightness = max(nDot, 0.0);
    vec3 diffuse = brightness * lightColor;

    gl_FragColor = vec4(diffuse, 1.0) * vec4(color, 1.0);
}