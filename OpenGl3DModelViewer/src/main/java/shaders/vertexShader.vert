#version 400 core

in vec3 position;
in vec3 normal;
in vec3 colour;

out vec3 color;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 transformation;
uniform mat4 projection;
uniform mat4 view;
uniform vec3 lightPosition;

void main()
{

    //defines the world-position of each vertex
    vec4 world = transformation * vec4(position, 1.0f);
    gl_Position = projection * view * world;


   //transmit following variables to the fragment shader

    //übergebe die Farbe des Objektes
    color = colour;

    //übergebe den Normalenvektor
    surfaceNormal = (transformation * vec4(normal, 1.0)).xyz; //    surfaceNormal = (transformation * vec4(normal, 0.0)).xyz;

    //übergebe den Vektor model->light
    toLightVector = lightPosition - position.xyz;

    //übergebe den Vektor model->camera
    toCameraVector = (inverse(view) * vec4(0,0,0,1.0)).xyz - world.xyz;
}