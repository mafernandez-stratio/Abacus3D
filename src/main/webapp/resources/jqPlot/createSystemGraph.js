/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var line1=[['23:12:10', 30], ['23:13:19', 33], ['23:14:37', 40], ['23:15:57', 50]];
    var line2=[['23:12:10', 50], ['23:13:19', 60], ['23:14:37', 55], ['23:15:57', 65]];
    var line3=[['23:12:10', 40], ['23:13:19', 45], ['23:14:37', 30], ['23:15:57', 25]];
    var line4=[['23:12:10', 80], ['23:13:19', 70], ['23:14:37', 60], ['23:15:57', 35]];
    var plot1 = $.jqplot('dashboardSecondDiv', [line1, line2, line3, line4], {        
        series:[
            {label:'CPU'},
            {label:'MEM'},
            {label:'GPU'},
            {label:'Load'}
        ], 
        seriesDefaults: { 
            showMarker:false
        },
        legend: {
            renderer: $.jqplot.EnhancedLegendRenderer,
            show: true,
            rendererOptions: {
                numberRows: 1
            },
            placement: 'outside',
            location: 'n'            
        },
        axes:{
          xaxis:{
            renderer:$.jqplot.DateAxisRenderer,
            tickOptions:{
              formatString:'%H:%M.%S'
            }            
          },
          yaxis:{
            ticks: [0, 20, 40, 60, 80, 100],
            tickOptions:{
                formatString:'%d\%'
            }
          }
        },
        highlighter: {
          show: true,
          sizeAdjust: 7.5
        },
        cursor: {
          show: false
        }
    });
});
