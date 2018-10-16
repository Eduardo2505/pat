function configurarTamanio() {
	loadChunks();
	cambiarVolumen();
	var width = PF('mediaDialog').jq.width();
	//inner_width = width * 0.9;
	
	var wd = PF('volumen').jq.parent().parent().width(); 
	PF('volumen').jq.css('left', /*Math.trunc((wd - inner_width) / 2) + */ '0px');	
	//$('div.barra_desp').width(inner_width);
	
	if (lienzo != null) {		
		//var height = PF('mediaDialog').jq.height();
		
		//lienzo.width = inner_width;
		//lienzo.height = height * 0.9;
	}
}

function clear() {
	if (vId != null)
		clearInterval(vId);
}

function crearVideo(xhr, status, args)
{
	v = document.createElement(args.tipo);
	tipoMedia = args.tipo;
	
	vId = null;
	
	var src = document.createElement("source");
	var req = window.location.origin + "/sif_ordinario/app/video?f=" + Math.random();
		
	src.setAttribute("type", args.mediaMime);
	src.setAttribute("src", req);
	
	v.appendChild(src);
	
	v.onloadstart = function ()
	{
		console.log("Tiempo actual: " + (new Date()).getTime());
	}
	
	$('#headerMedia').html(args.headerMedia);
	
	$('#mediaView').empty();
	
	if (tipoMedia == 'video') {
		$('#mediaView').append('<canvas id="lienzo" class="lienzo-class"/>');
		
		lienzo = document.getElementById('lienzo');
		c = lienzo.getContext('2d');
		PF('mediaDialog').jq.height(385);
	}
	else {
		$('#mediaView').append('<span id="bocina" class="ui-icon ui-icon-volume-up" style="font-size: 3em;"/>');
		
		PF('mediaDialog').jq.height(180);
		lienzo = null;
	}
	
	PF('mediaDialog').jq.width(400);
	
	v.addEventListener
	(
		'play',
		function ()
		{
			vId = setInterval(draw, 42);
			//$('div.barra_desp').width(inner_width);
		},
		false
	);
	
	function draw()
	{
		if (tipoMedia == 'video')
			c.drawImage(v, 0, 0, lienzo.width, lienzo.height);
		
		var _width = $('div.barra_desp').width();
		var dif = v.currentTime / v.duration * _width;
		$('div.barra_desp span.posicion').width(dif);
		
		$('#reloj').html(clockFormat(v.currentTime));
	}

	setControls();
	
	PF('mediaDialog').jq.on
	(
		"resize",
		configurarTamanio
	)
}

function cerrarVideo() {
	clear();
	v.pause();
	
	$.post(window.location.origin + "/sif_campania/app/cerrarVideo?f=" + Math.random());
}

function loadChunks() 
{
	$('div.barra_desp').empty();
	var _width = $('div.barra_desp').width();
	var dif = v.currentTime / v.duration * _width;
	
	for (var i = 0; i < v.buffered.length; i++)
	{
		var chunk = $('<span class="cargado"></span>');
		var start = v.buffered.start(i);
		var end = v.buffered.end(i);
		var x_nuev = start / v.duration * _width + $('div.barra_desp').position().left;
		var wd_nuev = (end - start) / v.duration * _width;
		
		chunk.css('left', x_nuev + 'px');
		chunk.css('width', wd_nuev + 'px');
		$('div.barra_desp').append(chunk);
	}
	
	var posicion = $('<span class="posicion"></span>');
	posicion.width(dif);
	$('div.barra_desp').append(posicion);
	
	$('#duracion').html(clockFormat(v.duration));
};

function setControls() {
	var play = document.getElementById('play');
	play.addEventListener
	(
		'click',
		function () 
		{
			if (!v.paused)
				return;

			if (v.ended)
				v.currentTime = 0;

			v.play();
		}
	);

	var pause = document.getElementById('pause');
	pause.addEventListener
	(
		'click',
		function () 
		{
			if (v.paused)
				return;

			v.pause();
		}
	);

	var stop = document.getElementById('stop');
	stop.addEventListener ('click', function () {v.currentTime = 0; v.pause();});

	var atrasar = document.getElementById('atrasar');
	atrasar.addEventListener 
	(
		'click', 
		function () 
		{
			v.currentTime = v.currentTime >= 3 ? v.currentTime - 3 : 0;
		}
	);

	var adelantar = document.getElementById('adelantar');
	adelantar.addEventListener 
	(
		'click', 
		function () 
		{
			if (!(v.currentTime + 3 >= v.duration))
				v.currentTime += 3;
			else
				v.currentTime = v.duration;
		}
	);
	
	v.onprogress = loadChunks;
	v.onplaying = loadChunks;
	
	$('div.barra_desp').on 
	(
		'click',
		function (event) {
			var left = $('div.barra_desp').offset().left;
			var width = $('div.barra_desp').width();
			var dif = event.pageX - left;
			var time = dif / width * v.duration;
			
			v.currentTime = time;
		}
	);	
}

function clockFormat(num) {
	if (isNaN(num))
		return '00:00';
	
	var minutos = Math.trunc(num / 60);
	var residuo = Math.trunc(num) % 60; 
	return (minutos + '').padStart(2, '0') + ':' + (residuo + '').padStart(2, '0');
}

function cambiarVolumen() {
	var vol = $('[id$="idVolumen"]').val() - 0;
	v.volume = vol / 100;
}