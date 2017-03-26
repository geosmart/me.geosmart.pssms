// statisSaleAmount
var statisSaleAmount = {};
statisSaleAmount.partial = "../tmpl/statisSaleAmount.html";
statisSaleAmount.init = function () {
    console.log("init page:statisSaleAmount");
    statisSaleAmount.saleOrderStatis();

    $("#btnSaleOrderStatis").click(function () {
        var orderType = $("#selectOrderType").val();
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        statisSaleAmount.saleOrderStatis(orderType, beginDate, endDate);
    })
};

statisSaleAmount.saleOrderStatis = function (orderType, beginDate, endDate) {
    var queryObj = {
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

                option = {
                    // backgroundColor: "#344b58",
                    "title": {
                        "text": "销量统计图",
                        x: "4%",
                    },
                    "tooltip": {
                        "trigger": "axis",
                        "axisPointer": {
                            "type": "shadow",
                            textStyle: {
                                color: "#fff"
                            }
                        },
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {
                                show: true,
                                type: ['pie', 'funnel'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'left',
                                        max: 1548
                                    }
                                }
                            },
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    "grid": {
                        "borderWidth": 0,
                        "top": 110,
                        "bottom": 85,
                        textStyle: {
                            color: "#fff"
                        }
                    },
                    "legend": {
                        y: '4%',
                        top: '11%',
                        textStyle: {
                            color: '#90979c',
                        },
                        "data": ['销量']
                    },
                    "xAxis": [{
                        "type": "category",
                        "axisLine": {
                            lineStyle: {
                                color: '#90979c'
                            }
                        },
                        "axisTick": {
                            alignWithLabel: true
                        },
                        "axisLabel": {
                            "interval": 4,
                        },
                        "data": xAxisData,
                    }],
                    yAxis: [{
                        "type": "value",
                        name: '销量',
                        "axisLine": {
                            lineStyle: {
                                color: '#90979c'
                            }
                        },
                        position: 'left',
                        "axisTick": {
                            "show": false
                        },
                        "axisLabel": {
                            "interval": 0,
                            formatter: '{value} 件'
                        },
                        "splitArea": {
                            "show": false
                        }
                    }
                    ],
                    "dataZoom": [{
                        "show": true,
                        "height": 30,
                        "xAxisIndex": [
                            0
                        ],
                        bottom: 20,
                        start: 5,
                        end: 90,
                        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                        handleSize: '120%',
                        handleStyle: {
                            color: "#d3dee5",
                        },
                        textStyle: {
                            color: "#fff"
                        },
                        borderColor: "#90979c"
                    }, {
                        "type": "inside",
                        "show": true,
                        "height": 15,
                        "start": 1,
                        "end": 35
                    }],
                    "series": [{
                        "name": "销量",
                        "type": "bar",
                        "stack": "总量",
                        yAxisIndex: 0,
                        barMaxWidth: 40,
                        "itemStyle": {
                            "normal": {
                                "color": "#0091ea",
                                "label": {
                                    "show": false,
                                    "textStyle": {
                                        "color": "#fff"
                                    },
                                    "position": "insideTop",
                                    formatter: function (p) {
                                        return p.value > 0 ? (p.value) : '';
                                    }
                                }
                            }
                        },
                        "data": seriesData,
                    },
                        {
                            name: '退单使用金额/销售额',
                            type: 'pie',
                            center: ['75%', '12%'],
                            radius: '15%',
                            data: [
                                {value: res.netSaleAmount, name: '纯销售额'},
                                {value: res.backOrderLogSumAmount, name: '退单使用金额'}
                            ]
                        }
                    ]
                };
                myChart.setOption(option);
            },
            error: function (err) {
                console.log(err);
            }
        });
};





