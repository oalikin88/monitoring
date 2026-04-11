/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


 $(function(){
     
      $("#toggle-btn")[0].addEventListener("click", function() {
         // console.log($(this).closest("aside").attr('class'));
          if($(this).closest("aside").attr('class') == '') {
              localStorage.setItem('sidebarPosition', 'noExpand');
          } else {
              localStorage.setItem('sidebarPosition', 'expand');
          }
          
      });
     
        $('a').each(function(){
            if (window.location.href.indexOf('?') >= 0) {
                
                if ($(this).prop('href') == window.location.href.substring(0, window.location.href.indexOf('?'))) {
                $(this).addClass('active');
                $(this).closest('ul').addClass('show');
                if($(this).closest('ul').parent().closest('ul').length > 0) {
                    $(this).closest('ul').parent().closest('ul').addClass('show');
                }
                if($(this).closest('ul').parent().closest('ul').parent().closest('ul').length > 0) {
                    $(this).closest('ul').parent().closest('ul').parent().closest('ul').addClass('show');
                }
                $(this).parents('li').addClass('active');
            }
                
            } else if ($(this).prop('href') == window.location.href) {
                $(this).addClass('active');
                $(this).closest('ul').addClass('show');
                if($(this).closest('ul').parent().closest('ul').length > 0) {
                    $(this).closest('ul').parent().closest('ul').addClass('show');
                }
                if($(this).closest('ul').parent().closest('ul').parent().closest('ul').length > 0) {
                    $(this).closest('ul').parent().closest('ul').parent().closest('ul').addClass('show');
                }
                $(this).parents('li').addClass('active');
            }
           
         
            
        });
        
        if(localStorage.getItem('sidebarPosition') === 'expand') {
          if(!$("#sidebar").hasClass('expand')) {
               $("#sidebar").addClass("expand");
          }
      }
        
    });