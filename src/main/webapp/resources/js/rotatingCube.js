var camera, scene, renderer, geometry, material, mesh;

init();
animate();

function init() {

    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 1000);
    camera.position.z = 500;
    scene.add(camera);

    geometry = new THREE.CubeGeometry(100, 100, 100);
    material = new THREE.MeshNormalMaterial();

    mesh = new THREE.Mesh(geometry, material);
    scene.add(mesh);

    var scale = 0.8;

    renderer = new THREE.CanvasRenderer();
    renderer.setSize(window.innerWidth*scale, window.innerHeight*scale);

    //document.body.appendChild(renderer.domElement);
    //document.appendChild(renderer.domElement);
    var popup = document.getElementById("nBodyDiv");
    popup.appendChild(renderer.domElement);
    //document.documentElement;
}

function animate() {

    requestAnimationFrame(animate);
    render();

}

function render() {

    mesh.rotation.x += 0.01;
    mesh.rotation.y += 0.02;

    renderer.render(scene, camera);

}


