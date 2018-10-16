;(function ($) {
    "use strict";
	
	var MediaViewer = function( $wrapper , src , options ) {
		var defaults = {
			enableExtendButton: false,
			enableDownload: false,
			filename: undefined,
			pdf: { forceHeight:true },
			minHeight: '100px'
		};

		var $options = $.extend(defaults, options);
		var filename;

		function download(){
			$.fileDownload(src,{
				failCallback: function (html, url) {
					alert('No se pudo descargar el archivo.');
				}
			});
		}
			
		function openInModal(){
			$.ModalViewer('open');
			$.ModalViewer('container').mediaViewer(src,{reset: true, 
														pdf:{forceHeight: false}
														});
			
			/*var modal = new Modal();
			modal.setToolbarCenterField( $options.filename );
			modal.getContentContainer().mediaViewer(src,{filename: $options.filename});
			modal.open();*/
		}

		var StatusCard = function( mediaViewer ) {
			var $statusCard = mediaViewer.find( '.status-card' ),
				$message = $statusCard.find( '.message' ),
				$band = $statusCard.find( '.band' );

			function enableLoading() {
				resetStatusCard();
				mediaViewer.addClass( 'is-loading' );
			}	

			function enableSuccess() {
				resetStatusCard();
				mediaViewer.addClass( 'is-succesful-loaded' );
			}	
			
			function enableBroken() {
				resetStatusCard();
				$message.html( ' <b>No se ha podido visualizar el archivo ' + filename + '</b>');
				$band.html( '' );
				$band.click(function(){
					download();
				});
				mediaViewer.addClass( 'is-broken' );
			}	

			function enableNotVisible( msg , band ) {
				resetStatusCard();
				$message.html(  '<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><h3> <b>El archivo ' 
					       + filename + ' no tiene un formato compatible para visualizarlo en el navegador </b></h3>' );
				$band.html( '' );
				$band.click(function(){
					download();
				});
				mediaViewer.addClass( 'is-notvisible' );
			}
			
			function resetStatusCard(){
				mediaViewer.removeClass( 'is-loading' );
				mediaViewer.removeClass( 'is-broken' );
				mediaViewer.removeClass( 'is-notvisible' );
				mediaViewer.removeClass( 'is-succesful-loaded' );
				
				$message.text( '' );
				$band.text( '' );
			}
			
			return{
				enableLoading: enableLoading ,
				enableSuccess:enableSuccess ,
				enableBroken: enableBroken,
				enableNotVisible: enableNotVisible
			}
		};
		
		var FilenameAnalizer = function ( filename ){
			var viewerTypesEnum = {
					 Pdf: 1,
					 Image: 2,
					 Video: 3,
					 Audio: 4,
					 NotVisible: 5,
					 Html: 6,
					 Office: 7
				},
				textExtensions = ['txt'],
				imageExtensions = ['png', 'jpg', 'jpeg', 'gif','PNG', 'JPG', 'JPEG', 'GIF'],
				audioExtensions = ['mp3', 'ogg','MP3', 'OGG'],
				videoExtensions = ['ogv', 'mp4', 'webm','OGV', 'MP4', 'WEBM'],
				pdfExtensions = ['pdf','PDF'],
				htmlExtensions = ['htm', 'html'],
				officeExtensions = ['docx', 'xlsx','doc','xls','DOCX', 'XLSX','DOC','XLS'],
				
				extension = /(?:\.([^.]+))?$/.exec(filename)[1];
				extension = extension ? extension : '';
				
				var viewerType = getViewerType( extension );

			function isHtml() {
				if( viewerType == viewerTypesEnum.Html )
					return true;
				return false;
			}  

			function isImage() {
				if( viewerType == viewerTypesEnum.Image )
					return true;
				return false;
			}  

			function isPdf() {
				if( viewerType == viewerTypesEnum.Pdf )
					return true;
				return false;
			} 
			
			function isAudio() {
				if( viewerType == viewerTypesEnum.Audio )
					return true;
				return false;
			} 
			
			function isVideo() {
				if( viewerType == viewerTypesEnum.Video )
					return true;
				return false;
			} 

			function isOffice() {
				if( viewerType == viewerTypesEnum.Office )
					return true;
				return false;
			} 			

			function isNotVisible() {
				if( viewerType == viewerTypesEnum.NotVisible )
					return true;
				return false;
			}            

			function getViewerType( extension ){
				if( $.inArray( extension, textExtensions ) != -1 )
					return viewerTypesEnum.Text;
				else if( $.inArray( extension, imageExtensions ) != -1 )
					return viewerTypesEnum.Image;
				else if( $.inArray( extension, audioExtensions ) != -1 )
					return viewerTypesEnum.Audio;
				else if( $.inArray( extension, videoExtensions ) != -1 )
					return viewerTypesEnum.Video;
				 else if( $.inArray( extension, pdfExtensions ) != -1 )
					return viewerTypesEnum.Pdf;
				else if( $.inArray( extension, htmlExtensions ) != -1 )
					return viewerTypesEnum.Html;
				else if( $.inArray( extension, officeExtensions ) != -1 )
					return viewerTypesEnum.Office;
				return viewerTypesEnum.NotVisible;
			}

			function getExtension(){
				return extension;
			}
			
			return{
				isImage: isImage,
				isPdf: isPdf,
				isAudio: isAudio,
				isVideo: isVideo,
				isHtml: isHtml,
				isOffice: isOffice,
				isNotVisible: isNotVisible,
				getExtension: getExtension
			}
		};

		var AudioLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $audio = $('<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <b>'+filename+'</b> <br/><br/><br/><br/><br/>'+
					       ' <audio id="demoAudio" controls preload  style="color:green"></audio> <br/><br/><br/>');

			load();

			function load() {
				onLoading();
				$audio.on('error', function (e) {
					onError();
				});
				$audio.attr('src', src);
				$embedMedia.append( $audio );
				onSuccess();
			}
		};

		var VideoLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $video = $('<video id="demoVideo" controls preload style="width: 76%"></video>');
			
			load();

			function load() {
				onLoading();

				$video.on('error', function (e) {
					onError();
				});
				$video.attr('src', src);
				$embedMedia.append( $video );
				onSuccess();
			}
		};

		var HtmlLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $template = $('<div class="html-viewer">' +
									'<div class="html-container" style="display: block;"/>' +
								'</div>'),
				$htmlViewer,				
				$htmlContainer;
			var originalWidth,
				originalHeight = 1000;
			
			load();

			function load() {
				onLoading();

				$embedMedia.append( $template );
				$htmlViewer = $embedMedia.find('.html-viewer');
				$htmlContainer = $htmlViewer.find('.html-container');
				
				if( $options.pdf.forceHeight )
					$htmlViewer.css({
						//'width': $wrapper.outerWidth(),
						'height': $wrapper.outerHeight(),
						'overflow': 'auto'
					});
				
				$htmlContainer.load( src, function( response, status, xhr ) {
				  if ( status == "error" ) {
					onError();
				  }else{
					onSuccess();  
					originalWidth = $htmlContainer.outerWidth(); 
					redraw();
					
					$( window ).resize(function() {
					  redraw();
					});
				  }
				});				
			}
			
			function redraw(){
				
					//$htmlViewer.css({
						//'width': $wrapper.outerWidth(),
						//'height': $wrapper.outerHeight(),
						//'overflow': 'auto'
					//});
				
				var scale = ($wrapper.outerWidth()-40) / originalWidth;
				if( scale <= 1 )
					$htmlContainer.css({
						'zoom': scale,
						//'transform': 'scale(' + scale + ')',
						'transform-origin': '0 0'
					});
			}
		};
		
		var ImageLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $img = $('<img style="opacity:0;" style="width: 100%" />');
			
			load();

			function load() {
				onLoading();

				$img.attr('src', src);

				$img.imagesLoaded()
					.done(function() {
						$embedMedia.append( $img );
						$img.animate( {opacity:1} );
						onSuccess();
					}).fail( function() {
						onError();
				});
			}
		};

		var PdfLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $templatePdfCarousel = $('<div class="embed-responsive embed-responsive-4by3"><iframe class="embed-responsive-item" style="width: 100%; height: 600px"/></div>');
			
			$embedMedia.append( $templatePdfCarousel );
			var $iframe = $embedMedia.find('iframe');
			$iframe.attr('src',src);
			//$iframe.css({"background-color": "#fafaf7"});
			onSuccess();
		};
		
		var GenericLoader = function( $embedMedia , src , onLoading , onError , onSuccess ) {
			var $templateOffice = $('<div >' +
										'<div ><br/><br/><br/><br/><br/><br/><br/><br/></div>' +
										'<div > <img src="../../resources/img/icono-documentos.png"  width="150" height="150">' +
											'<div > <h3> Para visualizar el archivo, da clic en el botón Descargar. <br/> Se utilizará la aplicación vinculada a tu computadora.</h3></div>' +
											'<div > <br/>Descargar<br/><br/><a href="'+src+'"><img src="../../resources/img/icon-download.png"  width="50" height="50"> </a>'+
											'<br/><br/><br/><br/><br/><br/><br/><br/></div>' +
										'</div>' +
									'</div>');
				
			$embedMedia.append( $templateOffice );
		
			onSuccess();
		};		
		
		function load( ){	
			var $template = $('<div class="media-viewer">' +
								'<div class="status-card">' +
									'<div class="icon"></div>' +
									'<div class="placeholder-info">' +
										'<div class="message"></div>' +
										'<div class="band"></div>' +
									'</div>' +
								'</div>' +
								'<div class="embed-media">' +
									'<div class="show-modal-button media-buttons"></div>' +
									'<div class="show-download-button media-buttons"></div>' +
								'</div>' +
							'</div>');
		
			createTemplate();
			
			filename = $options.filename ? $options.filename : getBaseName(src);
			
			var $mediaViewer = $wrapper.find( '.media-viewer' ),
				$embedMedia = $wrapper.find( '.embed-media' ),
				$showModalButton = $mediaViewer.find( '.show-modal-button' ),
				$showDownloadButton = $mediaViewer.find( '.show-download-button' ),
				statusCard = new StatusCard( $mediaViewer ),
				filenameAnalizer = new FilenameAnalizer( filename );

				$mediaViewer.css('min-height',$options.minHeight);
				
			if( filenameAnalizer.isImage() ){
				var imageLoader = new ImageLoader( $embedMedia , 
														src , 
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'image' );
				showExtendButton();
				showDownloadButton();
			}else if( filenameAnalizer.isHtml() ){
				var htmlLoader = new HtmlLoader( $embedMedia , 
														src , 
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'html' );
				showExtendButton();
			//	showDownloadButton();
			}else if( filenameAnalizer.isPdf() ){
				var pdfLoader = new PdfLoader( $embedMedia , 
														src , 
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'pdf' );
				showExtendButton();
				showDownloadButton();
			}else if( filenameAnalizer.isAudio() ){
				var audioLoader = new AudioLoader( $embedMedia , 
														src , 
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'audio' );
				
				showDownloadButton();
			}else if( filenameAnalizer.isVideo() ){
				var videoLoader = new VideoLoader( $embedMedia , 
														src ,  
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'video' );
				showExtendButton();
				showDownloadButton();
			}else if( filenameAnalizer.isOffice() ){
				var officeLoader = new GenericLoader( $embedMedia , 
														src ,  
														statusCard.enableLoading , 
														statusCard.enableBroken , 
														statusCard.enableSuccess );
				$mediaViewer.addClass( 'office' );
				showExtendButton();
				showDownloadButton();
			}else if( filenameAnalizer.isNotVisible() ){
				statusCard.enableNotVisible( filename , filenameAnalizer.getExtension() );
				$mediaViewer.addClass( 'not-visible' );
			}

			function showExtendButton(){
				if( $options.enableExtendButton ){
					$showModalButton.show();
					$showModalButton.click(
						function (){
							openInModal();		
						});
				}
			}
			
			function showDownloadButton(){
				if( $options.enableDownload ){
					$showDownloadButton.show();
					$showDownloadButton.click(
						function (){
							download();
							//openInModal();		
						});
				}
			}
			
			function createTemplate() {
			/*	if( $options.minSizeAsParent == true )
					$template.css( 'min-height' , $wrapper.height() );*/
				$wrapper.html( $template );
			}		

			function getBaseName(url) {
			  if(!url || (url && url.length === 0)) {
				return "";
			  }
			  var index = url.lastIndexOf("/") + 1;
			  var filenameWithExtension = url.substr(index);
			  var basename = filenameWithExtension.split(/[?&#]+/)[0];

			  // Handle '/mypage/' type paths
			  if(basename.length === 0) {
				url = url.substr(0,index-1);
				basename = getBaseName(url);
			  }
			  return basename ? basename : ""; 
			}			
		};
		
		return{
			load: load
		}
	};

	$.extend({
        MediaViewer: function( options ) {
			$('a.media').each( function(){
				var $newDiv = $('<div></div>');
				$(this).replaceWith( $newDiv );
				$newDiv.mediaViewer( $(this).attr('href') , options );
			});
        }
    });
	
	$.fn.mediaViewer = function(src,options) {	 
		/*var defaults = {
			reset: false
		};
		var $options = $.extend(defaults, options);*/

		var mediaViewer = this.data('media-viewer');
	
	/*	if( this.data('media-viewer') ){
			if( $options.reset )
				this.removeData('media-viewer');
		}*/
		
		if( !mediaViewer ){
			mediaViewer = new MediaViewer(this, src , options);
			this.data('media-viewer', mediaViewer);
			mediaViewer.load();
		}
		
		return mediaViewer;
	};	

})(jQuery);
