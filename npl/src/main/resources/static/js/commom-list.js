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
				success: function(data) {
					var message = "Đã xóa thành công " + entityName;
					Swal.fire('Thông báo!', message, 'success').then(() => {
						window.location.href = moduleURL;
					});
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
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(data),
		contentType: "application/json",
		beforeSend: function(xhr) {
			// Lấy token CSRF từ trang và bao gồm nó trong yêu cầu
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			xhr.setRequestHeader(header, token);
		},
		success: function(response) {
			if(response){
				if(response.retCode && response.retCode == 'OK') {
					if(response.keyValue && response.keyValue!=''){
						Swal.fire(response.retStr + '\n NO ' + response.keyValue, 'success')
						.then((result) => {
							if (result.isConfirmed) {
								window.location.reload();
							}
						});;
					} else {
						Swal.fire('Thông báo!', response.retStr, 'success').then((result) => {
							if (result.isConfirmed) {
								window.location.reload();
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

