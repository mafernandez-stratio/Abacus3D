/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var data = [['Free', 40], ['Mem', 60]];
  var plot1 = jQuery.jqplot('diskBarDiv', [data], {          
    seriesColors:['#0099FF', '#F6A828'],
    title: {
      show: false  
    },
    seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer,         
        rendererOptions: {
            showDataLabels: true,
            padding: 1,
            sliceMargin: 0,
            dataLabels: 'label'
        }
    },
    legend: {
        show: false
    }
  });
  
});
