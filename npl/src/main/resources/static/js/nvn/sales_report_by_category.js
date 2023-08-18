// Sales Report by Category
var data;
var chartOptions;

$(document).ready(function() {
	setupButtonEventHandlers("_category", loadSalesReportByDateForCategory);	
});

function loadSalesReportByDateForCategory(period) {
	if (period == "custom") {
		startDate = $("#startDate_category").val();
		endDate = $("#endDate_category").val();
		
		requestURL = contextPath + "reports/category/" + startDate + "/" + endDate;
	} else {
		requestURL = contextPath + "reports/category/" + period;		
	}
	
	$.get(requestURL, function(responseJSON) {
		prepareChartDataForSalesReportByCategory(responseJSON);
		customizeChartForSalesReportByCategory();
		formatChartData(data, 1, 2);
		drawChartForSalesReportByCategory(period);
		setSalesAmount(period, '_category', "Tổng sản phẩm");
	});
}

function prepareChartDataForSalesReportByCategory(responseJSON) {
	data = new google.visualization.DataTable();
	data.addColumn('string', 'Category');
	data.addColumn('number', 'Doanh thu');
	data.addColumn('number', 'Lãi ròng');
	
	totalGrossSales = 0.0;
	totalNetSales = 0.0;
	totalItems = 0;
	
	$.each(responseJSON, function(index, reportItem) {
		data.addRows([[reportItem.identifier, reportItem.grossSales, reportItem.netSales]]);
		totalGrossSales += parseFloat(reportItem.grossSales);
		totalNetSales += parseFloat(reportItem.netSales);
		totalItems += parseInt(reportItem.productsCount);
	});
}

function customizeChartForSalesReportByCategory() {
	chartOptions = {
		height: 400,
		colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'],
		is3D: true, legend: { position: 'right' }
	};
}

function drawChartForSalesReportByCategory() {
	var salesChart = new google.visualization.PieChart(document.getElementById('chart_sales_by_category'));
	salesChart.draw(data, chartOptions);
}