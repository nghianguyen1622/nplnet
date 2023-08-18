// Sales Report by Date
var data;
var chartOptions;
var totalGrossSales;
var totalNetSales;
var totalItems;

$(document).ready(function() {
	setupButtonEventHandlers("_date", loadSalesReportByDate);	
});

function loadSalesReportByDate(period) {
	if (period == "custom") {
		startDate = $("#startDate_date").val();
		endDate = $("#endDate_date").val();
		
		requestURL = contextPath + "reports/sales_by_date/" + startDate + "/" + endDate;
	} else {
		requestURL = contextPath + "reports/sales_by_date/" + period;		
	}
	
	$.get(requestURL, function(responseJSON) {
		prepareChartDataForSalesReportByDate(responseJSON);
		customizeChartForSalesReportByDate(period);
		formatChartData(data, 1, 2);
		drawChartForSalesReportByDate(period);
		setSalesAmount(period, '_date', "Tổng đơn hàng");
	});
}

function prepareChartDataForSalesReportByDate(responseJSON) {
	data = new google.visualization.DataTable();
	data.addColumn('string', 'Date');
	data.addColumn('number', 'Doanh thu');
	data.addColumn('number', 'Lãi ròng');
	data.addColumn('number', 'Đơn hàng');
	
	totalGrossSales = 0.0;
	totalNetSales = 0.0;
	totalItems = 0;
	
	$.each(responseJSON, function(index, reportItem) {
		data.addRows([[reportItem.identifier, reportItem.grossSales, reportItem.netSales, reportItem.ordersCount]]);
		totalGrossSales += parseFloat(reportItem.grossSales);
		totalNetSales += parseFloat(reportItem.netSales);
		totalItems += parseInt(reportItem.ordersCount);
	});
}

function customizeChartForSalesReportByDate(period) {
	chartOptions = {
		title: getChartTitle(period),
		'height': 360,
		bar: { groupWidth: '75%' },
		legend: {position: 'top', maxLines: 3},
		vAxis: {format: 'decimal'},
		colors: ['#1b9e77', '#d95f02', '#7570b3'],
		series: {
			0: {targetAxisIndex: 0},
			1: {targetAxisIndex: 0},
			2: {targetAxisIndex: 1},
		},
		
		vAxes: {
			0: {title: 'Doanh số bán ra'},
			1: {title: 'Số đơn hàng'}
		}
	};
}

function drawChartForSalesReportByDate() {
	var salesChart = new google.visualization.ColumnChart(document.getElementById('chart_sales_by_date'));
	salesChart.draw(data, chartOptions);
}