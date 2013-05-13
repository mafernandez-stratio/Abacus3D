
var scene = new THREE.Scene();

var camera = new THREE.PerspectiveCamera(75,  window.innerWidth/window.innerHeight, 0.1, 1000); 

//var renderer = new THREE.WebGLRenderer(); 
var renderer = new THREE.CanvasRenderer();
renderer.setSize(window.innerWidth, window.innerHeight); 
document.body.appendChild(renderer.domElement); 

var geometry = new THREE.CubeGeometry(1,1,1); 
//var geometry = new THREE.SphereGeometry(50, 16, 16);
//var geometry = new THREE.CircleGeometry();
var material = new THREE.MeshBasicMaterial({color: 0x00ff00}); 
//var material = new THREE.MeshLambertMaterial({color: 0xCC0000});

var figure = new THREE.Mesh(geometry, material); 

// set the geometry to dynamic
// so that it allow updates
//figure.geometry.dynamic = true;

// changes to the vertices
//figure.geometry.verticesNeedUpdate = true;

// changes to the normals
//figure.geometry.normalsNeedUpdate = true;

scene.add(figure); 

// create a point light
//var pointLight = new THREE.PointLight(0xFFFFFF);

// set its position
//pointLight.position.x = 10;
//pointLight.position.y = 50;
//pointLight.position.z = 130;

// add to the scene
//scene.add(pointLight);

camera.position.z = 5;

var render = function () { 
    requestAnimationFrame(render);
    
    figure.rotation.x += 0.1; 
    figure.rotation.y += 0.1; 
    renderer.render(scene, camera); 
};

render();

//renderer.render(scene, camera); 
