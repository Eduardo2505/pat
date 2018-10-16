//Validaciones con JavaScript	

//Función que sólo deja teclear números
	function esNumero(e) {
		k = (document.all) ? e.keyCode : e.which;
		if (k==8 || k==0) 
			return true;
		patron = /\d/;
		return patron.test(String.fromCharCode(k));
	}
	
//Función que sólo deja teclear letras
	function esLetra(e) {
		k = (document.all) ? e.keyCode : e.which;
		// 8 = backspace, 0 = delete, 209 = ñ, 241 = Ñ 
		if (k==8 || k==0 || k==209 || k==241 ) 
			return true;
		//letras minusculas
		else if (k>64 && k<91)
			return true;
		//letras mayusculas
		else if (k>96 && k<123)
			return true
		else
			return false;
	}

	//Función que sólo deja teclear letras y espacios
	function esLetraSpace(e) {
		k = (document.all) ? e.keyCode : e.which;
		// 8 = backspace, 0 = delete, 209 = ñ, 241 = Ñ 
		if (k==8 || k==0 || k==32 || k==209 || k==241 ) 
			return true;
		//letras minusculas
		else if (k>64 && k<91)
			return true;
		//letras mayusculas
		else if (k>96 && k<123)
			return true
		else
			return false;
	}
	
	//Función que sólo deja teclear letras y numeros
	function esAlfanumerico(e) {
		charCode = (document.all) ? e.keyCode : e.which;
		// 8 = backspace, 0 = delete, 38 = &
		if (charCode==8 || charCode==0 ) 
			return true;
		if (charCode==95) 
			return false;
		patron = /\w/;
		return patron.test(String.fromCharCode(charCode));
	}
	
	//Función que sólo deja teclear letras, números y caracteres especiales para el RFC y CURP
	function esAlfanumericoRFC_CURP(e) {
		charCode = (document.all) ? e.keyCode : e.which;
		// 8 = backspace, 0 = delete, 38 = &
		if (charCode==8 || charCode==0 || charCode==38) 
			return true;
		if (charCode==95) 
			return false;
		patron = /\w/;
		return patron.test(String.fromCharCode(charCode));
	}