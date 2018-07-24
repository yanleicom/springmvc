peioption={
    title: {
        text: '2018年2月主要指标完成情况(计量单位:亿元)',
        x:'center'
   },
   tooltip: {
       trigger: 'item',
       formatter: "数据来源:统计局，财政局"
   },
   legend: {
       top:40,
       orient: 'vertical',
       x: 'left',
       data:['地区生产总值','工业增加值','第三产业','财政总收入','公共预算','消费品零售','固定投资','外贸出口','单位GDP','高新技术','杭外到位资金','到位外资','浙商到位资金']
   },
   series: [
       {
           name:'累计完成',
           type:'pie',
           selectedMode: 'single',
           radius: [0, '30%'],

           label: {
               normal: {
                   position: 'inner'
               }
           },
           labelLine: {
               normal: {
                   show: false
               }
           },
           data:[
               {value:335, name:'地区生产总值', selected:true},
               {value:679, name:'工业增加值'},
               {value:1548, name:'第三产业'}
           ]
       },
       {
           name:'累计完成',
           type:'pie',
           radius: ['40%', '55%'],
           label: {
               normal: {
                   formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                   backgroundColor: '#eee',
                   borderColor: '#aaa',
                   borderWidth: 1,
                   borderRadius: 4,
                   rich: {
                       a: {
                           color: '#999',
                           lineHeight: 22,
                           align: 'center'
                       },
                       hr: {
                           borderColor: '#aaa',
                           width: '100%',
                           borderWidth: 0.5,
                           height: 0
                       },
                       b: {
                           fontSize: 12,
                           lineHeight: 24
                       },
                       per: {
                           color: '#eee',
                           backgroundColor: '#334455',
                           padding: [2, 4],
                           borderRadius: 2
                       }
                   }
               }
           },
           data:[
               {value:335, name:'财政总收入'},
               {value:310, name:'公共预算'},
               {value:234, name:'消费品零售'},
               {value:367, name:'固定投资'},
               {value:570, name:'外贸出口'},
               {value:251, name:'高新技术'},
               {value:543, name:'杭外到位资金'},
               {value:147, name:'浙商到位资金'},
               {value:102, name:'到位外资'}
           ]
       }
   ]
};
baroption={
    title: {
        text: '2018年2月街道经济',
        x:'center'
   },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        top:30,
        data:['限额消费品零售额','固定资产投资','地方公共预算','杭外到位资金','到位外资','浙商到位资金','限额贸易销售额','其他服务业']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['天水街道','武林街道','长庆街道','潮鸣街道','朝晖街道','文晖街道','东新街道','石桥街道']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'限额消费品零售额',
            type:'bar',
            data:[320, 332, 301, 334, 390, 330, 320,300]
        },
        {
            name:'固定资产投资',
            type:'bar',
            stack: '广告',
            data:[120, 132, 101, 134, 90, 230, 210,200]
        },
        {
            name:'地方公共预算',
            type:'bar',
            stack: '广告',
            data:[220, 182, 191, 234, 290, 330, 310,290]
        },
        {
            name:'杭外到位资金',
            type:'bar',
            stack: '广告',
            data:[150, 232, 201, 154, 190, 330, 410,180]
        },
        {
            name:'到位外资',
            type:'bar',
            data:[862, 1018, 964, 1026, 1679, 1600, 1570,1389],
        },
        {
            name:'浙商到位资金',
            type:'bar',
            barWidth : 5,
            stack: '搜索引擎',
            data:[620, 732, 701, 734, 1090, 1130, 1120,800]
        },
        {
            name:'限额贸易销售额',
            type:'bar',
            stack: '搜索引擎',
            data:[120, 132, 101, 134, 290, 230, 220,240]
        },
        {
            name:'其他服务业',
            type:'bar',
            stack: '搜索引擎',
            data:[60, 72, 71, 74, 190, 130, 110,110]
        }
    ]
};
lineoption={
    title: {
        text: '2018年2月财政收支',
        x:'center'
   },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['本月','累计','同比%'],
        top:30
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['地方公共收入','增值税','营业税','企业所得税','个人所得税','公共预算支出']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'本月',
            type:'line',
            stack: '总量',
            data:[120, 132, 101, 134, 90, 230]
        },
        {
            name:'累计',
            type:'line',
            stack: '总量',
            data:[220, 182, 191, 234, 290, 330]
        },
        {
            name:'同比%',
            type:'line',
            stack: '总量',
            data:[150, 232, 201, 154, 190, 330]
        },
        
    ]
};
neioption={
    title: {
        text: '2018年2月国内贸易',
        x:'center'
   },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['本月','累计','同比%'],
        top:30
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['批发和零售业消费','住宿和餐饮消费','批发和零售业销售','住宿和餐饮销售']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'本月',
            type:'line',
            stack: '总量',
            data:[120, 132, 101, 134]
        },
        {
            name:'累计',
            type:'line',
            stack: '总量',
            data:[220, 182, 191, 234]
        },
        {
            name:'同比%',
            type:'line',
            stack: '总量',
            data:[150, 232, 201, 154]
        },
        
    ]
};
zhioption={
    title: {
        text: '2017年社会指标情况表',
        x:'center'
   },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['绝对值','同比%'],
        top:30
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['户籍人口','总户数','常住人口','年末从业人员','在岗职工','登记失业','专利申请数','专利受权数']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'绝对值',
            type:'line',
            stack: '总量',
            data:[220, 182, 191, 234,183,46,87,277]
        },
        {
            name:'同比%',
            type:'line',
            stack: '总量',
            data:[15, 23, 20, 54,16,46,36,47]
        },
        
    ]
};