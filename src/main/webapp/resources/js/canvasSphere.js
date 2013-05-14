
var container, stats;

var camera, scene, renderer;

var sphere, plane;

init();
render();

function init() {
    container = document.createElement('div');
    document.body.appendChild( container );

    camera = new THREE.PerspectiveCamera(20, window.innerWidth/window.innerHeight, 1, 10000);
    camera.position.set(0, 0, 1000);

    scene = new THREE.Scene();

    // Sphere

    geometry = new THREE.SphereGeometry(100, 50, 50);
    material = new THREE.MeshLambertMaterial({color: 0x660000, 
                                              shading: THREE.FlatShading, 
                                              overdraw: true});

    sphere = new THREE.Mesh(geometry, material);

    scene.add(sphere);

    // Lights

    var ambientLight = new THREE.AmbientLight(0xffffff);
    scene.add(ambientLight);

    var directionalLight = new THREE.DirectionalLight(0xffffff);
    directionalLight.position.set(50, 50, 0);
    //scene.add(directionalLight);

    var pointLight = new THREE.PointLight(0xff0000, 1, 100);
    //pointLight.position.set(10, 10, 10);
    scene.add(pointLight);
    

    renderer = new THREE.CanvasRenderer();
    //renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth*0.9, window.innerHeight*0.9);

    container.appendChild(renderer.domElement);
}

//

function render() {
    renderer.render(scene, camera);
}


