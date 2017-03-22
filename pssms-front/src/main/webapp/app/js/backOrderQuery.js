//数据查询
var backOrderQuery = {};
backOrderQuery.partial = "../tmpl/backOrder.html";
backOrderQuery.init = function () {
    console.log("init page:backOrderQuery");
    backOrderQuery.request();
    $("#btnBackOrderQuery").click(function () {
        var backOrderStatus = $("#selectBackOrderStatus").val();
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        var backOrderId = $("#tbxBackOrderId").val();
        var customerCode = $("#tbxCustomerCode").val();
        backOrderQuery.request(beginDate, endDate, backOrderId, backOrderStatus, customerCode);
    })
};
backOrderQuery.request = function (beginDate, endDate, backOrderId, backOrderStatus, customerCode) {
    var queryObj = {
        pageNumber: 1,
        pageSize: 5000,
        backOrderStatus: backOrderStatus,
        backOrderId: backOrderId,
        beginDate: beginDate,
        endDate: endDate,
        customerCode: customerCode
    };
    $.ajax(
        {
            type: "POST",
            url: api.backOrderQuery,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(queryObj),
            success: function (res) {
                if (res.length == 0) {
                    $("#tableBackOrder > tbody").html("");
                }
                else {
                    //TOTO 重复渲染有问题
                    $('#tableBackOrder').bootstrapTable('destroy').bootstrapTable(
                        {
                            data: res.records,
                            totalRows: res.total,
                            pagination: true,
                            pageSize: 10,
                            sidePagination: "client",
                            pageList: [10, 20, 50, 100, 200]
                        }).show();
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
};