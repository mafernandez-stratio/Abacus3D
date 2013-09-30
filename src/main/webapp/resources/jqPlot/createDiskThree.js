/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var data = [
    ['Octave', 15],['Tests', 85]
  ];
  var plot1 = jQuery.jqplot ('resultThreeDiv', [data], 
    { 
      title: 'Type',
      seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          dataLabels: 'percent',
          showDataLabels: true,
          padding: 4
        }
      }, 
      legend: { 
          renderer : $.jqplot.PieLegendRenderer,
          show: true,
          placement: 'outside',
          location: 's',
          rendererOptions: {
              numberRows: 1
          }
      }
    }
  );
});
