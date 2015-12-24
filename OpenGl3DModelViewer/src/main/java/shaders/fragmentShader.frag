#version 400 core

in vec3 color;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

uniform vec3 lightColor;

void main()
{

    vec3 unitNormal = normalize(surfaceNormal); //normalize: vec länge auf 1 setzen
    vec3 unitToLightVector = normalize(toLightVector);

    //Diffuse
    float nDot = dot(unitNormal, unitToLightVector);
    float brightness = max(nDot, 0.4); //Ambient
    vec3 diffuse = brightness * lightColor;

    //spekular
    vec3 unitVectorToCamera = normalize(toCameraVector);
    vec3 lightDirection = -unitToLightVector;
    vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

    float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
    specularFactor = max(specularFactor, 0.0);
    float dampedFactor = pow(specularFactor, 10); //DampFactor
    vec3 finalSpecular = dampedFactor * 1 * lightColor; //Reflectivity

    //Ohne Licht
    //gl_FragColor = vec4(color, 1.0);

    //Ambient und Diffuse
    //gl_FragColor = (vec4(diffuse, 1.0) * vec4(color, 1.0)) ;

    //Ambient, Diffuse und Spekular
    gl_FragColor = (vec4(diffuse, 1.0) * vec4(color, 1.0)) + vec4(finalSpecular, 1.0);


}