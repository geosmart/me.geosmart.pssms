
//global parameters
var settings = {};
//cache for partial pages
settings.partialCache = {};
//div for loading partials
settings.mainContent = document.getElementById("page-wrapper");

var api={};
// api.host = "http://10.10.1.20:8080";
api.host = "http://localhost:8080";
api.backOrderQuery= api.host + "/api/backOrder/query";
api.saleOrderQuery = api.host + "/api/saleOrder/query";
api.saleOrderStatis = api.host + "/api/statis/saleOrderStatis";
api.backRateStatis = api.host + "/api/statis/backRateStatis";

