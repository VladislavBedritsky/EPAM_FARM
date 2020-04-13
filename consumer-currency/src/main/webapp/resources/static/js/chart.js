var chartName = startDate+' : '+endDate ;
var chart1;

$(document).ready(function(){
  chart1 = new Highcharts.Chart({
    chart: {renderTo: 'container1'},
    title: {text: chartName},
    series: [
        {data: rub, name: 'RUB'},
        {data: usd, name: 'USD', color: 'green'},
        {data: eur, name: 'EUR', color: 'brown'}
    ],
    xAxis: {
        categories: datesArray
    }
  });
});