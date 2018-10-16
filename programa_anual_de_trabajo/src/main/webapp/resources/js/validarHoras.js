function validarHoraInicio(){
			var divErrorInicio = document.getElementById("msgErrorHoraInicio");
			var msgError = "Hora inválida.";
			var horaInicio = document.getElementById("formCapturaEventos:horaInicioEvento");
			var horaFin = document.getElementById("formCapturaEventos:horaFinEvento");
			var nombreEvento = document.getElementById("formCapturaEventos:nombreEvento");
			var descripcion = document.getElementById("formCapturaEventos:descripcion");
			var nombreResponsable = document.getElementById("formCapturaEventos:nombreResponsable");
			var primerApellido = document.getElementById("formCapturaEventos:apellidoPaternoResponsable");
			var segundoApellido = document.getElementById("formCapturaEventos:apellidoMaternoResponsable");
			var calle = document.getElementById("formCapturaEventos:calle");
			var noInterior = document.getElementById("formCapturaEventos:noInterior");
			var noExterior = document.getElementById("formCapturaEventos:noExterior");
			var colonia = document.getElementById("formCapturaEventos:colonia");
			var cp = document.getElementById("formCapturaEventos:cp");
			var referencia = document.getElementById("formCapturaEventos:referencia");
			var lugarExacto = document.getElementById("formCapturaEventos:lugarExactoEvento");
			

			var botonAceptar = document.getElementById("formCapturaEventos:guardaEvento");
			var botonAceptarModifica = document.getElementById("formCapturaEventos:guardaEventoModifica");

			var patronInicio=/^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])?$/;
			if(patronInicio.test(horaInicio.value)) {
				divErrorInicio.innerHTML = "";
				horaFin.disabled = false;
				PF('selectEvento').enable();
				PF('selectTipoEvento').enable();
				nombreEvento.disabled = false;
				descripcion.disabled = false;
				nombreResponsable.disabled = false;
				primerApellido.disabled = false;
				segundoApellido.disabled = false;
				PF('selectEntidad').enable();
				calle.disabled = false;
				noInterior.disabled = false;
				noExterior.disabled = false;
				colonia.disabled = false;
				cp.disabled = false;
				referencia.disabled = false;
				lugarExacto.disabled = false;
				PF('selectEstatus').enable();
				botonAceptar.disabled = false;
				botonAceptarModifica.disabled = false;
			}
			else {
				divErrorInicio.innerHTML = msgError;
				horaFin.disabled = true;
				PF('selectEvento').disable();
				PF('selectTipoEvento').disable();
				nombreEvento.disabled = true;
				descripcion.disabled = true;
				nombreResponsable.disabled = true;
				primerApellido.disabled = true;
				segundoApellido.disabled = true;
				PF('selectEntidad').disable();
				calle.disabled = true;
				noInterior.disabled = true;
				noExterior.disabled = true;
				colonia.disabled = true;
				cp.disabled = true;
				referencia.disabled = true;
				lugarExacto.disabled = true;
				PF('selectEstatus').disable();
				botonAceptar.disabled = true;
				botonAceptarModifica.disabled = true;
			}
		}

		function validarHoraFin(){
		    var divErrorFin = document.getElementById("msgErrorHoraFin");
		    var divErrorFechas = document.getElementById("msgErrorFechasInvalidas");
			var msgError = "Hora inválida.";
			var msgErrorComp = "La hora fin no debe ser menor o igual que la hora inicio.";
			var horaInicio = document.getElementById("formCapturaEventos:horaInicioEvento");
			var horaFin = document.getElementById("formCapturaEventos:horaFinEvento");
			var nombreEvento = document.getElementById("formCapturaEventos:nombreEvento");
			var descripcion = document.getElementById("formCapturaEventos:descripcion");
			var nombreResponsable = document.getElementById("formCapturaEventos:nombreResponsable");
			var primerApellido = document.getElementById("formCapturaEventos:apellidoPaternoResponsable");
			var segundoApellido = document.getElementById("formCapturaEventos:apellidoMaternoResponsable");
			var calle = document.getElementById("formCapturaEventos:calle");
			var noInterior = document.getElementById("formCapturaEventos:noInterior");
			var noExterior = document.getElementById("formCapturaEventos:noExterior");
			var colonia = document.getElementById("formCapturaEventos:colonia");
			var cp = document.getElementById("formCapturaEventos:cp");
			var referencia = document.getElementById("formCapturaEventos:referencia");
			var lugarExacto = document.getElementById("formCapturaEventos:lugarExactoEvento");
	
			var botonAceptar = document.getElementById("formCapturaEventos:guardaEvento");
			var botonAceptarModifica = document.getElementById("formCapturaEventos:guardaEventoModifica");

			var patronFin=/^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])?$/;
			if(patronFin.test(horaFin.value)) {
				var inicio = horaInicio.value;
				var fin = horaFin.value;

				if (inicio == fin) {
					divErrorFechas.style.border="1px solid red";
					divErrorFechas.style.color="red";
					divErrorFechas.style.backgroundColor=" #FFE4E1"
					divErrorFechas.innerHTML = msgErrorComp;
					divErrorFin.innerHTML = "";
					PF('selectEvento').disable();
					PF('selectTipoEvento').disable();
					nombreEvento.disabled = true;
					descripcion.disabled = true;
					nombreResponsable.disabled = true;
					primerApellido.disabled = true;
					segundoApellido.disabled = true;
					PF('selectEntidad').disable();
					calle.disabled = true;
					noInterior.disabled = true;
					noExterior.disabled = true;
					colonia.disabled = true;
					cp.disabled = true;
					referencia.disabled = true;
					lugarExacto.disabled = true;
					PF('selectEstatus').disable();
					botonAceptar.disabled = true;
					botonAceptarModifica.disabled = true;
				}

				if (inicio > fin) {
					divErrorFechas.style.border="1px solid red";
					divErrorFechas.style.color="red";
					divErrorFechas.style.backgroundColor=" #FFE4E1"
					divErrorFechas.innerHTML = msgErrorComp;
					divErrorFin.innerHTML = "";
					PF('selectEvento').disable();
					PF('selectTipoEvento').disable();
					nombreEvento.disabled = true;
					descripcion.disabled = true;
					nombreResponsable.disabled = true;
					primerApellido.disabled = true;
					segundoApellido.disabled = true;
					PF('selectEntidad').disable();
					calle.disabled = true;
					noInterior.disabled = true;
					noExterior.disabled = true;
					colonia.disabled = true;
					cp.disabled = true;
					referencia.disabled = true;
					lugarExacto.disabled = true;
					PF('selectEstatus').disable();
					botonAceptar.disabled = true;
					botonAceptarModifica.disabled = true;
				  }
				if(fin > inicio)

				 {
					divErrorFin.innerHTML = "";
					divErrorFechas.style.border="";
					divErrorFechas.style.color="";
					divErrorFechas.style.backgroundColor=""
					divErrorFechas.innerHTML = "";
					PF('selectEvento').enable();
					PF('selectTipoEvento').enable();
					nombreEvento.disabled = false;
					descripcion.disabled = false;
					nombreResponsable.disabled = false;
					primerApellido.disabled = false;
					segundoApellido.disabled = false;
					PF('selectEntidad').enable();
					calle.disabled = false;
					noInterior.disabled = false;
					noExterior.disabled = false;
					colonia.disabled = false;
					cp.disabled = false;
					referencia.disabled = false;
					lugarExacto.disabled = false;
					PF('selectEstatus').enable();
					botonAceptar.disabled = false;
					botonAceptarModifica.disabled = false;
				 }
			}
			else {
				divErrorFechas.style.border="";
				divErrorFechas.style.color="";
				divErrorFechas.style.backgroundColor=""
				divErrorFechas.innerHTML = "";
				divErrorFin.innerHTML = msgError;
				PF('selectEvento').disable();
				PF('selectTipoEvento').disable();
				nombreEvento.disabled = true;
				descripcion.disabled = true;
				nombreResponsable.disabled = true;
				primerApellido.disabled = true;
				segundoApellido.disabled = true;
				PF('selectEntidad').disable();
				calle.disabled = true;
				noInterior.disabled = true;
				noExterior.disabled = true;
				colonia.disabled = true;
				cp.disabled = true;
				referencia.disabled = true;
				lugarExacto.disabled = true;
				PF('selectEstatus').disable();
				botonAceptar.disabled = true;
				botonAceptarModifica.disabled = true;
			}

		}
		
		function limpiarMsgs(){
			var msgError = "Hora inválida.";
			var msgErrorComp = "La hora fin no debe ser menor o igual que la hora inicio.";
			var divErrorInicio = document.getElementById("msgErrorHoraInicio");
			var divErrorFin = document.getElementById("msgErrorHoraFin");
		    var divErrorFechas = document.getElementById("msgErrorFechasInvalidas");
		    
		    divErrorInicio.innerHTML = "";
		    divErrorFin.innerHTML = "";
		    
		    divErrorFin.innerHTML = "";
			divErrorFechas.style.border="";
			divErrorFechas.style.color="";
			divErrorFechas.style.backgroundColor=""
			divErrorFechas.innerHTML = "";			
		}
		
		