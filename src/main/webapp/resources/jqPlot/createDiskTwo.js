/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
  var data = [
    ['Admin', 240],['Cediant', 142], ['User', 108]
  ];
  var plot1 = jQuery.jqplot ('resultTwoDiv', [data], 
    { 
      title: 'User',
      seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          dataLabels: 'value',
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
