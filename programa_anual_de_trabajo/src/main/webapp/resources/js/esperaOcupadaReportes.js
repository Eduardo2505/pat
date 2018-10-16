function iniciaSondeoReporte(xhr, status, args, fDesbloquear) 
{
	if (args.iniciaSondeo) {
		segundos = new Date();
		tiempoTranscurrido = null;
		
	 	varSondeo = setInterval(sondea, 1000);
	 	
	 	desbloquear = fDesbloquear;
	}
	else
		fDesbloquear(args);
}

function sondeoReporte(xhr, status, args) {
	diferencia = ((new Date()) - segundos) / 1000;

	console.log(diferencia);
	
	if (!args.termino)
		return;
	
	clearInterval(varSondeo);
	desbloquear(args);
	
	if (!args.encontroResultados)
		return;
	
	document.getElementById('descarga:getFile').click();
}