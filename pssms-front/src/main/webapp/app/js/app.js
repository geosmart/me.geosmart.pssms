//数据查询
var saleOrderQuery = {};
saleOrderQuery.partial = "../tmpl/saleOrder.html";
saleOrderQuery.init = function () {
    console.log("init page:saleOrderQuery");
    $("#tbxProductCode").val("6025");
    // saleOrderQuery.request("6025");
    $("#btnSaleOrderQuery").click(function () {
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        var orderId = $("#tbxOrderId").val();
        var productCode = $("#tbxProductCode").val();
        var customerCode = $("#tbxCustomerCode").val();
        saleOrderQuery.request(beginDate, endDate, orderId, productCode, customerCode);
    })
};
saleOrderQuery.request = function (beginDate, endDate, orderId, productCode, customerCode) {
    var queryObj = {
        pageNumber: 1,
        pageSize: 5000,
        orderType: 1,
        beginDate: beginDate,
        endDate: endDate,
        productCode: orderId,
        productCode: productCode,
        customerCode: customerCode
    };
    $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/api/saleOrder/query",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(queryObj),
            success: function (res) {
                if (res.length == 0) {
                    $("#table > tbody").html("");
                }
                else {
                    //TOTO 重复渲染有问题
                    $('#table').bootstrapTable('destroy');
                    $('#table').bootstrapTable(
                        {
                            data: res.records,
                            totalRows: res.total,
                            pagination: true,
                            pageSize: 10,
                            sidePagination: "client",
                            pageList: [10, 20, 50, 100, 200]
                            // data-search="true"
                        });
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
}

//initialize
miniSPA.changeUrl();