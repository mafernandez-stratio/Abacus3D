var camera, scene, renderer, objects = [];

//init();
//animate();
    
//var numParticles = "#{nBodysBean.numParticles}";
var numParticles;
//var numSteps = "#{nBodysBean.numSteps}";
var numSteps;

var message = '';

var currentStep = 0;

var frames = [];

var scale = 0.04;

var verbose = false;

/*
function onInitFs(fs) {
    lines = '';
    message = 'Opened file system: ' + fs.name;
    var number = currentStep;
    if(currentStep<10){
        number = '00'+currentStep;
    } else if (currentStep>9 && currentStep<100){
        number = '0'+currentStep;
    }
    number = '001';
    fs.root.getFile('../txt/frame'+number+'.txt', {}, function(fileEntry) {
        // Get a File object representing the file,
        // then use FileReader to read its contents.
        fileEntry.file(function(file) {
            var reader = new FileReader();
            reader.onloadend = function(e) { 
                var text = " ";
                text = reader.result;
                lines = text.split('\n');
            };
            reader.readAsText(file);
            //file.size;
            //var blob = file.slice(start, stop + 1);
            //reader.readAsText(blob);                                   
        }, errorHandler);
    }, errorHandler);
}
*/

/*
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
*/

function init(nParticles, nSteps, str) {            
    
    currentStep = 0;
    numParticles = nParticles;
    numSteps = nSteps;
    
    frames = str.split('*');       
    
    if(verbose){
        alert(frames.length+' frames');
    }
    
    var currentFrame = frames[currentStep];
    
    var lines = currentFrame.split('_');  
    
    if(verbose){
        alert(lines.length+' particles');
    }
    
    /*alert(str);
    alert(typeof str);
    alert(str.length);
    
    var coordinates = str.split("_");
    
    alert(typeof coordinates);
    alert(coordinates.length);
    var firstLine = coordinates[0];
    alert(firstLine);
    var splitNum = firstLine.split(";");
    var number = splitNum[0];
    alert(number);
    var coordinates = splitNum[1];
    alert(coordinates);
    var firstCoordinates = coordinates.split(",");
    var coordinateX = firstCoordinates[0];
    alert(coordinateX);*/   

    //alert("START");
    //alert(nParticles);
    //alert(nSteps);  

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
    
    //window.requestFileSystem  = window.requestFileSystem || window.webkitRequestFileSystem;        
    
    //alert(message);

    //alert(numParticles+' particles');

    for(var i = 0; i<numParticles; i++){
        
        var currentLine = lines[i];
        
        if((i==0) && verbose){
            alert('line: '+currentLine);
        }
        
        //window.requestFileSystem(window.TEMPORARY, 5*1024*1024 /*5MB*/, onInitFs, errorHandler);
        
        

        //alert(i);
        
        //alert(typeof lines);
        //alert(lines.length);
        //var splitNumber = lines[i].split(';');
        //alert('splitNumber');
        //mesh.name = "cube"+splitNumber[0];       
        //alert('mesh.name');
        //var coordinates = splitNumber[1].split(',');
        //alert('coordinates');
        
        //mesh.position.x = coordinates[0]/5;
        
        //mesh.position.y = coordinates[1]/5;
        
        //mesh.position.z = coordinates[2]/5;
        
        var currentSplitNum = currentLine.split(";");
        var currentNumber = currentSplitNum[0];
        
        if((i==0) && verbose){
            alert('number: '+currentNumber);
        }       

        var currentSplitCoordinates = currentSplitNum[1];

        if((i==0) && verbose){
            alert('Coordinates line: '+currentSplitCoordinates);
        }

        var currentCoordinates = currentSplitCoordinates.split(",");
        if((i==0) && verbose){
            alert(currentCoordinates.length+' coordinates');
        }
        
        var currentCoordinateX = currentCoordinates[0];        
        var currentCoordinateY = currentCoordinates[1];        
        var currentCoordinateZ = currentCoordinates[2];
        
        if((i==0) && verbose){
            alert('coordinates: '+currentCoordinateX+','+currentCoordinateY+','+currentCoordinateZ);
        }
        
        if((i==0) && verbose){
            alert('mesh.position: '+mesh.position.x+','+mesh.position.y+','+mesh.position.z);
        }
        
        /*mesh.name = "cube"+i;  
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.x = plusOrMinus*Math.random()*window.innerWidth*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.y = plusOrMinus*Math.random()*window.innerHeight*0.2;
        
        plusOrMinus = Math.random()<0.5?-1:1; 
        mesh.position.z = plusOrMinus*Math.random()*camera.position.z*0.2;*/
        
        var geometry = new THREE.CubeGeometry(8, 8, 8);
        //var material = new THREE.MeshNormalMaterial();
        var material = new THREE.MeshLambertMaterial({color: 'blue'});
        if(currentCoordinateX<0){
            material = new THREE.MeshLambertMaterial({color: 'red'});
        }        
                                              
        var mesh = new THREE.Mesh(geometry, material);
        mesh.overdraw = true;
        mesh.name = "cube"+currentNumber;
        mesh.position.x = currentCoordinateX*window.innerWidth*scale;
        mesh.position.y = currentCoordinateY*window.innerHeight*scale;
        mesh.position.z = currentCoordinateZ*camera.position.z*scale;
        
        scene.add(mesh);       
        
        // add subtle ambient lighting
        var ambientLight = new THREE.AmbientLight(0x000044);
        scene.add(ambientLight);
        
        // directional lighting
        var directionalLight = new THREE.DirectionalLight(0xffffff);
        directionalLight.position.set(1, 1, 1).normalize();
        scene.add(directionalLight);
        
        // directional lighting
        var directionalLightNeg = new THREE.DirectionalLight(0xffffff);
        directionalLightNeg.position.set(-1, -1, -1).normalize();
        scene.add(directionalLightNeg);
        
        objects.push(mesh);
    }

    renderer = new THREE.CanvasRenderer();
    renderer.setSize(window.innerWidth*0.98, window.innerHeight*0.98);

    //document.body.appendChild(renderer.domElement);
    //document.appendChild(renderer.domElement);
    var popup = document.getElementById("nBodyDiv");
    popup.appendChild(renderer.domElement);
    //document.documentElement;
    
    currentStep++;
    
    animate();
}

function animate() {
    //requestAnimationFrame(animate);
    setTimeout(function() {
        requestAnimationFrame(animate);
    }, 800);
    //renderer.render();
    //setTimeout(render, 3000);
    render();
}

/*function sleep(milliseconds) {
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
}*/

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
    
    if (verbose){
        alert('current step: '+currentStep);
    }
    
    if (currentStep<numSteps){
    //if (currentStep<-999){
        
        for (var i = 0; i<objects.length; i++) {
            var mesh = objects[i];                                 
            
            var currentFrame = frames[currentStep];
    
            var lines = currentFrame.split('_');
            
            var currentLine = lines[i];
            
            var currentSplitNum = currentLine.split(";");
            var currentNumber = currentSplitNum[0];

            mesh.name = "cube"+currentNumber;

            var currentSplitCoordinates = currentSplitNum[1];

            var currentCoordinates = currentSplitCoordinates.split(",");
            var currentCoordinateX = currentCoordinates[0];
            mesh.position.x = currentCoordinateX*window.innerWidth*scale;
            var currentCoordinateY = currentCoordinates[1];
            mesh.position.y = currentCoordinateY*window.innerHeight*scale;
            var currentCoordinateZ = currentCoordinates[2];
            mesh.position.z = currentCoordinateZ*camera.position.z*scale;
            
            if((i==0) && verbose){
                alert('coordinates: '+currentCoordinateX+','+currentCoordinateY+','+currentCoordinateZ);
                alert('mesh.position: '+mesh.position.x+','+mesh.position.y+','+mesh.position.z);
            }

            /*plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.x = plusOrMinus*Math.random()*window.innerWidth*0.2;

            plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.y = plusOrMinus*Math.random()*window.innerHeight*0.2;

            plusOrMinus = Math.random()<0.5?-1:1; 
            mesh.position.z = plusOrMinus*Math.random()*camera.position.z*0.2;*/

            //window.requestFileSystem(window.TEMPORARY, 5*1024*1024 /*5MB*/, onInitFs, errorHandler);

            //var splitNumber = lines[i].split(';');
            //mesh.name = "cube"+splitNumber[0];        
            //var coordinates = splitNumber[1].split(',');

            //mesh.position.x = coordinates[0]/5;

            //mesh.position.y = coordinates[1]/5;

            //mesh.position.z = coordinates[2]/5;                            
        }   
        currentStep++;
        if(currentStep==numSteps){
            alert("END");
        }
    }       

    renderer.render(scene, camera);            
    //window.setTimeout(function(){renderer.render(scene, camera);}, 3000);        
}

