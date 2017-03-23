//数据查询
var statis = {};
statis.partial = "../tmpl/statis.html";
statis.init = function () {
    console.log("init page:statis");
    $("#btnSaleOrderStatis").click(function () {
        var orderType = $("#selectOrderType").val();
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        statis.saleOrderStatis(orderType, beginDate, endDate);
    })
};

statis.saleOrderStatis = function (orderType, beginDate, endDate) {
    var queryObj = {
        pageNumber: 1,
        pageSize: 5000,
        orderType: orderType,
        beginDate: beginDate,
        endDate: endDate
    };
    $.ajax(
        {
            type: "POST",
            url: api.saleOrderStatis,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(queryObj),
            success: function (res) {
                console.log(res);
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('divStatis'));
                // res.saleOrderSumAmount backOrderLogSumAmount netSaleAmount
                var xAxisData = [];
                var seriesData = [];
                $(res.productSaleAmount).each(function (index, item) {
                    $.map(item, function (value, key) {
                        xAxisData.push(key);
                        seriesData.push(value);
                    });
                });
                var option = {
                    title: {
                        text: '货号-销售金额统计图'
                    },
                    tooltip: {},
                    legend: {
                        data: ['销量']
                    },
                    xAxis: {
                        data: xAxisData
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: seriesData
                    }]
                };
                myChart.setOption(option);
            },
            error: function (err) {
                console.log(err);
            }
        });
};


