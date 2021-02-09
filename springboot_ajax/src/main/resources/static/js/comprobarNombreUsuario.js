$("body").on('keyup', '#username', comprobarNombreUsuario);


function comprobarNombreUsuario() {

	var nombreUsuario = $('#username').val();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var datos = { "username" : nombreUsuario };

	$.ajax({
		url: "/profesor/disponibleUsername",
    	contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(datos),
		type: "POST",
		success: function(response) {

			var alerta;
			alert(response);
			if (response == false) {
				alerta =
					"<div class='form-group col-md-8 alert alert-success' role='alert'>" +
					"Este nombre de usuario está libre para su uso" +
					"</div>";
			} else {
				alerta =
					"<div class='form-group col-md-8 alert alert-danger' role='alert'>" +
					"Este nombre de usuario no está disponible" +
					"</div>";

			}
			$('#nombreUsuarioError').html(alerta);

		},
		error: function(xhr, status, error) {

			var alerta =
				"<div class='alert alert-danger' role='alert'>" +
				"Este nombre de usuario ya está ocupado" +
				"</div>";

			$('#nombreUsuarioError').html(alerta);
		}
	});

}