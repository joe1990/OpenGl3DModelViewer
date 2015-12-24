#version 400 core

in vec3 position;
in vec3 normal;

out vec3 color;

out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 transformation;
uniform mat4 projection;
uniform mat4 view;

uniform vec3 lightPosition;
//uniform vec3 lightColor;

void main()
{

    //gl_Position = projection *  view * transformation * vec4(position, 1.0f);

    vec4 world = transformation * vec4(position, 1.0f);
    gl_Position = projection * view * world;
    color = vec3(1.0, 0.0, 0.0); //Farbe des Objektes

    surfaceNormal = normal;
    surfaceNormal = (transformation * vec4(normal, 0.0)).xyz;
    toLightVector = lightPosition - position.xyz;
    toCameraVector = (inverse(view) * vec4(0,0,0,1.0)).xyz - world.xyz;
}