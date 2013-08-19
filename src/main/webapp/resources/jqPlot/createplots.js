
function buildplots(strOne, strTwo){
    $.jqplot('externdiv',  strOne);
    var div = document.getElementById("chartdiv");  
    var plot = document.getElementById("externdiv");    
    div.appendChild(plot);
    
    $.jqplot ('piechart', strTwo, { 
        seriesDefaults: {
            // Make this a pie chart.
            renderer: jQuery.jqplot.PieRenderer, 
            rendererOptions: {
              // Put data labels on the pie slices.
              // By default, labels show the percentage of the slice.
              showDataLabels: true
            }
        }, 
        legend: {show:false, location: 'e'}
    });      
    
    var piechart = document.getElementById("piechart");  
    var piediv = document.getElementById("piediv");    
    piediv.appendChild(piechart);
}


