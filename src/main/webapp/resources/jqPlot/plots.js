
$(document).ready(function(){
    $.jqplot('externdiv',  [[3,7,9,1,4,6,8,2,5]]);
    var div = document.getElementById("chartdiv");  
    var plot = document.getElementById("externdiv");    
    div.appendChild(plot);
    
    
    /*var data = [['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
                ['Out of home', 16],['Commuting', 7], ['Orientation', 9]];*/
    var data = [12, 9, 14, 16, 7, 9];
    $.jqplot ('piediv', [data], { 
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
    
});

