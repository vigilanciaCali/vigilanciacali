$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      //event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
  
  $(window).scroll(function() {
    $(".slideanim").each(function(){
      var pos = $(this).offset().top;

      var winTop = $(window).scrollTop();
        if (pos < winTop + 600) {
          $(this).addClass("slide");
        }
    });
  });


});

//AUTO COLLAPSE NAVBAR validate SubMenus
$(document).on('click','.navbar-collapse li',function(e) {
  //console.log("click navbar-collapse.in");
    if( $(e.target).is('li') && $(e.target).attr('class') != 'nav-item btn-group' ) {
        //$(".navbar-collapse").collapse('show');
        //console.log("show");
    }else{
      //console.log("hide");
      $(".navbar-collapse").collapse('hide');
    }
    
}); 

//AUTO COLLAPSE NAVBAR
/*
$('.navbar-collapse a').click(function(){
    $(".navbar-collapse").collapse('hide');
}); */



/*
$(document).on('click','.navbar-collapse.in',function(e) {
    if( $(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle' ) {
        $(this).collapse('hide');
    }
}); */




