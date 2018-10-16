
var nodeInterpreter = window.location.origin + "/sif_ordinario/app/fasp-web/NodeConnector";

function restartAllData() {
	this.seleccionados = [];
}

var transaccion = true;

function uploadFile() {
	var cargador = this;
	
	if (!cargador.puedeSubir)
		return;
	
	cargador.puedeSubir = false;
	
	jQuery.ajax({
		type : 'POST',
		url : nodeInterpreter,
		data : {
			upload : "/"
		}
	}).done(function(data) {
		var isError = false;
		var descError = '';
		
		$.each($.parseJSON(data), function(key, value) {
			if ('error' == key) {
				isError = true;
				descError = value;
			}
		});
		
		if (!isError) {
			//eval(cargador.onstart);
			
			if (cargador.asperaWeb == null)
				initConnect(data, cargador);
			else
				cargador.startUploadFile(data);
		} 
		else
			cargador.failure(descError);

	}).fail(function(jqXHR, textStatus) {
		cargador.failure(textStatus);
	});
}

var handleUploadCallback = function() {
	var cargador = this;
	var lasTransferencias = [], primeraVez = true;
	var transferencias_todas = [];
	var fallos = 0;
	
	// random is a random number used for creating multiple instances of
	// downloads/uploads
	// Progress bar handler
	var handleTransferEvents = function(event, returnObj) {
		var termino = cargador.transferenciasIniciadas == cargador.transferenciasEsperadas;
		var fallido = null;
		
		for (var i = 0; i < returnObj.transfers.length; i++)
		{
			var obj = returnObj.transfers[i];
			
			var uready = false;
			var seleccionado = null;
			
			var j;
			for (j = 0; j < cargador.seleccionados.length; j++)
			{
				var remp = cargador.seleccionados[j].nombreCorto;
				var id_n = obj.current_file.lastIndexOf("/");
				var nombreCorto = obj.current_file.substr(id_n + 1);
				
				if (remp == nombreCorto)
				{
					seleccionado = cargador.seleccionados[j];
					break;
				}
			}

			if (!obj) {
				obj = {};
				uready = false;
			}
			
			if (seleccionado == null)
				seleccionado = {};
			
			if (obj.status == 'failed') {
				fallos++;
				
				jQuery("#upload-path").val(
						'La transferencia fall\u00F3: ' + obj.error_desc + ' ('
								+ obj.error_code + ')');
				jQuery("#upload-path").attr("style",
						"background-color: #f2dede;");
				failure(obj.error_desc + ' (' + obj.error_code + ')');
				uready = false;
				
				if (fallos == 3)
				{
					fallido = {nombreCorto: seleccionado.nombreCorto, 
							   status : obj.status};
					cargador.removeAllTransfers();
					termino = true;
				}
				break;
			}

			// Received completed, make background green and print complete
			// message. Operation is done.
			else if (obj.status == 'completed') {
				jQuery("#upload-path").val("Transferencia completa");
				jQuery("#upload-path").attr("style",
						"background-color: #dff0d8;");
				replication(obj.title, obj.transfer_spec.destination_root);			
				
				uready = false;
				
				if (transferencias_todas[obj.uuid] == undefined)
				{
					cargador.transferenciasIniciadas++;
					transferencias_todas[obj.uuid] = true;
					cargador.asperaWeb.removeTransfer(obj.uuid);
					
					var _ind = cargador.ids_transferencias.lastIndexOf(obj.uuid);
					cargador.ids_transferencias.splice(_ind, 1);
				}
			}

			// Received cancelled
			else if (obj.status == 'cancelled') {
				jQuery("#upload-path").val("Transferencia cancelada");
				jQuery("#upload-path").attr("style", "background-color: #F3F781;");
				
				fallido = {nombreCorto: seleccionado.nombreCorto,
						   status : obj.status};
				cargador.removeAllTransfers();
				termino = true;
				break;
			}

			// Transfer in progress, use gradient effect to show background
			// moving (like loading bar). Need to include gradient for all
			// browsers.
			else {
				termino = false;
				
				switch (event) {
					case 'transfer':
						//var div = seleccionado.renglon.find('.barra_progreso');
						//var span = seleccionado.renglon.find('.barra_progreso span');
						
						//span.css("width", (obj.percentage * div.width()) + 'px');
						
						break;
				}
			}
		}
		
		if (termino) {
			cargador.examinar.enable();
			cargador.restartAllData();
			cargador.asperaWeb.removeEventListener('transfer');
			
			cargador.terminar(fallido);
		}
	};

	cargador.asperaWeb.addEventListener('transfer', handleTransferEvents);

	// Block Connect Dialog from appearing, since we are showing progress on web
	// app
	connectSettings = {
		"allow_dialogs" : "no"
	};
}

function initConnect(dataServer, cargador) {
	cargador.id = Math.floor((Math.random() * 10000) + 1);
	
	var CONNECT_INSTALLER = '//d3gcli72yxqn2z.cloudfront.net/connect/v4';
	if (cargador.asperaWeb == null) {
		cargador.asperaWeb = new AW4.Connect({
			sdkLocation : CONNECT_INSTALLER,
			minVersion : '3.6.0',
			id : "aspera_web_transfers-" + cargador.id
		});
	}
	
	var asperaInstaller = new AW4.ConnectInstaller({
		sdkLocation : CONNECT_INSTALLER
	});
	
	var statusEventListener = function(eventType, data) {
		var status = AW4.Connect.STATUS;
		if (eventType === AW4.Connect.EVENT.STATUS) {
			if (data === status.INITIALIZING) {
				cargador.examinar.disable();
				asperaInstaller.showLaunching();
				//$("#aspera-iframe-container").css('position', 'fixed');
			}
			if (data === status.FAILED) {				
				cargador.examinar.disable();
				asperaInstaller.showDownload();
				//$("#aspera-iframe-container").css('position', 'fixed');
			}
			if (data === status.OUTDATED) {
				cargador.examinar.disable();
				asperaInstaller.showUpdate();
				//$("#aspera-iframe-container").css('position', 'fixed');
			}
			if (data === status.RUNNING) {
				if (cargador.hayNube && !cargador.disabled)
					cargador.examinar.enable();
					
				asperaInstaller.connected();
				
				if (dataServer != null)
					cargador.startUploadFile(dataServer);
			}
		}
	};
	
	cargador.asperaWeb.addEventListener(AW4.Connect.EVENT.STATUS, statusEventListener);
	cargador.asperaWeb.initSession('nodeConnect-' + cargador.id);
}


function startUploadFile(data) {
	var tsa = JSON.parse(data);
	var archivos = tsa.archivos;
	var transferSpecArrays = tsa.node;
	cargador = this;
	
	this.ids_transferencias = [];
	var transfers_ind = 0;
	
	connectSettings = {
		"allow_dialogs" : "yes"
	};
	
	cargador.transferenciasEsperadas = transferSpecArrays.transfer_specs.length;
	cargador.transferenciasIniciadas = 0;
	cargador.handleUploadCallback();
	
	var transferSpec = transferSpecArrays.transfer_specs[0].transfer_spec;
	this.seleccionados = archivos;
	
	for (var i = 0; i < tsa.archivos.length; i++)
	{
		var archivo = archivos[i];
		
		transferSpec.paths[i] = {
			source : archivo.name.replace(/\//g, '\\'),
			destination : "/" + archivo.relativePath
		};
	}
	
	transferSpec.remote_password = this._c_;
	transferSpec.target_rate_kbps = 60000;
	transferSpec.cipher = "none";
	//transferSpec.rate_policy = "fixed";
	transferSpec.min_rate_kbps = 2000;
	transferSpec.resume = "attributes";
	transferSpec.create_dir = true;
	
	this.asperaWeb.startTransfer
	(
		transferSpec,
		connectSettings, 
		{
			success: function (data)
			{
				window.onbeforeunload = function()
				{
					var fallido = {nombreCorto: "Se interrumpió el proceso", 
							   	   status : "cancel"};
					cargador.terminar(fallido);
				}
				
				cargador.ids_transferencias[transfers_ind++] = data.transfer_specs[0].uuid;
			}
		}
	);
}


function replication(file, path)
{
    jQuery.ajax({
        type: 'POST',
        url: nodeInterpreter,
        data: {replicateFile: file, replicatePath: path}
    })
            .done(function (data) {

            })
            .fail(function (jqXHR, textStatus) {
                failure(textStatus);
            });
}

function ventana() {
	var cargador = this;
	var tipoL = atob(cargador.ts).toLowerCase();
	var tipoU = tipoL.toUpperCase();
	var arrMin = $.parseJSON("[\"" + tipoL.split(',').join("\",\"") + "\"]")
	var arrMay = $.parseJSON("[\"" + tipoU.split(',').join("\",\"") + "\"]")
	var arr = arrMin.concat(arrMay);
	
	options = {
		allowMultipleSelection : true,
	    allowedFileTypes : [{filter_name : "Archivos permitidos", extensions : arr}],
	    title : "Seleccione un archivo"
	};
	
	// Show Select File dialog box and loop through each file to add it to path
	// array.
	this.asperaWeb.showSelectFileDialog({
		success : function(pathArray) {
			cargador.puedeSubir = true;
			
			var fileArray = pathArray.dataTransfer.files;
			
			if (fileArray.length > 0)
			{
				eval(cargador.onstart);
				
				var res = [];
				
				for (var i = 0; i < fileArray.length; i++) {
					var ind1 = fileArray[i].name.lastIndexOf('\\');
					if (ind1 == -1)
						ind1 = fileArray[i].name.lastIndexOf('/');
					
					var name = fileArray[i].name.substr(ind1 + 1);
					
					var ind2 = name.lastIndexOf(".");
					var extension = name.substr(ind2 + 1).toLowerCase();
					
					if (arr.lastIndexOf(extension) == -1)
					{
						cargador.agregarMensaje('Error:', 'El archivo "' + name + '" no está permitido.', 'error');
						continue;
					}
					
					var esta = false;
					for (var j = 0; j < cargador.seleccionados.length; j++)
					{
						var n1 = cargador.seleccionados[j].nombreCorto;

						if (n1 == name)
						{
							esta = true;
							break;
						}
					}
					
					if (esta)
					{
						cargador.agregarMensaje('Advertencia:', 'El archivo ya existe "' + name + '"', 'adv');
						continue;
					}
					
					fileArray[i].nombreCorto = name;
					fileArray[i].extension = extension;
					res.push(fileArray[i]);
				}
				
				cargador.comprobarArchivo(res);
			}
		}
	}, options);
}

function failure(message) {
	console.log("Falló el servicio de la nube...");
}