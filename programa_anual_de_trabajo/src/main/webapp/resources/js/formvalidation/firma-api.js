/**
 * firma-api.js 1.0 Javascript Cryptography Library
 */

/**
 * Crea el espacio de la librería con el alias de 'ine'
 * @namespace ine
 */
(function(ine, forge) {
	'use strict';
	
	/**
	 * Devuelve la versión de la librería.
	 * <pre>
	 * ine.version
	 * </pre>
	 */
	ine.version = '1.0';
	
	/**
	 * Variables globales
	 */
	var caCerts = new Array();
	var fasn1 = forge.asn1;
    var fpki = forge.pki;
    var futil = forge.util;
	/**
	 * @class Signer
	 * @classdesc Clase que contiene la información asociada a un firmante.
	 * @since 1.0
	 */
	ine.Signer = function() {
		/**
		 * Certificado X.509 del firmante.
		 * @name Signer#certificate
		 * @type Object 
		 * @since 1.0
		 */
		this.certificate = undefined;
		
		/**
		 * Clave privada del firmante.
		 * @name Signer#privateKey
		 * @type Object
		 * @since 1.0
		 */
		this.privateKey = undefined;
		
		/**
		 * Nombre del algoritmo que utiliza el firmante para realizar la digestión.
		 * @name Signer#hashAlgorithm
		 * @type String 
		 * @since 1.0
		 */
		this.hashAlgorithm = undefined;
	};
	

	/**
	 * @class Infos
	 * @classdesc Clase que contiene la información de un signerInfo asociado al estandar PCKS7.
	 * @since 1.0
	 */
	ine.Infos= function(){
		/**
		 * Algoritmo criptográfico
		 * @name Infos#hashAlg
		 * @type String 
		 * @since 1.0
		 */
        this.hashAlg = undefined;

        /**
		 * Mensaje Digesto
		 * @name Infos#messageDigest
		 * @type String 
		 * @since 1.0
		 */
        this.messageDigest = undefined;

         /**
		 * Encriptado del Mensaje Digesto o Firma Digital
		 * @name Infos#encryptedDigest
		 * @type String 
		 * @since 1.0
		 */
        this.encryptedDigest = undefined;

        /**
		 * Atributos de SignerInfo
		 * @name Infos#sAttr
		 * @type String 
		 * @since 1.0
		 */
        this.sAttr = {};

        /**
		 * Certificado del firmante
		 * @name Infos#signerCert
		 * @type Object 
		 * @since 1.0
		 */
        this.signerCert = undefined;

        /**
		 * Algoritmo criptográfico de la firma
		 * @name Infos#sigAlg
		 * @type String 
		 * @since 1.0
		 */
        this.sigAlg = undefined;

        /**
		 * Llave privada del firmante
		 * @name Infos#sigAlg
		 * @type Object 
		 * @since 1.0
		 */
        this.signerPrvKey = undefined;
	};
	
	
	/**
	 * Clase que provee las funciones relacionadas a la firma de información.
	 * @namespace ine.Crypto
	 * @since 1.0
	 */
	ine.Crypto = {
		
		/**
		 * Método que realiza el empaquetado del PKCS7.
		 * @param {String} data Información a ser firmada.
		 * @param {Object} signers Firmante o firmantes de la información.
		 * @param {String} date Fecha en que se realiza la firma digital
		 * @since 1.0
		 */
		
		signData: function(data, signers, date) {
			
			var arraySignersInfo = ine.Crypto.signersInfo(data,signers,date);
            var arrayCertPem = ine.Crypto.certsPem(signers);
            var arraydigestAlgorithms = ine.Crypto.digestAlgorithms(signers);
            
            var param = {
                    content: {str: data},
                    digestAlgorithms: arraydigestAlgorithms,
                    certs: arrayCertPem,
                    signerInfos: arraySignersInfo
            };
            
            try {
            	var sd = KJUR.asn1.cms.CMSUtil.newSignedData(param);
            	console.log(sd);
            	var aux=forge.util.encode64(forge.pki.pemToDer(sd.getPEM()).getBytes())
            	console.log(aux);
            	return aux;
            } catch(ex) {
            	console.log(ex);
            }
		},
		
		/**
		 * Método que forma el paquete SignerInfo con la información de uno o más firmantes.
		 * @param {String} data Información a ser firmada
		 * @param {Object} signers Firmante o firmantes de la información.
		 * @param {String} date Fecha en que se realiza la firma digital
		 * @since 1.0 
		 */
		signersInfo: function(data,signers,date) {
			
			var pkpem = undefined;
            var certpem = undefined;
            var algDig = undefined;
            var dataHash = undefined;
            var auxInfos = new ine.Infos();
            var signerInfo = new Array();
            
            for (var i=0;i<signers.length;i++) {
            	pkpem = forge.pki.privateKeyToPem(signers[i].privateKey);
	            certpem = forge.pki.certificateToPem(signers[i].certificate);
	            algDig = ine.Util.getDigestAlgoritm(signers[i].certificate.signatureOid);
	            signers[i].hashAlgorithm=algDig.toUpperCase()+"withRSA";
	            dataHash = ine.Util.getHash(algDig, data);
			    
	            auxInfos.hashAlg=algDig;
	            auxInfos.messageDigest = dataHash;
	            auxInfos.encryptedDigest = ine.Crypto.signPKCS1(data,signers[i]);
	            auxInfos.sAttr.SigningTime = date;
	            auxInfos.signerCert=certpem;
	            auxInfos.sigAlg=signers[i].hashAlgorithm;
	            auxInfos.signerPrvKey=pkpem;
	            
	            signerInfo.push(auxInfos);
            }			
            
            return signerInfo;
		},
		
		/**
		 * Metodo que devuelve en forma de arreglos los certificados de los firmantes
		 * @param {Object} signers Firmante o firmantes de la información.
		 * @since 1.0
		 */
		certsPem: function(signers) {
            var certificates = new Array();
            for (var i=0;i<signers.length;i++) {
	            certificates.push(forge.pki.certificateToPem(signers[i].certificate));
            }
            return certificates;
		},
		
		/**
		 * Método que realiza una firma digital y lo empaqueta en un estandar PKCS1
		 * @param {String} data Información a ser firmada
		 * @param {Object} signers Firmante o firmantes de la información.
		 * @since 1.0
		 */
		signPKCS1: function(data,signer) {
		  var algorithm = ine.Util.getDigestAlgoritm(signer.certificate.signatureOid);
          var digest = ine.Util.getHash(algorithm,data);
          var signature = signer.privateKey.sign(digest);
          return signature;
		},
		
		/**
		 * Método que devuelve en forma de arreglo los OID del algoritmo de encriptación de cada firmante
		 * @param {Object} signers Firmante o firmantes de la información.
		 * @since 1.0
		 */
		digestAlgorithms: function(signers) {
          var algorithms = new Array();
          for (var i=0;i<signers.length;i++) {
          	algorithms.push(signers[i].certificate.signatureOid);
          }
          return algorithms;
		},
		
		
		/**
		 * Método que realiza una firma digital sin realizar Hash al dato o datos a firmar y lo empaqueta con estandar PCKS1.
		 * @param {Object} privateKey llave privada.
		 * @param {String} messageDigestFromHex Mensaje digesto o Hash del mensaje en formato hexadecimal
		 * @since 1.0
		 */
		signPKCS1WithoutHash: function(privateKey,messageDigestFromHex) {
			var sig = new KJUR.crypto.Signature({alg: "SHA1withRSA"});
			var pkpem = fpki.privateKeyToPem(privateKey);
			sig.init(pkpem);
			sig.updateHex(messageDigestFromHex);
			var hSigVal = sig.sign();
	          return hSigVal;
		},
		

		/**
		 * Método que obtiene la llave privada del firmate dada una lectura del archivo por un objeto FileReader como 'Data URL'.
		 * @param {Object} privateKeyResult Resultado de la lectura de un archivo por medio de un objeto FileReader como 'Data URL'.
		 * @param {String} password Contraseña del firmate para la llave privada.
		 * @since 1.0
		 */
		readPrivateKey:  function(privateKeyResult, password) {
            var pkcs8 = futil.decode64(privateKeyResult);
            var pkcs8asn1 = fasn1.fromDer(pkcs8);
            var privateKeyInfo = fpki.decryptPrivateKeyInfo(pkcs8asn1, password);
            var privateKey = fpki.privateKeyFromAsn1(privateKeyInfo);
            return privateKey;
		},
		
		/**
		 * Método que permite obtener la hora actual del servidor de acuerdo a la diferencia de zona horaria especificada
		 * @param {requestCallback} callback - Callback que maneja el response del ajax.
		 * @since 1.0
		 */
		getTime: function(callback){
			$.ajax({
				type: 'GET',
			    url: "https//www.timeapi.org/utc/now.json",
			    contentType: "application/json",
			    dataType: "jsonp",
			    succes: callback
			});
		},

		/**
		 * This callback is displayed as a global member.
		 * @callback requestCallback
		 * @param {jsonp} json
		 * 
		 * function callback(json) {
		 *   console.log(new Date(json.dateString));
		 * }
		 *
		 */
		

		
	}
	
	
	
	/**
	 * @class Certificate
	 * @classdesc Clase que contiene la información asociada a un certificado.
	 * @since 1.0
	 */
	ine.Certificate = function(certificateFile) {
		/**
		 * Archivo que contiene el certificado digital de un firmante.
		 * @name Certificate#certificateFile
		 * @type Object 
		 * @since 1.0
		 */
		this.certificateFile = certificateFile;
		
		/**
		 * Instancia de un  objeto mapeado a uno de tipo certificado digital x.509.
		 * @name Certificate#certificate
		 * @type Object 
		 * @since 1.0
		 */
		this.certificate;
		
		/**
		 * Método que lee el archivo correspondiente a al certificado digital de un firmante
		 * @function Certificate.read
		 * @return Una instancia de un objeto mapeado a uno de tipo certificado digital x.509.
		 * @since 1.0
		 */
		this.read = function() {
			
	        try {
	        	this.certificate = forge.pki.certificateFromAsn1(fasn1.fromDer(futil.decode64(this.certificateFile)));
	        } catch (certificateException) {
//	                console.log("CertificateException: ", certificateException.message);
	        }
	        return this.certificate;
		};
	}
	
	/**
	 * Método que permite obtener el valor del distinguished name de la autoridad que firma el certificado digital.
	 * @function Certificate.getIssuer
	 * @return El valor del distinguished name.
	 * @since 1.0
	 */
	ine.Certificate.prototype.getIssuer = function() { return this.issuer = this.certificate.issuer; };
	
	/**
	 * Método que permite obtener el valor de la clave pública de un certificado digital.
	 * @function Certificate.getPublicKey
	 * @return Un objeto tipo publicKey.
	 * @since 1.0
	 */
	ine.Certificate.prototype.getPublicKey = function() { return this.publicKey = this.certificate.publicKey; }
	
	/**
	 * Método que permite obtener el valor subject de un certificado digital.
	 * @function Certificate.getSubject
	 * @return El valor del campo subject.
	 * @since 1.0
	 */
	ine.Certificate.prototype.getSubject = function() { return this.subject = this.certificate.subject; }
	
	/**
	 * Método que permite validar de forma completa un certificado digital x.509 
	 * @function Certificate.validate
	 * @return Valor de tipo booleano que indica sí todas las validaciones del certificado fueron aprobadas con éxito.
	 * @since 1.0
	 */
	ine.Certificate.prototype.validate = function(fechaActual) {
		
		var result = false;
		
			console.log("1.- Validación de la integredidad del certificado");
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

			console.log("2.- Validar vigencia del certificado");
			if(result)
				if((this.certificate.validity.notBefore > fechaActual) || (this.certificate.validity.notAfter < fechaActual))			
					return false;			
			
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
			console.log("5.- Validar estado de revocación del certificado");
		return result;
	}
	/**
	 * Método que permite identificar el algoritmo Hash del certificado.
	 * @function Certificate.getHashAlgorithm
	 * @return {String} Algoritmo Hash
	 * @since 1.0
	 */
	ine.Certificate.prototype.getHashAlgorithm = function() { return this.hashAlgorithm = this.certificate.signatureOid; }
	
	
	/**
	 * Clase que provee funcionalidades auxiliares.
	 * @namespace ine.Util
	 * @since 1.0
	 */
	ine.Util = {
		/**
		 * Método que realiza la digestión de la información de acuerdo al algoritmo proporcionados.
		 * @param {String} algoritmo Firmante de la información.
		 * @param {String} data Información a ser cifrada.
		 * @return {String} Hash del algoritmo especificado como cadena
		 * @since 1.0
		 */
			getHash: function(algoritmo, data) {
				var alg = algoritmo.toLowerCase(), md;
				if (alg == 'sha1') md = forge.md.sha1.create();
				else if (alg == 'sha256') md = forge.md.sha256.create();
				else if (alg == 'sha384') md = forge.md.sha384.create();
				else if (alg == 'sha512') md = forge.md.sha512.create();
				else if (alg == 'md5') md = forge.md.md5.create();
				
				if (md === undefined)
					throw new NoSuchAlgorithmException();
				
				md.update(data);
				return md;
			},
	
	/**
	 * Método que indentifica el algoritmo Hash del certificado proporcionado.
	 * @param {String} algoritmoOID Identificador OID del algoritmo hash del certificado.
	 * @return {String} Nombre del algoritmo de encriptación Hash
	 * @since 1.0
	 */
		getDigestAlgoritm: function(algorithmOID) {
			var algorithmHashName = undefined;
			switch(algorithmOID) {
			
			case '1.2.840.113549.1.1.5': algorithmHashName='sha1'; break;
			case '1.2.840.113549.1.1.4': algorithmHashName='md5'; break;	
			case '1.2.840.10040.4.3': algorithmHashName='sha1'; break;	
			case '1.3.14.3.2.29': algorithmHashName='sha1'; break;
			case '1.3.14.3.2.15': algorithmHashName='sha'; break;
			case '1.3.14.3.2.3': algorithmHashName='md5'; break;
			case '1.3.14.3.2.13': algorithmHashName='sha1'; break;
			case '1.3.14.3.2.27': algorithmHashName='sha1'; break;		
			case '1.3.14.3.2.26': algorithmHashName='sha1'; break;
			case '1.2.840.113549.2.5': algorithmHashName='md5'; break;
			case '2.16.840.1.101.3.4.2.1': algorithmHashName='sha256'; break;
			case '2.16.840.1.101.3.4.2.2': algorithmHashName='sha384'; break;		
			case '2.16.840.1.101.3.4.2.3': algorithmHashName='sha512'; break;
			case '1.2.840.113549.1.1.11': algorithmHashName='sha256'; break;
			case '1.2.840.113549.1.1.12': algorithmHashName='sha384'; break;
			case '1.2.840.113549.1.1.13': algorithmHashName='sha512'; break;
			case '1.2.840.10045.4.1': algorithmHashName='sha1'; break;
			case '1.2.840.10045.4.3.2': algorithmHashName='sha256'; break;
			case '1.2.840.10045.4.3.3': algorithmHashName='sha384'; break;
			case '1.2.840.10045.4.3.4': algorithmHashName='sha512'; break;
				return algorithmHashName;
			default: return undefined;
			}
			return algorithmHashName;
		}
	};
	
}(window.ine = window.ine || {}, forge));

(function() {
    String.prototype.getBytes = function() {
            var bytes = [];
            for (var i = 0; i < this.length; i++) {
                    var charCode = this.charCodeAt(i);
                    var cLen = Math.ceil(Math.log(charCode)/Math.log(256));
                    for (var j = 0; j < cLen; j++) {
                        bytes.push((charCode << (j*8)) & 0xFF);
                    }
            }
            return bytes;
    }
})();