// statisBackRate
var statisBackRate = {};
statisBackRate.partial = "../tmpl/statisBackRate.html";
statisBackRate.init = function () {
    console.log("init page:statisBackRate");
    statisBackRate.saleOrderStatis();

    $("#btnSaleOrderStatis").click(function () {
        var beginDate = $("#tbxBeginDate").val();
        var endDate = $("#tbxEndDate").val();
        statisBackRate.saleOrderStatis(orderType, beginDate, endDate);
    })
};

statisBackRate.saleOrderStatis = function (beginDate, endDate) {
    var queryObj = {
        beginDate: beginDate,
        endDate: endDate
    };
    $.ajax(
        {
            type: "POST",
            url: api.backRateStatis,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(queryObj),
            success: function (res) {
                console.log(res);
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('divStatis'));
                // res.saleOrderSumAmount backOrderLogSumAmount netSaleAmount
                var xAxisData = [];
                var saleSeriesData = [];
                var backSeriesData = [];
                var backRateSeriesData = [];
                $(res).each(function (index, item) {
                    $.map(item, function (value, key) {
                        xAxisData.push(key);
                        saleSeriesData.push(value["销售量"]);
                        backSeriesData.push(value["退货量"]);
                        var backRate = isNaN(value["退货率"]) ? 0 : (parseFloat(value["退货率"]).toFixed(2)) * 100;
                        backRateSeriesData.push(backRate);
                    });
                });

                option = {
                    // backgroundColor: "#344b58",
                    "title": {
                        "text": "退货率统计图",
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
                        "data": ['销售量', '退货量', '退货率']
                    },
                    "calculable": true,
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
                        name: '销售量',
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
                    }, {
                        type: 'value',
                        name: '退货率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        offset: 10,
                        axisLine: {
                            lineStyle: {
                                "color": "#d50000"
                            }
                        },
                        axisLabel: {
                            formatter: '{value} %'
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
                        "name": "销售量",
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
                        "data": saleSeriesData,
                    }, {
                        "name": "退货量",
                        "type": "bar",
                        "stack": "总量",
                        yAxisIndex: 0,
                        "itemStyle": {
                            "normal": {
                                "color": "#d50000",
                                "barBorderRadius": 0,
                                "label": {
                                    "show": false,
                                    "position": "top",
                                    formatter: function (p) {
                                        return p.value > 0 ? (p.value) : '';
                                    }
                                }
                            }
                        },
                        "data": backSeriesData
                    }, {
                        name: "退货率",
                        type: "line",
                        smooth: true,
                        yAxisIndex: 1,
                        symbolSize: 10,
                        symbol: 'circle',
                        lineStyle: {
                            normal: {
                                width: 3,
                                color: '#388e3c',
                                label: {
                                    "show": false,
                                    "position": "top",
                                    formatter: function (p) {
                                        return p.value > 0 ? (p.value) + " %" : ''
                                    }
                                }
                            }
                        },
                        "data": backRateSeriesData
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





