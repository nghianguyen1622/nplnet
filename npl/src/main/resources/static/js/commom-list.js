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
									removeTable();
								}
							});;
						} else {
							if(response.retStr){
								Swal.fire({ icon: 'error', title: response.retStr });
							}	else {
								removeTable();
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
								removeTable();
							}
						});;
					} else {
						Swal.fire('Thông báo!', response.retStr, 'success').then((result) => {
							if (result.isConfirmed) {
								removeTable();
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

