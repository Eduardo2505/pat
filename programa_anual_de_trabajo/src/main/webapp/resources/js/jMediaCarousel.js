;(function ($) {
    "use strict";
	var uuid = 0;
	
	var JMediaCarousel = function( $wrapper , mediaArray , options ) {
		var defaults = {
			callbackOnSlid: function(index){},
			callbackOnItemLoaded: function(index){},
			minHeight: '301px',
			pagerAffixTop: function( $carousel ){ return $carousel.offset().top + 50 },
			pagerAffixBottom: function( $carousel ){ return $(document).height() - $carousel.offset().top - $carousel.height() + 20 },
			affix: function( thisEl , $carousel ){ $(thisEl).css({ 'width': $carousel.outerWidth() - 20 }) },
			scrollTop: function( $carousel ){ return $carousel.offset().top }	
		};

		var $options = $.extend(defaults, options);
		
		var $carousel,
			$carouselIndicators,
			$carouselInner,
			$carouselArrows;

		function clean(){
			$wrapper.empty();
			if( $wrapper.data('media-carousel') )
				 $wrapper.removeData('media-carousel')
		};
		
		function load() {
			clean();
			createDOM();
			$wrapper.data('media-carousel',this);
		};	
		
		function createDOM(){
			if( mediaArray.length == 0 ){
				$wrapper.html( 'Sin items' );
				return;
			}
			
			uuid++;
			
			var $templateCarousel = '<div id="media-carousel-'+uuid+'" class="carousel slide" data-ride="carousel" data-interval="false" style="min-width:100px;min-height:'+$options.minHeight+';background-color:#ffffff;">' +

//				'<nav style="position: absolute;z-index: 1;width: calc(100% - 20px); top: 50px;">'+
//				  '<ul class="affix-top carrousel-arrows pager">' +
//					'<li class="previous"><a href="#media-carousel-'+uuid+'" style="margin-left: 25px;" role="button" data-slide="prev"><span aria-hidden="true">&larr;</span> Anterior</a></li>' +
//					'<li class="next"><a href="#media-carousel-'+uuid+'" style="margin-right: 25px;" role="button" data-slide="next">Siguiente <span aria-hidden="true">&rarr;</span></a></li>' +
//				  '</ul>' +
//				'</nav>'+
				
//				'<!-- Indicators -->' +
//				  '<ol class="carousel-indicators"></ol>' +

				  '<!-- Wrapper for slides -->' +
				  '<div class="carousel-inner" role="listbox"></div>' +
				  
				/*  '<!-- Controls -->' +
				  '<a class="left carousel-control" href="#media-carousel-'+uuid+'" role="button" data-slide="prev">' +
					'<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>' +
					'<span class="sr-only">Previous</span>' +
				  '</a>' +
				  '<a class="right carousel-control" href="#media-carousel-'+uuid+'" role="button" data-slide="next">' +
					'<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>' +
					'<span class="sr-only">Next</span>' +
				  '</a>' +*/
				'</div>' ;
			
			var $templateCarouselIndicator = 
				$('<li data-target="#media-carousel-'+uuid+'"></li>');
			
			var $templateCarouselInner = 
				$('<div class="item">' +
					'<div class="item-content" style="text-align: center;">' +
						/*'<img src="http://placehold.it/350x150" alt="...">' +*/
					'</div>' +
//					'<div class="carousel-caption">' +
//						'...' +
//					 '</div>' +
				'</div>');			
			
			$wrapper.html( $templateCarousel );	
			
			$carousel = $wrapper.find('.carousel');
			$carouselIndicators = $carousel.find('.carousel-indicators');
			$carouselInner = $carousel.find('.carousel-inner');
			$carouselArrows = $carousel.find('.carrousel-arrows');
			
			for( var index = 0 ; index < mediaArray.length ; index++ ){
				var $clonedCarouselIndicator = $templateCarouselIndicator.clone();
				var $clonedCarouselInner = $templateCarouselInner.clone();
				
				if( index == 0 ){
					$clonedCarouselIndicator.addClass('active');
					$clonedCarouselInner.addClass('active');
				}
				
				$clonedCarouselIndicator.attr('data-slide-to',index);
				
				$carouselIndicators.append(
					$clonedCarouselIndicator
				);
				
				$carouselInner.append( 
					$clonedCarouselInner
				);
			}
			
			loadItemFromArray( 0 );
			
			$carousel.on('slid.bs.carousel', function ( e ) {
				var activeIndicator = $('li.active', e.target);
				var activeItem = $('.item.active', e.target);
				var indexItem = $('.active', e.target).index();
					
				if( activeItem.hasClass('loaded') == false ){
					loadItemFromArray( indexItem );
				}
				
				$options.callbackOnSlid( indexItem );
				
//				$('html,body').animate({
//					scrollTop: $options.scrollTop( $carousel ) },
//				'fast');
			});
			
//			$carouselArrows.affix({
//				offset: {
//					top: $options.pagerAffixTop( $carousel ), 
//					bottom: $options.pagerAffixBottom( $carousel )
//				}
//			}).on('affix.bs.affix', function() { // before affix
//				$options.affix( this , $carousel );
//			});
		}
		
		function loadItemFromArray( index ){	
			loadItem( index , mediaArray[index].src , mediaArray[index].filename );			
		}
		
		function loadItem( index , mediaURL , caption ){
			var $activeIndicator = $($('li', $carouselIndicators)[index]);
			var $activeItem = $($('.item', $carouselInner)[index]);
			
			var $itemContent = $activeItem.find('.item-content');
			var $itemCaption = $activeItem.find('.carousel-caption');
			
			$itemContent.empty();
			$itemCaption.text( caption );
			
			$itemContent.mediaViewer( mediaURL , {filename: caption,minHeight:$options.minHeight} );
			//$itemContent.append('<img src="' + mediaURL + '" alt="...">');
			
			$activeIndicator.addClass('loaded');
			$activeItem.addClass('loaded');
			
			$options.callbackOnItemLoaded(index);
		}
		
		function gotoIndex( index ){
			$carousel.carousel(index);
		}
		
		return{
			load: load,
			clean: clean,
			gotoIndex: gotoIndex
		}
	};
/*
	$.extend({
        JMediaCarousel: function( options ) {
        }
    });*/

/*	$.fn.jMediaCarousel = function( command ) {
		var jMediaCarousel;
	
		if( this.data('media-carousel') ){
			jMediaCarousel = this.data('media-carousel');
			if( command == 'reset' ){
				jMediaCarousel.clean();
				this.removeData('media-carousel');
			}
		}
	};*/
	
	/*$.fn.jMediaCarousel = function( methodOrOptions ) {
        if ( methods[methodOrOptions] ) {
            return methods[ methodOrOptions ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof methodOrOptions === 'object' || ! methodOrOptions ) {
            // Default to "init"
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }    
    };*/
	
	$.fn.jMediaCarousel = function( mediaArray , options ) {	 
		var jMediaCarousel;
	
		if( this.data('media-carousel') ){
			jMediaCarousel = this.data('media-carousel');
		}
		
		if( !this.data('media-carousel') && typeof mediaArray !== "undefined" ){
			jMediaCarousel = new JMediaCarousel(this, mediaArray , options);
			//this.data('media-carousel', jMediaCarousel);
			jMediaCarousel.load();
			console.log('jCarousel Successfully created!');
		}else if( this.data('media-carousel') && typeof mediaArray !== "undefined" ){
			console.log('jCarousel Already created, clean before create new one!');
		}
		
		return jMediaCarousel;
	};
	
})(jQuery);