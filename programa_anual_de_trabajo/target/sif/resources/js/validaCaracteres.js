/**
 * 
 *  Funciones que validan los caracteres capturados en los campos
 * 
 */
 		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Nombre
		 * - Apellido  Paterno
		 * - Apellido  Materno
		*/
		function verificarNombreApellidos(e) {	
			 
		   if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
		   if(key == 225 || key == 233 || key == 237 || key == 243 ||key == 250) //vocales minúscula acentuadas
              return true;
           if(key == 193 || key == 201 || key == 205 || key == 211 || key == 218) //vocales mayúsculas acentuadas
              return true;
           if(key == 209 || key == 241 ) // Ñ y ñ
               return true; 
           if(key == 32 ) //espacio en blanco
              return true;
           if(key == 39 || key == 46) // . '
               return true;
           if(key > 64 && key < 91) //todas las letras mayúsculas
              return true;
           if (key > 96 && key < 123)// todas las letras minúsculas
              return true;
           return false;
         }		
		
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Cargo
		*/
		function verificarCargo(e) {	

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
		   if(key == 225 || key == 233 || key == 237 || key == 243 ||key == 250) //vocales minúscula acentuadas
              return true;
           if(key == 193 || key == 201 || key == 205 || key == 211 || key == 218) //vocales mayúsculas acentuadas
              return true; 
           if(key == 209 || key == 241 ) // Ñ y ñ
               return true;            
           if(key == 32 ) //espacio en blanco
               return true;
           if(key == 39 || key == 46) // . '
              return true;
           if(key > 64 && key < 91) //todas las letras mayúsculas
              return true;
           if (key > 96 && key < 123)// todas las letras minúsculas
              return true;
           return false;
         }				
		
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Domicilio
		*/
		function verificarDomicilio(e) {	 

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
		   if(key == 225 || key == 233 || key == 237 || key == 243 ||key == 250) //vocales minúscula acentuadas
              return true;
           if(key == 193 || key == 201 || key == 205 || key == 211 || key == 218) //vocales mayúsculas acentuadas
              return true; 
           if(key == 209 || key == 241  ) // Ñ y ñ
               return true;            
           if(key == 32 ) //espacio en blanco
               return true;
           if(key == 08 || key == 13 || key == 35 || key == 39 || key == 44 || key == 45 || key == 46 || key == 47 || key == 58 || key == 59 || key == 146) // \r \n # ' , - . / : ; ´
              return true;                                                                              																			   
           if(key > 64 && key < 91) //todas las letras mayúsculas
              return true;
           if (key > 96 && key < 123)// todas las letras minúsculas
              return true;
           return false;
         }
	
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Plazos legales
		*/
		function verificarPlazosLegales(e) {	 

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
           return false;
         }			
		
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Paquetes extemporaneos 
		*/
		function verificarPaquetesExtemporaneos(e) {	 

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
           return false;
         }			
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Hora 
		*/
		function verificarHora(e) {	 

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
           return false;
         }			
		
		
		/*Función para validar que el usuario teclee solo caracteres válidos en:
		 * - Minutos 
		*/
		function verificarMinutos(e) {	 

			if (navigator.appName == 'Microsoft Internet Explorer')
		      key = window.event.keyCode;
		   else
			  key = e.which;
		   if(key > 47 && key < 58) //todos los números del 0 al 9
	    	   return true;
           return false;
         }		
			  
		
