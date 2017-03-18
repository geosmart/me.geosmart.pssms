//数据查询
var saleOrderQuery = {};
saleOrderQuery.partial = "../tmpl/saleOrder.html";
saleOrderQuery.init = function () {
    console.log("init page:saleOrderQuery");
    $("#tbxProductCode").val("6025");
    saleOrderQuery.request("6025");
    $("#btnSaleOrderQuery").click(function () {
        var order_id = $("#tbxOrderId").val();
        var product_code = $("#tbxProductCode").val();
        saleOrderQuery.request(product_code);
    })
};
saleOrderQuery.request = function (product_code) {
    $.ajax({
               type: "GET",
               url: "http://localhost:8080/api/saleOrder/query?product_code=" + product_code,
               contentType: "application/json; charset=utf-8",
               success: function (res) {
                   if (res.length == 0) {
                       $("#table > tbody").html("");
                   }
                   else {
                       $('#table').bootstrapTable({
                                                      data: res
                                                  });
                   }
               }
               ,
               error: function (err) {
                   console.log(err);
               }
           });
}

//initialize
miniSPA.changeUrl();