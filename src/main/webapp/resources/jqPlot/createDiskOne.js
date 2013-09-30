/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var data = [
    ['Low', 30],['Medium', 20], ['High', 50]
  ];
  var plot1 = jQuery.jqplot ('resultOneDiv', [data], 
    { 
      title: 'Priority',
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
