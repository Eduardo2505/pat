/*
 *@INE 2015 JAVASCRIPT CRYPTORAPHY LIBRARY
 */

// variables necesarias para el uso de la libreria forge JS.
var pki = forge.pki, asn1 = forge.asn1, caCerts = new Array();

// class Certificate.
// certificateFile: Archivo que contiene el certificado digital de un firmante.
// certificate: una instancia de un  objeto mapeado a uno de tipo certificado digital x.509.
function Certificate(certificateFile) {
	this.certificateFile = certificateFile;
	this.read = read;
}

// read(): Método que lee el archivo correspondiente a al certificado digital de un firmante
// formato binario (DER), regresa un objeto tipo certificate x.509.
// certificate: una instancia de un objeto mapeado a uno de tipo certificado digital x.509.
function read() {
	try {
		this.certificate = pki.certificateFromAsn1(forge.asn1
				.fromDer(forge.util.decode64(this.certificateFile
						.split("base64,")[1])));
	} catch (certificateException) {
		console.log("CertificateException: ", certificateException.message);
	}
	return this.certificate;
}

// getIssuer: Método que permite obtener el valor del distinguished name de la
// autoridad que firma el certificado digital.
// issuer: el valor del distinguished name.
Certificate.prototype.getIssuer = function() {
	return this.issuer = this.certificate.issuer;
}

// getPublicKey: Método que permite obtener el valor de la clave pública de un certificado digital.
// publicKey: un objeto tipo publicKey.
Certificate.prototype.getPublicKey = function() {
	return this.publicKey = this.certificate.publicKey;
}

// getSubject: Método que permite obtener el valor subject de un certificado digital.
// publicKey: el valor del campo subject .
Certificate.prototype.getSubject = function() {
	return this.subject = this.certificate.subject;
}

// validate: Método que permite validar de forma completa un certificado digital x.509 
// result: valor de tipo booleano que indica sí todas las validaciones 
// del certificado fueron aprobadas con éxito
Certificate.prototype.validate = function() {
	console.log("Método para validar el certificado");
	var result = false;
	
	// 1.- Validación de la integredidad del certificado 
	// 4.- Verificar que el certificado fue emitido por una autoridad certificadora de confianza
		console.log("1.- Validación de la integredidad del certificado");
		console.log("caCerts", caCerts);
		if (caCerts != undefined) {			
			for (i in caCerts) {
				try {
					result = caCerts[i].certificate.verify(this.certificate);
				} catch (isNotParentException) {
					console.log(isNotParentException);
				}	
			}
		}else{
			return false;
		}		

	// 2.- Validar vigencia del certificado
		console.log("2.- Validar vigencia del certificado");
		if(result)
			var fechaActual = Crypto.getTime();					 			 
			if((this.certificate.validity.notBefore > fechaActual) || (this.certificate.validity.notAfter < fechaActual))			
				return false;			
		
	// 3.- Validar las operaciones que se deseab realizar con el certificado
		console.log("3.- Validar las operaciones que se desean realizar con el certificado");
		if(result){
			var digitalSignature = false;
			for(i in this.certificate.extensions){	
				if(this.certificate.extensions[i].name == "keyUsage")
					if(this.certificate.extensions[i].digitalSignature)
						digitalSignature = false;				
			}
			if(!digitalSignature){
				return true;
			}	
		}
	// 5.- Validar estado de revocación del certificado
		console.log("5.- Validar estado de revocación del certificado");
		
		// Implementar request OCSP con libreria PKI de globalSign 
	
	return result;
}