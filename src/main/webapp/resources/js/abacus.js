
var onclear = function (event) {  
    var data = event.rf.data;  
    for (var i in data) {  
        var item = data[i];  
        removeFile(item.name);  
    } 
};


