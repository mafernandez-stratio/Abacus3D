
var container, stats;

var camera, scene, renderer;

var sphere, plane;

init();
animate();

function init() {
    container = document.createElement('div');
    document.body.appendChild( container );

    camera = new THREE.PerspectiveCamera(20, window.innerWidth/window.innerHeight, 1, 10000);
    camera.position.set(0, 0, 1000);

    controls = new THREE.TrackballControls(camera);

    scene = new THREE.Scene();

    // Sphere

    geometry = new THREE.SphereGeometry(100, 100, 100);
    material = new THREE.MeshLambertMaterial({color: 0x660000, 
                                              shading: THREE.FlatShading, 
                                              overdraw: true});

    sphere = new THREE.Mesh(geometry, material);

    scene.add(sphere);

    // Lights

    var ambientLight = new THREE.AmbientLight(0xffffff);
    scene.add(ambientLight);

    var directionalLight = new THREE.DirectionalLight(0xffffff);
    directionalLight.position.set(100, 100, 100);
    //scene.add(directionalLight);

    var pointLight = new THREE.PointLight(0xffffff);
    pointLight.position.set(100, 100, 100);
    scene.add(pointLight);
    
    // Little sphere pointing to the light
    pointGeometry = new THREE.SphereGeometry(2, 20, 20);
    pointMaterial = new THREE.MeshLambertMaterial({color: 0x000000, 
                                              shading: THREE.FlatShading, 
                                              overdraw: true});

    point = new THREE.Mesh(pointGeometry, pointMaterial);
    point.position.set(100, 100, 100);
    scene.add(point);
    
    // Renderer
    renderer = new THREE.CanvasRenderer();
    //renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth*0.9, window.innerHeight*0.9);

    container.appendChild(renderer.domElement);
    
    //
    window.addEventListener('resize', onWindowResize, false);
}

function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize( window.innerWidth, window.innerHeight );
}


//
function animate() {
    requestAnimationFrame(animate);
    render();
}

//
function render() {
    controls.update();
    renderer.render(scene, camera);
}


