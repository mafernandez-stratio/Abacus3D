var camera, scene, renderer, objects = [];

//init();
//animate();
    
//var numParticles = "#{nBodysBean.numParticles}";
var numParticles;
//var numSteps = "#{nBodysBean.numSteps}";
var numSteps;

var message = '';

function onInitFs(fs) {
    message = 'Opened file system: ' + fs.name;
    fs.root.getFile('../txt/frame001.txt', {}, function(fileEntry) {

    // Get a File object representing the file,
    // then use FileReader to read its contents.
    fileEntry.file(function(file) {
        var reader = new FileReader();

        reader.onloadend = function(e) {
            var text = this.result;
        };

            reader.readAsText(file);
        }, errorHandler);

    }, errorHandler);
}

function errorHandler(e) {
    var msg = '';
    switch (e.code) {
        case FileError.QUOTA_EXCEEDED_ERR:
            msg = 'QUOTA_EXCEEDED_ERR';
            break;
        case FileError.NOT_FOUND_ERR:
            msg = 'NOT_FOUND_ERR';
            break;
        case FileError.SECURITY_ERR:
            msg = 'SECURITY_ERR';
            break;
        case FileError.INVALID_MODIFICATION_ERR:
            msg = 'INVALID_MODIFICATION_ERR';
            break;
        case FileError.INVALID_STATE_ERR:
            msg = 'INVALID_STATE_ERR';
            break;
        default:
            msg = 'Unknown Error';
            break;
    };
    message = 'Error: ' + msg;
}

function init(nParticles, nSteps) {

    //alert("START");
    //alert(nParticles);
    //alert(nSteps);

    numParticles = nParticles;
    numSteps = nSteps;

    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(45, window.innerWidth/window.innerHeight, 1, 1000);
    camera.position.z = 500;
    scene.add(camera);
        
    /*fh = fopen('../txt/frame'+nSteps+'.txt', 0); // Open the file for reading.
    if(fh!=-1){ // Check if the file has been successfully opened.    
        length = flength(fh); // Get the length of the file.
        str = fread(fh, length); // Read in the entire file.
        fclose(fh); // Close the file.

        // Display the contents of the file.
        write(str);
    }*/
    
    window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;
    
    window.requestFileSystem(window.TEMPORARY, 5*1024*1024 /*5MB*/, onInitFs, errorHandler);
    
    //alert(message);

    for(var i = 0; i<numParticles; i++){
        var geometry = new THREE.CubeGeometry(3, 3, 3);
        var material = new THREE.MeshNormalMaterial();
        /*material = new THREE.MeshLambertMaterial({color: Math.random()*0xffffff, 
                                                  shading: THREE.FlatShading, 
                                                  overdraw: true});*/   

        var mesh = new THREE.Mesh(geometry, material);
        mesh.name = "cube"+i;        
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.x = plusOrMinus*Math.random()*window.innerWidth*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.y = plusOrMinus*Math.random()*window.innerHeight*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.z = plusOrMinus*Math.random()*camera.position.z*0.2;
        
        scene.add(mesh);       
        
        objects.push(mesh);
    }

    renderer = new THREE.CanvasRenderer();
    renderer.setSize(window.innerWidth*0.98, window.innerHeight*0.98);

    //document.body.appendChild(renderer.domElement);
    //document.appendChild(renderer.domElement);
    var popup = document.getElementById("nBodyDiv");
    popup.appendChild(renderer.domElement);
    //document.documentElement;
    
    animate();
}

function animate() {
    //requestAnimationFrame(animate);
    setTimeout(function() {
        requestAnimationFrame(animate);
    }, 2000);
    //renderer.render();
    //setTimeout(render, 3000);
    render();
}

function sleep(milliseconds) {
    var start = new Date().getTime();
    //while ((new Date().getTime() - start) > milliseconds){}
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}

function render() {
    //mesh.rotation.x += 0.01;
    //mesh.rotation.y += 0.02;  
   
    //sleep(9000);
    
    /*setInterval(function(){
        for (var i = 0; i < objects.length; i++) {
            var mesh = objects[i];                

            plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.x = plusOrMinus*Math.random()*window.innerWidth*0.2;

            plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.y = plusOrMinus*Math.random()*window.innerHeight*0.2;

            plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.z = plusOrMinus*Math.random()*camera.position.z*0.2;
        }
        renderer.render(scene, camera);
    }, 3000);*/
   
    for (var i = 0; i < objects.length; i++) {
        var mesh = objects[i];                
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.x = plusOrMinus*Math.random()*window.innerWidth*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.y = plusOrMinus*Math.random()*window.innerHeight*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.z = plusOrMinus*Math.random()*camera.position.z*0.2;
    }

    renderer.render(scene, camera);            
    //window.setTimeout(function(){renderer.render(scene, camera);}, 3000);        
}

