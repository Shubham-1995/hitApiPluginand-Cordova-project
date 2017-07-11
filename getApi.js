var exec = require('cordova/exec');

exports.sndUrl = function(sqlArr, success, error) {
console.log("sqlArr ------ >>> "+JSON.stringify(sqlArr));
    exec(success, error, "responseApi", "sndUrl", [sqlArr]);
};
