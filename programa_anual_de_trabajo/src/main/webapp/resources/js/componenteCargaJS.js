/**
 * 
 *
 */

function crearCargador(identificador, ts, _onstart, _oncomplete, _c_, almacenado, hayNube, disabled, strDatos) {
	var cargador;

	if (window[identificador] == undefined)
	{
		cargador =
		{
			ident : identificador,
			numRenglones : 0,
			identificador : 0,
			mensajeError: null,
			crearRenglon : function (achivo)
			{
				var progress = '<div class="barra_progreso">'
							 + '   <span class="avance" style="width: 1px;">&nbsp;</span>'
							 + '</div>';
				
				var quitar = $('<td class="ui-md-5" style="text-align: center;">'
					           + '    <div class="fa fa-times fa-2x cancelar"></div>'
					           + '</td>');
				
				var idSelector = identificador + this.identificador;
				
				var nombre = achivo.name.substring(achivo.name.lastIndexOf('\\') + 1);
				
				var reng = $('<tr id="' + idSelector + '">'
							 + '    <td class="ui-md-5"> ' + nombre + '</td>'
							 //+ '    <td class="columna">' + progress + '</td>'
							 + '</tr>');
				
				reng.append(quitar);
				
				var botonQuitar = quitar.find('div');
				botonQuitar.on
				(
					'click',
					function ()
					{
						cargador.quitarRenglon(reng);
					}
				);
				
				this.refTabla.append(reng);
				
				this.identificador++;
				this.numRenglones++;
				
				reng.nombre = achivo.name;
				
				return reng;
			},
			quitarRenglon : function (renglon)
			{
				cargador.numRenglones--;
				
				if (cargador.numRenglones == 0)
				{
					cargador.cargar.disable();
				}
	
				var i;
				for (i = 0; i < cargador.seleccionados.length; i++)
				{
					var _renglon = cargador.seleccionados[i].name;
					
					if (renglon.nombre == _renglon)
						break;
				}
				
				cargador.seleccionados.splice(i, 1);
				
				renglon.remove();
			},
			removeAllTransfers : function ()
			{
				for (var i = 0; i < this.ids_transferencias.length; i++)
					this.asperaWeb.removeTransfer(this.ids_transferencias[i]);
				
				this.asperaWeb.stop();
			},
			agregarMensaje : function (sumario, mensaje, tipo)
			{
				var _mensaje, pal;
				
				if (tipo == "error")
				{
					pal = "error";
					_mensaje = cargador.mensajeError;
				}
				else if (tipo == "adv")
				{
					pal = "warn";
					_mensaje = cargador.mensajeAdvertencia;
				}
				
				if (_mensaje == null)
				{
					var close = $('<span class="ui-messages-close"><span class="ui-icon ui-icon-close"></span></span>')
					
					_mensaje = $
					(
						'<div class="ui-messages-' + pal + ' ui-corner-all">' +
						'    <span class="ui-messages-' + pal + '-icon"></span>' + 
						'    <ul></ul>' + 
						'</div>'
					);
					
					_mensaje.append(close);
					cargador.mensajes.append(_mensaje);
					
					close.click
					(
						function ()
						{
							_mensaje.remove();
							
							if (_mensaje == cargador.mensajeError)
								cargador.mensajeError = null;
							else if (_mensaje == cargador.mensajeAdvertencia)
								cargador.mensajeAdvertencia = null;
						}
					);
				}
				
				_mensaje.children('ul').append
				(
					'<li>' + 
					'    <span class="ui-messages-' + pal + '-summary">' + sumario + '</span>' + 
					'    <span class="ui-messages-' + pal + '-detail">' + mensaje + '</span>' + 
					'</li>'
				);

				if (tipo == "error")
					cargador.mensajeError = _mensaje;
				else if (tipo == "adv")
					cargador.mensajeAdvertencia = _mensaje;
			},
			comprobarArchivo : comprobarArchivo,
			asperaWeb : null,
			uploadFile : uploadFile,
			restartAllData : restartAllData,
			handleUploadCallback : handleUploadCallback,
			startUploadFile : startUploadFile,
			ventana : ventana,
			subido : false,
			onstart : _onstart,
			oncomplete : _oncomplete,
			puedeSubir : false,
			terminar : terminar
		};
	}
	else
		cargador = window[identificador];
	
	cargador.refTabla = $("#" + identificador + "TabIntr");
	cargador.examinar = PF('exmId');
	cargador.cargar = PF('carId');
	cargador.mensajes = $("." + identificador + "Mensajes");
	cargador.ts = ts;
	cargador.seleccionados = [];
	cargador.cargar.disable();
	cargador._c_ = _c_;
	cargador.almacenado = almacenado;
	cargador.hayNube = hayNube;
	cargador.disabled = disabled;
	cargador.mensajeError = null;
	cargador.mensajeAdvertencia = null;
	cargador.datos = JSON.parse(strDatos);
	
	if (!hayNube || disabled || ts.length == 0)
		cargador.examinar.disable();
	else
		cargador.examinar.enable();
	
	if (cargador.asperaWeb == null)
		initConnect(null, cargador);
	
	cargador.examinar.jq.on
	(
		'click',
		function ()
		{
			if (cargador.subido)
			{
				cargador.asperaWeb = null;
				initConnect(null, cargador);
				cargador.subido = false;
			}
			
			cargador.ventana();
		}
	);
	
	cargador.cargar.jq.on
	(
		'click',
		function ()
		{
			var canc = $('#' + identificador + 'TabIntr .cancelar');
			canc.removeClass('cancelar').addClass('cancelar_disabled');
			canc.unbind('click');			
			
			cargador.examinar.disable();
			cargador.cargar.disable();
			
			var req = window.location.origin + "/sif_ordinario/app/prepararEvidencia?f=" + Math.random();
			
			$.ajax
			(
				{
					url : req,
					method : 'post',
					data : JSON.stringify(cargador.seleccionados),
					processData : false,
					contentType: 'application/octet-stream',
				}
			).done
			(
				function ()
				{
					var handler = window[identificador + "Command"];
					handler();
					cargador.examinar.enable();
				}
			);
		}
	);
	
	return cargador;
}

function comprobarArchivo(archivos)
{
	if (archivos.length == 0) {
		eval(this.oncomplete);
		return;
	}
	
	var BUFFER_SIZE = 20;
	var cargador = this;
	var archivo = archivos[0];
	
	var options =
	{
        path : archivo.name,
        offset : 0,
        chunkSize : archivo.size < BUFFER_SIZE ? archivo.size : BUFFER_SIZE
    };
	
	function leyo(read)
	{
		var req = window.location.origin + "/sif_ordinario/app/parse?f=" + Math.random();
		var dif = archivo.size - options.offset > BUFFER_SIZE;
		var u = atob(read.data);
		//options.offset += BUFFER_SIZE;
		
		var es = false;
		for (var tipo in cargador.datos)
		{			
			for (var i = 0; i < cargador.datos[tipo].length; i++)
			{				
				if (tipo == archivo.extension && u.includes(cargador.datos[tipo][i]))
				{
					es = true;
					break;
				}
			}
			
			if (es)
				break;
		}
		
		if (es)
		{
			cargador.crearRenglon(archivo);
			delete(archivo.extension)
			cargador.seleccionados.push(archivo);
			
			if (!cargador.disabled)
				cargador.cargar.enable();
		}
		else
		{
			cargador.agregarMensaje('Error:', 'El archivo "' + archivo.nombreCorto + '" no es válido.', 'error');
		}
		
		archivos.splice(0, 1);
		
		cargador.comprobarArchivo(archivos);
	}
	
	function prepara_lectura()
	{
		var trozo;
		
		if (options.offset < archivo.size)
		{
			var dif = archivo.size - options.offset > BUFFER_SIZE ? BUFFER_SIZE : archivo.size - options.offset;
			options.chunkSize = dif;
			
			cargador.asperaWeb.readChunkAsArrayBuffer
			(
				options, 
				{
                    success: leyo,
                    error: function (error)
                    {
                        console.log(error);
                    }
				}
			);
		}
		else
		{
			setTimeout
			(
				function ()
				{
					leyo({data : btoa(String.fromCharCode(0))});
				},
				500
			);
		}
		
	}
	
	prepara_lectura();
}

function terminar(fallido)
{
	var cargador = this;
	
	var req = window.location.origin + "/sif_ordinario/app/estatus";
	
	window.onbeforeunload = null;
	
	$.ajax
	(
		{
			url : req,
			method : 'post',
			data : JSON.stringify(fallido),
			processData : false,
			contentType: 'application/octet-stream'
		}
	).done
	(
		function ()
		{
			var almacenado = window[cargador.almacenado];
			almacenado();
			cargador.subido = true;
		}
	);
}