/**	
	* Template Name: Apex App
	* Version: 1.0	
	* Template Scripts
	* Author: MarkUps
	* Author URI: http://www.markups.io/

	Custom JS
	
	1. FULL OVERLAY MENU
	2. MENU SMOOTH SCROLLING
	3. VIDEO POPUP
	4. APPS SCREENSHOT SLIDEER ( SLICK SLIDER )
	5. BOOTSTRAP ACCORDION  
	
	
**/



(function( $ ){


	/* ----------------------------------------------------------- */
	/*  1. FULL OVERLYAY MENU
	/* ----------------------------------------------------------- */

   $('.mu-menu-btn').on('click', function(event) {
	   
        event.preventDefault();
        
        $('.mu-menu-full-overlay').addClass('mu-menu-full-overlay-show');
       
    });
   
    // when click colose btn
    
    $('.mu-menu-close-btn').on('click', function(event) {
	    
	    event.preventDefault();
	    
		$('.mu-menu-full-overlay').removeClass('mu-menu-full-overlay-show');
		
    });

    // when click menu item overlay disappear

    $('.menu a').on('click', function(event) {
	   
        event.preventDefault();
        
        $('.mu-menu-full-overlay').removeClass('mu-menu-full-overlay-show');
       
    });

    /* ----------------------------------------------------------- */
	/*  2. MENU SMOOTH SCROLLING
	/* ----------------------------------------------------------- */ 

	//MENU SCROLLING WITH ACTIVE ITEM SELECTED

	 $(".menu a").click(function(event){
         event.preventDefault();
         //calculate destination place
         var dest=0;
         if($(this.hash).offset().top > $(document).height()-$(window).height()){
              dest=$(document).height()-$(window).height();
         }else{
              dest=$(this.hash).offset().top;
         }
         //go to destination
         $('html,body').animate({scrollTop:dest}, 1000,'swing');
     });
	
})( jQuery );



  
	