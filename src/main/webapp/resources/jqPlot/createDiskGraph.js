/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//
//$(document).ready(function(){
//  var data = [['Free', 40], ['Mem', 60]];
//  var plot1 = jQuery.jqplot('diskBarDiv', [data], {          
//    seriesColors:['#0099FF', '#F6A828'],
//    title: {
//      show: false  
//    },
//    seriesDefaults: {
//        renderer: jQuery.jqplot.PieRenderer,         
//        rendererOptions: {
//            showDataLabels: true,
//            padding: 1,
//            sliceMargin: 0,
//            dataLabels: 'label'
//        }
//    },
//    legend: {
//        show: false
//    }
//  });
//  
//});
//


$(document).ready(function(){
  var line1 = [['Disk', 75]];
  var plot1 = $.jqplot('diskBarDiv', [line1], {
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
            labels:['75'],
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

