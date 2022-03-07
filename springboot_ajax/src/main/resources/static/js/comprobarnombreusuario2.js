
$("body").on('keyup', '#username', comprobarNombreUsuario);

function comprobarNombreUsuario() {

    var token = $("meta[name='_csrf']").attr("content");

    fetch('/profesor/disponibleUsername',
     {
         headers: { "Content-Type": "application/json; charset=utf-8",
         "X-CSRF-TOKEN": token },
         method: 'POST',
         credentials: 'include',
         body: JSON.stringify({ "username": $('#username').val() }), 
        }
    )
        .then(res => res.json())
        .then(response => {
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
        })
        .catch(function(messsageDeError) {
            var alerta =
				"<div class='alert alert-danger' role='alert'>" +
				"Error en el servidor" +
				"</div>";

			$('#nombreUsuarioError').html(alerta);
        })
}
