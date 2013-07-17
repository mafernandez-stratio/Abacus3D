
$(document).ready(function(){
    $.jqplot('externdiv',  [[3,7,9,1,4,6,8,2,5]]);
    var div = document.getElementById("chartdiv");
    var plot = document.getElementById("externdiv");
    div.appendChild(plot);
});

