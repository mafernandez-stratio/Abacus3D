if (!smiley) var smiley = {}

if (!smiley.js) {
  smiley.js = {
    init : function(canvasId, penColor, lineWidth) {
      var canvas, context

      canvas = document.getElementById(canvasId);
      if (canvas == null) {
        alert("Canvas " + canvasId + " not found")
      }

      context = canvas.getContext("2d");
      if (context == null)
        return

      // smiley face code originally downloaded
      // from thinkvitamin.com
        
      // Create the face
      context.strokeStyle = "#000000";
      context.fillStyle = "#AAAAFF";
      context.beginPath();
      context.arc(100,100,50,0,Math.PI*2,true);
      context.closePath();
      context.stroke();
      context.fill();
      
      // eyes              
      context.strokeStyle = "#000000";
      context.fillStyle = "#FFFFFF";
      context.beginPath();
      context.arc(80,80,8,0,Math.PI*2,true);
      context.closePath();
      context.stroke();
      context.fill();
      
      context.fillStyle = "#0000FF";
      context.beginPath();
      context.arc(80,80,5,0,Math.PI*2,true);
      context.closePath();
      context.fill();
      
      context.strokeStyle = "#000000";
      context.fillStyle = "#FFFFFF";
      context.beginPath();
      context.arc(120,80,8,0,Math.PI*2,true);
      context.closePath();
      context.stroke();
      context.fill();
      
      context.fillStyle = "#0000FF";
      context.beginPath();
      context.arc(120,80,5,0,Math.PI*2,true);
      context.closePath();
      context.fill();
      
      // nose
      context.fillStyle = "#000000";
      context.beginPath();
      context.moveTo(93,100);
      context.lineTo(100,93);
      context.lineTo(107,100);
      context.closePath();
      context.fill();

      // smile              
      context.strokeStyle = "#000000";
      context.beginPath();
      context.moveTo(70,110);
      context.quadraticCurveTo(100,150,130,110);       
      context.closePath();
      context.stroke();    }
  }
}