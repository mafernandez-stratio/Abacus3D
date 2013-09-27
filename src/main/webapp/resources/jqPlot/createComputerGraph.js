/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var line1 = [['CPU', 50], ['Mem', 75], ['Swap', 20]];
  var plot1 = $.jqplot('computerBarDiv', [line1], {
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
            labels:['50', '75', '20'],
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
