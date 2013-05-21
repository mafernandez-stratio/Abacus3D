var container;
var camera, controls, scene, projector, renderer;
var objects = [], plane;

var mouse = new THREE.Vector2(), 
            offset = new THREE.Vector3(), 
            INTERSECTED, 
            SELECTED;

init();
animate();

function init() {
    container = document.createElement('div');
    document.body.appendChild(container);

    camera = new THREE.PerspectiveCamera(25, window.innerWidth/window.innerHeight, 1, 10000);
    camera.position.set(0, 0, 1000); 
    
    controls = new THREE.TrackballControls(camera);    
    controls.rotateSpeed = 1.0;
    controls.zoomSpeed = 1.2;
    controls.panSpeed = 0.8;
    controls.noZoom = false;
    controls.noPan = false;
    controls.staticMoving = true;
    controls.dynamicDampingFactor = 0.3;

    scene = new THREE.Scene();
    
    // Lights

    var ambientLight = new THREE.AmbientLight(0xffffff);
    scene.add(ambientLight);  

    // Sphere
    var radius = 100;
    var geometry = new THREE.SphereGeometry(radius, 120, 120);
    var material = new THREE.MeshLambertMaterial({color: 0x660000, 
                                              shading: THREE.FlatShading, 
                                              overdraw: true});        

    var sphere = new THREE.Mesh(geometry, material);

    scene.add(sphere);
    
    // Little cube pointing to the light
    var pointGeometry = new THREE.CubeGeometry(8, 8, 8);    
          
    for(var i = 0; i<10; i++){
        var pointMaterial = new THREE.MeshLambertMaterial({color: 0xDAA520, 
                                                           shading: THREE.FlatShading, 
                                                           overdraw: true}); 
        
        var point = new THREE.Mesh(pointGeometry, pointMaterial);  
        
        point.material.ambient = point.material.color;
        
        var coefficientA = 1.5;
        var coefficientB = 0.8;
        
        var plusOrMinus = Math.random() < 0.5 ? -1 : 1;                
        point.position.x = (Math.random() * (radius/coefficientA)) + (radius * coefficientB);
        point.position.x = point.position.x * plusOrMinus;
        
        plusOrMinus = Math.random() < 0.5 ? -1 : 1;     
        point.position.y = (Math.random() * (radius/coefficientA)) + (radius * coefficientB);
        point.position.y = point.position.y * plusOrMinus;
        
        plusOrMinus = Math.random() < 0.5 ? -1 : 1;     
        point.position.z = (Math.random() * (radius/coefficientA)) + (radius * coefficientB);
        point.position.z = point.position.z * plusOrMinus;       
        
        /*
        point.rotation.x = Math.random() * 2 * Math.PI;
        point.rotation.y = Math.random() * 2 * Math.PI;
        point.rotation.z = Math.random() * 2 * Math.PI;
        
        point.castShadow = true;
        point.receiveShadow = true;
        */
        
        scene.add(point);   
        
        objects.push(point);
    }

    plane = new THREE.Mesh(new THREE.PlaneGeometry(2000, 2000, 8, 8), 
                           new THREE.MeshBasicMaterial({color: 0x000000, 
                                                        opacity: 0.25, 
                                                        transparent: true, 
                                                        wireframe: true}));
    plane.visible = false;
    scene.add(plane);

    ///////////////////////////////////////////////////////////////////////
    // Horizontal grid
    // TODO: widen this grid and improve its looking
    var step = (radius/2);

    var geometry = new THREE.Geometry();

    for (var i=-(radius*2); i<=(radius*2); i+=step){
            geometry.vertices.push(new THREE.Vector3(-(radius*2), -(radius*2), i));
            geometry.vertices.push(new THREE.Vector3((radius*2), -(radius*2), i));

            geometry.vertices.push(new THREE.Vector3(i, -(radius*2), -(radius*2)));
            geometry.vertices.push(new THREE.Vector3(i, -(radius*2), (radius*2) ));
    }

    var material = new THREE.LineBasicMaterial({color: 0x000000, opacity: 0.2});

    var line = new THREE.Line(geometry, material);
    line.type = THREE.LinePieces;
    scene.add(line);
    
    // TODO: Vertical grid
    ///////////////////////////////////////////////////////////////////////

    projector = new THREE.Projector();  
    
    // Renderer
    renderer = new THREE.CanvasRenderer();
    //renderer = new THREE.WebGLRenderer({antialias: true});
    renderer.sortObjects = false;
    renderer.setSize(window.innerWidth*0.9, window.innerHeight*0.9);
    
    renderer.shadowMapEnabled = true;
    renderer.shadowMapType = THREE.PCFShadowMap;

    container.appendChild(renderer.domElement);
    
    renderer.domElement.addEventListener('mousemove', onDocumentMouseMove, false);
    renderer.domElement.addEventListener('mousedown', onDocumentMouseDown, false);
    renderer.domElement.addEventListener('mouseup', onDocumentMouseUp, false);

    window.addEventListener('resize', onWindowResize, false);
}

function onWindowResize() {
    camera.aspect = window.innerWidth/window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth*0.9, window.innerHeight*0.9);
}

function onDocumentMouseDown(event) {
    event.preventDefault();

    var vector = new THREE.Vector3(mouse.x, mouse.y, 0.5);
    projector.unprojectVector(vector, camera);

    var raycaster = new THREE.Raycaster(camera.position, 
                                        vector.sub(camera.position).normalize());

    var intersects = raycaster.intersectObjects(objects);

    if (intersects.length > 0) {
        controls.enabled = false;

        SELECTED = intersects[0].object;       

        var intersects = raycaster.intersectObject(plane);
        offset.copy(intersects[0].point).sub(plane.position);

        container.style.cursor = 'move';
    }
}

function onDocumentMouseMove(event) {
    event.preventDefault();

    mouse.x = (event.clientX/(window.innerWidth*0.9))*2-1;
    mouse.y = -(event.clientY/(window.innerHeight*0.9))*2+1;

    var vector = new THREE.Vector3(mouse.x, mouse.y, 0.5);
    projector.unprojectVector(vector, camera);

    var raycaster = new THREE.Raycaster(camera.position, 
                                        vector.sub(camera.position).normalize());

    if (SELECTED) {
        var intersects = raycaster.intersectObject(plane);
        SELECTED.position.copy(intersects[0].point.sub(offset));
        return;
    }

    var intersects = raycaster.intersectObjects(objects);

    if (intersects.length > 0){
        if (INTERSECTED != intersects[0].object){
            if (INTERSECTED){ 
                INTERSECTED.material.color.setHex(INTERSECTED.currentHex);
            }
            INTERSECTED = intersects[0].object;
            INTERSECTED.currentHex = INTERSECTED.material.color.getHex();            
            plane.position.copy(INTERSECTED.position);
            plane.lookAt(camera.position);
        }
        container.style.cursor = 'pointer';
    } else {
        if (INTERSECTED){
            INTERSECTED.material.color.setHex(INTERSECTED.currentHex);
        }
        INTERSECTED = null;
        container.style.cursor = 'auto';
    }
}

function onDocumentMouseUp(event){
    event.preventDefault();

    controls.enabled = true;

    if (INTERSECTED){
        plane.position.copy(INTERSECTED.position);
        SELECTED = null;
    }
    container.style.cursor = 'auto';
}

function animate() {
    requestAnimationFrame(animate);
    render();
}

function render() {
    controls.update();
    renderer.render(scene, camera);
}




