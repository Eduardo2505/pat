function comprobarArchivo(archivo, comprobador, evento)
{
	var BUFFER_SIZE = 1024 * 1024 * 5, offset = 0;
	var arch;
	var fr = new FileReader();
	evento.termino = false;
	
	function leyo()
	{
		var req = window.location.origin + "/sif_ordinario/app/parse?f=" + Math.random();		
		
		$.ajax
		(
			{
				url: req,
				method: 'post',
				data: new Blob([fr.result]),
				processData: false,
				contentType: 'application/octet-stream',
				dataType: "json"
			}
		).always
		(
			function (data)
			{
				evento.termino = data.termino;
				
				var j = {};
				j.loaded = data.posicion;
				j.total = arch.size;
				j.files = [arch];
				
				evento.contexto.ucfg.progress(null, j);
				
				if (!evento.termino)
				{
					if (data.resetStream)
						offset = data.posicion;
					
					prepara_lectura();					
				}
				else {
					console.log('Resultado: ' + data.cumple);
					evento.contexto.removeFile(archivo);
				}
			}
		);
	}
	
	fr.onload = leyo;
	
	function prepara_lectura()
	{
		var trozo;
		
		if (offset < arch.size)
		{
			trozo = arch.slice(offset, offset + BUFFER_SIZE);
			offset += BUFFER_SIZE;
			fr.readAsArrayBuffer(trozo);
		}
		else
		{
			var partes = ['\0'];
			trozo = new Blob(partes, {type: 'text/plain'});
			
			setTimeout
			(
				function ()
				{
					fr.readAsArrayBuffer(trozo)
				},
				500
			);
		}
		
	}
	
	offset = 0;
	arch = archivo;
	var _req = window.location.origin + "/sif_ordinario/app/establecer?f=" + Math.random();
	
	goodnight = evento;
	
	$.ajax
	(
		{
			url: _req,
			method: 'post',
			dataType: "json",
			data: {nombreArchivo: arch.name,
				   contentType: arch.type,
				   size: arch.size, 
				   tamTram: BUFFER_SIZE, 
				   completar : evento.contexto.cfg.completar,
				   mt: arch.lastModified}
		}
	).always
	(
		function (data)
		{
			if (!data.g)
			{
				if (data.p)
					offset = data.p; 
				
				setTimeout(comprobador, 0);
				prepara_lectura();
			}
			else
				console.log("Falló");
		}
	);
}