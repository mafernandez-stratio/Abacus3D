/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var line1 = [['CPU', 40], ['Mem', 30]];
  var plot1 = $.jqplot('gpuBarDiv', [line1], {
    title: {
      show: false  
    },
    seriesColors:['#0099FF'],
    series:[{
        renderer:$.jqplot.BarRenderer,         
        rendererOptions: {
            barWidth: 50
        },
        pointLabels:{
            show: true,
            labels:['40', '30'],
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
      },
      yaxis:{
        ticks: [0, 50, 100],
        tickOptions:{
            formatString:'%d\%'
        }
      }
    }
  });
  
});
