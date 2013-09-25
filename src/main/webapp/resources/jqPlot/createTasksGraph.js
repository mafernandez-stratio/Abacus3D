/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
//    var line1=[['23-May-08', 578.55], ['25-Jul-08', 480.88], ['22-Aug-08', 509.84],
//        ['26-Sep-08', 454.13], ['21-Nov-08', 303], ['26-Dec-08', 308.56],
//        ['23-Jan-09', 299.14], ['20-Mar-09', 325.99], ['24-Apr-09', 386.15]];
//    var line2=[['23-May-08', 478.55], ['25-Jul-08', 610.88], ['22-Aug-08', 609.84],
//        ['26-Sep-08', 554.13], ['21-Nov-08', 203], ['26-Dec-08', 508.56],
//        ['23-Jan-09', 209.14], ['20-Mar-09', 375.99], ['24-Apr-09', 316.15]];
//    var line3=[['23-May-08', 123.55], ['25-Jul-08', 345.88], ['22-Aug-08', 223.84],
//        ['26-Sep-08', 244.13], ['21-Nov-08', 333], ['26-Dec-08', 348.56],
//        ['23-Jan-09', 449.14], ['20-Mar-09', 225.99], ['24-Apr-09', 236.15]];
//    var line4=[['23-May-08', 193.55], ['25-Jul-08', 395.88], ['22-Aug-08', 443.84],
//        ['26-Sep-08', 554.13], ['21-Nov-08', 223], ['26-Dec-08', 408.56],
//        ['23-Jan-09', 669.14], ['20-Mar-09', 115.99], ['24-Apr-09', 506.15]];
    var line1=[['23:12:10', 200], ['23:13:19', 220], ['23:14:37', 180], ['23:15:57', 180]];
    var line2=[['23:12:10', 100], ['23:13:19', 90], ['23:14:37', 110], ['23:15:57', 120]];
    var line3=[['23:12:10', 300], ['23:13:19', 310], ['23:14:37', 290], ['23:15:57', 300]];
//    var line4=[['23:12:10', 493], ['23:13:19', 359], ['23:14:37', 555], ['23:15:57', 233]];
    var plot1 = $.jqplot('dashboardFirstDiv', [line1, line2, line3], {
        seriesDefaults: {
            fill: true,
            fillAndStroke: true,
            fillAlpha: 0.2
        },  
        seriesColors: ['#6495ED', '#73C774', '#C7754C'],
        series:[
            {label:'Running'},
            {label:'Queued'},
            {label:'Total'}
        ], 
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
