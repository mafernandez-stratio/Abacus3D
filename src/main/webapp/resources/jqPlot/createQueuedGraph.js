/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var line1 = [['Queued', 180]];
  var plot1 = $.jqplot('queuedBarDiv', [line1], {
    title: {
      show: false  
    },
    seriesColors:['#0099FF'],
    series:[{
        renderer:$.jqplot.BarRenderer,         
        rendererOptions: {
            barWidth: 100
        },
        pointLabels:{
            show: true,
            labels:['180'],
            location: 's',
            ypadding: 1,
            edgeTolerance: -6
        }
    }],
    axesDefaults: {
        tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
        tickOptions: {
          fontSize: '6pt'
        }
    },
    axes: {
      xaxis: {
        renderer: $.jqplot.CategoryAxisRenderer
      }
    }
  });
  
});
