
var dbObject; // always assign null
var arr =[];
function makeConnection(){

document.addEventListener("deviceready", onDeviceReady, false);
}
function onDeviceReady(){
if(dbObject == null || dbObject == undefined){
dbObject = window.sqlitePlugin.openDatabase({name:"json.db",createFromLocation: 1,location: 2,androidDatabaseImplementation: 2});
}else{
dbObject = window.sqlitePlugin.openDatabase({name:"json.db",location: 2,androidDatabaseImplementation: 2});	
}
}

function showValue(){

dbObject.transaction(function(executeQuery){
executeQuery.executeSql("select divisioncode from centredivision ",[],function(executeQuery,result){
console.log("check length : "+result.rows.length);
for(var i=0;i<result.rows.length;i++){
console.log("arrrrr  :"+result.rows.item(i));
arr.push(result.rows.item(i));
}



},function(error){
console.log("error");
});
});
}
function callPlugin(){
console.log("arrayFromDatabase  ----------- > "+JSON.stringify(arr));
cordova.plugins.sndUrl(arr,function(response){

    dbObject.transaction(function(insertQueryObject){

    console.log("response ------- >>> "+JSON.stringify(response));
console.log("response ----- "+response.length);
    for(var i=0;i<response.length;i++){
        var jsonObject = JSON.parse(response[i]);
        var divisoncode = arr[i].divisioncode;
        var recordArr = jsonObject.data;
       console.log("recordArr length ---- "+recordArr.length);
        for(var j=0;j<recordArr.length;j++){
               insertQueryObject.executeSql("insert into centerrange(rangecode,rangename,divisioncode) values('"+recordArr[j].c+"','"+recordArr[j].n+"','"+divisoncode+"')",[],function(insertQueryObject,res){
                                                                                                                     });
          }
    }
    },function(err){
             console.log("err ------ >> "+err);
             },function(){
             console.log("success");
      });
},function(err){
	console.log("err ----- "+err);
});

}
