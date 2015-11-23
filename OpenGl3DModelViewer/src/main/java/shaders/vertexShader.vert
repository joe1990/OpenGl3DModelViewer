#version 400 core

in vec3 position;
in vec3 normal;

out vec3 color;
out vec3 surfaceNormal;
out vec3 toLightVector;

uniform mat4 projection;
uniform mat4 view;

uniform vec3 lightPosition;
uniform vec3 lightColor;

void main()
{
    gl_Position = projection * view * vec4(position.x, position.y-5, position.z-20, 1.0f);
    color = vec3(0.5, 0.5, 1.0);

    surfaceNormal = normal;
    toLightVector = lightPosition - position;
}