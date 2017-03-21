//数据查询
var saleOrderQuery = {};
saleOrderQuery.partial = "../tmpl/saleOrder.html";
saleOrderQuery.init = function () {
    console.log("init page:saleOrderQuery");
    saleOrderQuery.request();
    $("#btnSaleOrderQuery").click(function () {
        var orderType = $("#selectOrderType").val();
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        var orderId = $("#tbxOrderId").val();
        var productCode = $("#tbxProductCode").val();
        var customerCode = $("#tbxCustomerCode").val();
        saleOrderQuery.request(orderType, beginDate, endDate, orderId, productCode, customerCode);
    })
};
saleOrderQuery.request = function (orderType, beginDate, endDate, orderId, productCode, customerCode) {
    var queryObj = {
        pageNumber: 1,
        pageSize: 5000,
        orderType: orderType,
        beginDate: beginDate,
        endDate: endDate,
        productCode: orderId,
        productCode: productCode,
        customerCode: customerCode
    };
    $.ajax(
        {
            type: "POST",
            url: api.saleOrderQuery,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(queryObj),
            success: function (res) {
                if (res.length == 0) {
                    $("#tableSaleOrder > tbody").html("");
                }
                else {
                    //TOTO 重复渲染有问题
                    $('#tableSaleOrder').bootstrapTable('destroy').bootstrapTable(
                        {
                            data: res.records,
                            totalRows: res.total,
                            pagination: true,
                            pageSize: 10,
                            sidePagination: "client",
                            pageList: [10, 20, 50, 100, 200]
                        });
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
}