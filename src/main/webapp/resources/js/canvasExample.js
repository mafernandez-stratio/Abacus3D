if (!canvasExample) var canvasExample = {}

if (!canvasExample.js) {
    canvasExample.js = {      
        init:function(canvasId, lineWidth) {
            var canvas, context, painting;

            /*canvas = document.getElementById(canvasId);
            if (canvas == null) {
              alert("Canvas " + canvasId + " not found")
            }*/

            //THREE.JS
            var scene = new THREE.Scene();
            var camera = new THREE.PerspectiveCamera(75, window.innerWidth/window.innerHeight, 0.1, 1000);

            var renderer = new THREE.WebGLRenderer();
            renderer.setSize(window.innerWidth, window.innerHeight);
            document.body.appendChild(renderer.domElement);

            //var geometry = new THREE.CubeGeometry(1,1,1);
            var geometry = new THREE.SphereGeometry(50, 8, 6, 0, Math.PI * 2, 0, Math.PI);
            var material = new THREE.MeshBasicMaterial({color: 0x00ff00});
            var cube = new THREE.Mesh(geometry, material);
            scene.add(cube);

            /*camera.position.z = 5;

            var render = function () {
                requestAnimationFrame(render);

                cube.rotation.x += 0.1;
                cube.rotation.y += 0.1;

                renderer.render(scene, camera);
            };

            render();     */  
        }              
    };
}

