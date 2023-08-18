// Sales Report by Product
var data;
var chartOptions;

$(document).ready(function() {
	setupButtonEventHandlers("_product", loadSalesReportByDateForProduct);	
});

function loadSalesReportByDateForProduct(period) {
	if (period == "custom") {
		startDate = $("#startDate_product").val();
		endDate = $("#endDate_product").val();
		
		requestURL = contextPath + "reports/product/" + startDate + "/" + endDate;
	} else {
		requestURL = contextPath + "reports/product/" + period;		
	}
	
	$.get(requestURL, function(responseJSON) {
		prepareChartDataForSalesReportByProduct(responseJSON);
		customizeChartForSalesReportByProduct();
		formatChartData(data, 2, 3);
		drawChartForSalesReportByProduct(period);
		setSalesAmount(period, '_product', "Tổng số sản phẩm");
	});
}

function prepareChartDataForSalesReportByProduct(responseJSON) {
	data = new google.visualization.DataTable();
	data.addColumn('string', 'Tên sản phẩm');
	data.addColumn('number', 'Số lượng');
	data.addColumn('number', 'Tổng danh thu');
	data.addColumn('number', 'Lãi ròng');
	
	totalGrossSales = 0.0;
	totalNetSales = 0.0;
	totalItems = 0;
	
	$.each(responseJSON, function(index, reportItem) {
		data.addRows([[reportItem.identifier, reportItem.productsCount, reportItem.grossSales, reportItem.netSales]]);
		totalGrossSales += parseFloat(reportItem.grossSales);
		totalNetSales += parseFloat(reportItem.netSales);
		totalItems += parseInt(reportItem.productsCount);
	});
}

function customizeChartForSalesReportByProduct() {
	chartOptions = {
		width: '100%',
		showRowNumber: true,
		page: 'enable',
		sortColumn: 2,
		sortAscending: true
	};
}

function drawChartForSalesReportByProduct() {
	var salesChart = new google.visualization.Table(document.getElementById('chart_sales_by_product'));
	salesChart.draw(data, chartOptions);
}