function clearFilter(){
	window.location = moduleURL;
}
function showDeleteConfirm(link, entityName, moduleURL) {
	entityId = link.attr("entityId");
	Swal.fire({
		title: 'Bạn muốn xóa ' + entityName + '?',
		showDenyButton: true,
		showCancelButton: true,
		confirmButtonText: 'Xóa',
		denyButtonText: `Hủy bỏ`,
		cancelButtonText: `Hủy`,
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: link.attr("href"),
				method: 'GET',
				success: function(response) {
					if(response){
						if(response.retCode && response.retCode == 'OK') {
							Swal.fire('Thông báo!', response.retStr, 'success').then((result) => {
								if (result.isConfirmed) {
									afterSaveForm();
								}
							});;
						} else {
							if(response.retStr){
								Swal.fire({ icon: 'error', title: response.retStr });
							}	else {
								afterSaveForm();
							}
						}
					}
				},
				error: function() {
					Swal.fire('Lỗi', 'Đã xảy ra lỗi khi xóa ' + entityName, 'error');
				}
			});
		} else if (result.isDenied) {
			Swal.fire('Hủy bỏ', '', 'info');
		}
	});
}

function save(url, data){
	var ajaxOptions = {
		type: "POST",
		url: url,
		data: data ,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		},
		success: function(response) {
			if(response){
				if(response.retCode && response.retCode == 'OK') {
					if(response.keyValue && response.keyValue!=''){
						Swal.fire(response.retStr + '\n NO ' + response.keyValue, 'success')
						.then((result) => {
							if (result.isConfirmed) {
								afterSaveForm();
							}
						});;
					} else {
						Swal.fire('Thông báo!', response.retStr, 'success').then((result) => {
							if (result.isConfirmed) {
								afterSaveForm();
							}
						});;
					}	
				} else {
					if(response.retStr){
						Swal.fire({ icon: 'error', title: response.retStr });
					}	else {
						window.location.href = '/';
					}
				}
			}
			
		},
		error: function(xhr, status, error) {
			Swal.fire({icon: 'error', title: xhr.responseText});
		}
	};
	if (data instanceof FormData) {
		ajaxOptions.enctype = 'multipart/form-data';
		ajaxOptions.processData = false;
		ajaxOptions.contentType = false;
	} else {
		ajaxOptions.data = JSON.stringify(data);
		ajaxOptions.contentType = "application/json";
	}

	$.ajax(ajaxOptions);
}

function listCate(url, brandId){
	$.ajax({
		url: url,
		method: 'GET',
		data: { brandId: brandId },
		success: function(response) {
			if(response){
				var html ='';
				response.forEach(function(category) {
					html += '<option value="'+category.catId+'">'+category.name+'</option>'
				});
				$("#categoryId").append(html);
			}
		},
		error: function() {
			Swal.fire('Lỗi', 'Đã xảy ra lỗi ', 'error');
		}
	});
}
	
function addDetail(){
	const container = document.getElementById("divProductDetails");
	const productInput = document.createElement("div");
	productInput.classList.add("form-inline");
	
	productInput.innerHTML = `
		<label class="m-3">Thuộc tính:</label>
		<input type="text" class="form-control w-25" name="detailNames" maxlength="255" placeholder="vd: Ram"/>
		<label class="m-3">Dữ liệu:</label>
		<input type="text" class="form-control w-25" name="detailValues" maxlength="255" placeholder="vd: 8GB"/>
		<a class="btn btn-trigger btn-icon delete-product"  title="Xóa trường này"><em class="icon ni ni-cross"></em></a>
	`;
	
	container.appendChild(productInput);
	
	const deleteButton = productInput.querySelector(".delete-product");
	deleteButton.addEventListener("click", () => {
		productInput.remove();
	});
}

	$(function() {
		$("#today").click(function() {
			var today = new Date();
			$("#fromDate").datepicker("setDate", today);
			$("#toDate").datepicker("setDate", today);
			afterSaveForm();
		});
		$("#lastWeek").click(function() {
			var today = new Date();
			var lastWeekStart = new Date(today);
			lastWeekStart.setDate(today.getDate() - 7);

			while (lastWeekStart.getDay() !== 1) {
				lastWeekStart.setDate(lastWeekStart.getDate() - 1);
			}
			var lastWeekEnd = new Date(lastWeekStart);
			lastWeekEnd.setDate(lastWeekEnd.getDate() + 6);

			$("#fromDate").datepicker("setDate", lastWeekStart);
			$("#toDate").datepicker("setDate", lastWeekEnd);
			afterSaveForm();
		});
		$("#lastMonth").click(function() {
			var today = new Date();
			var lastMonthStart = new Date(today);
			lastMonthStart.setMonth(today.getMonth() - 1, 1);
			var lastMonthEnd = new Date(today);
			lastMonthEnd.setDate(0);

			$("#fromDate").datepicker("setDate", lastMonthStart);
			$("#toDate").datepicker("setDate", lastMonthEnd);
			afterSaveForm();
		});

		$("#last3Months").click(function() {
			var today = new Date();
			var last3MonthsStart = new Date(today);
			last3MonthsStart.setMonth(today.getMonth() - 3, 1);
			var last3MonthsEnd = new Date(today);
			last3MonthsEnd.setDate(0);

			$("#fromDate").datepicker("setDate", last3MonthsStart);
			$("#toDate").datepicker("setDate", last3MonthsEnd);
			afterSaveForm();
		});
		$("#lastYear").click(function() {
			var today = new Date();
			$("#fromDate").datepicker("setDate", new Date(today.getFullYear() - 1, 0, 1));
			$("#toDate").datepicker("setDate", new Date(today.getFullYear() - 1, 11, 31));
			afterSaveForm();
		});
		$("#All").click(function() {
			$("#fromDate").datepicker("setDate", '');
			$("#toDate").datepicker("setDate", '');
			afterSaveForm();
		});
	})

$(document).ready(function() {
	var messageModal = document.getElementById('messageAlert');
	if (messageModal) {
		var message = messageModal.innerText;
		Swal.fire(
			'Thông báo',
			message,
			'success'
		);
	}
});

