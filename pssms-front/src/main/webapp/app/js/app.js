//initialize
var api={};
api.host = "http://10.10.1.20:8080";
api.backOrderQuery= api.host + "/api/backOrder/query";
api.saleOrderQuery = api.host + "/api/saleOrder/query";

miniSPA.changeUrl();