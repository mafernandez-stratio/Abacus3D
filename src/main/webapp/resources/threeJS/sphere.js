// MAIN

// standard global variables
var container, scene, camera, renderer;

// custom global variables
var cube;

init();
render();

// FUNCTIONS 		
function init(){
    // SCENE
    scene = new THREE.Scene();
    
    // CAMERA
    var SCREEN_WIDTH = window.innerWidth, SCREEN_HEIGHT = window.innerHeight;
    var VIEW_ANGLE = 45, ASPECT = SCREEN_WIDTH / SCREEN_HEIGHT, NEAR = 0.1, FAR = 20000;
    camera = new THREE.PerspectiveCamera( VIEW_ANGLE, ASPECT, NEAR, FAR);
    scene.add(camera);
    camera.position.set(0,150,400);
    camera.lookAt(scene.position);
    
    // RENDERER
    if ( Detector.webgl )
        renderer = new THREE.WebGLRenderer( {antialias:true} );
    else
        renderer = new THREE.CanvasRenderer(); 
    renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    container = document.createElement( 'div' );
    document.body.appendChild( container );
    container.appendChild( renderer.domElement );

    ////////////
    // CUSTOM //
    ////////////
    
    // Sphere parameters: radius, segments along width, segments along height
    var sphereGeom =  new THREE.SphereGeometry( 50, 32, 16 );

    // Three types of materials, each reacts differently to light.
    var darkMaterial = new THREE.MeshBasicMaterial( { color: 0x000088 } );
    
    // Creating three spheres to illustrate the different materials.
    // Note the clone() method used to create additional instances
    //    of the geometry from above.
    var sphere = new THREE.Mesh( sphereGeom.clone(), darkMaterial );
    sphere.position.set(-150, 50, 0);
    scene.add( sphere );    
}


function render(){
    renderer.render( scene, camera );
}


