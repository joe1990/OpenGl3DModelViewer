#version 400 core

in vec3 v_toLight;
in vec3 v_toCamera;
in vec3 v_normal;
in vec3 v_color;

uniform vec3 lightColor;

void main()
{
    //Vektoren normalisieren
    vec3 normNormal = normalize(v_normal); //normalize: vec länge auf 1 setzen
    vec3 normToLight = normalize(v_toLight);
    vec3 normToCamera = normalize(v_toCamera);

    //Diffuses-Licht
    float diffFactor = dot(normNormal, normToLight);
    float brightness = max(diffFactor, 0.4); //Ambient
    vec3 diffuse = brightness * lightColor;

    //Spekular-Licht
    vec3 lightDirection = -normToLight;
    vec3 reflectedLight = reflect(lightDirection, normNormal);
    float specFactor = dot(reflectedLight, normToCamera);
    specFactor = max(specFactor, 0.0);

    //Abweichung des Winkels zwischen toCamera und reflectedLight = 10
    float aberrationFactor = pow(specFactor, 10);

    //Reflektionsfähigkeit des Materials = 1
    vec3 specular = aberrationFactor * 1 * lightColor;

    //Ohne Licht
    //gl_FragColor = vec4(v_color, 1.0);

    //Ambient- und Diffuses-Licht
    //gl_FragColor = (vec4(diffuse, 1.0) * vec4(v_color, 1.0)) ;

    //Ambient-, Diffuses- und Spekular-Licht
    gl_FragColor = (vec4(diffuse, 1.0) * vec4(v_color, 1.0)) + vec4(specular, 1.0);
}