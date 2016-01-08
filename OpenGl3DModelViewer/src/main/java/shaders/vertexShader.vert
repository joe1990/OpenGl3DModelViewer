#version 400 core

//in variables
in vec3 position;
in vec3 normal;
in vec3 colour;

//out variables
out vec3 v_toLight;
out vec3 v_toCamera;
out vec3 v_normal;
out vec3 v_color;

//uniforms
uniform vec3 light;
uniform mat4 projection;
uniform mat4 transformation;
uniform mat4 view;


void main()
{

    //defines the world-position of each vertex
    vec4 transpos = transformation * vec4(position, 1.0f);
    gl_Position = projection * view * transpos;

    //übergebe die Farbe des Objektes
    v_color = colour;

    //übergebe den Normalenvektor
    v_normal = (transformation * vec4(normal, 1.0)).xyz; // 0.0

    //übergebe den Vektor model->light
    v_toLight = light - position.xyz;

    //übergebe den Vektor model->camera
    v_toCamera = (inverse(view) * vec4(0.0,0.0,0.0,1.0)).xyz - transpos.xyz;
}