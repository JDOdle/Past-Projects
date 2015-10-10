$(function() {
  var homeGalImgs = $('.image');
  var homeGalImgSrcs = new Array(homeGalImgs.length);
  for (var i = 0; i < homeGalImgSrcs.length; i++) {
    homeGalImgs[i].src = "homeGallary/image"+i+".jpg";
    homeGalImgSrcs[i] = "homeGallary/image"+i+".jpg";
  }
  var slideShowPos = 0;
  //main menu navigation functionality
  $("page-cover").width($(window).width());
  
  $('.main-menu-item').click(function() {
                             var thisID = "#" + this.id + "-page";
                             if (thisID == "#gallaries-page") {
                                thisID = "#home-page";
                                centerGalImages();
                             }
                             var ids = $(".page-content").filter(':visible');
                             var visibleID = "#" + ($(".page-content").filter(':visible')[0].id);
                             if (thisID != visibleID) {
                             $(visibleID).fadeOut(500, function() {$(thisID).delay(1).fadeIn(1000);});
                             }
                             if (thisID == "#contact-page") adjustHeight(visibleID);
                        });
  
  $('#gallaries, #gallaries-sub-menu').hover(function() {
                        var gal = $('#gallaries');
                        var posTop = gal.position().top;
                        var posLeft = gal.position().left;
                        var posWidth = gal.width();
                        $('#gallaries-sub-menu').css({top: posTop+45, left: posLeft-(posWidth/2)-27});
                        $('#gallaries-sub-menu').show(0)},
                        function() {
                        $('#gallaries-sub-menu').delay(350).hide(0);
                        });
  
  //navigation aesthetics functionality
  $('.fade').hover(function() {
                   $(this).fadeTo(300, 1);},
                   function() {
                   $(this).fadeTo(300, .4);
                   });
  
  //contact page textarea sizing
  $(window).resize(function() {
                   adjustHeight("#" + ($(".page-content").filter(':visible')[0].id));
                   positionImage();
                   centerGalImages();
                   });
  
  //gallary image aesthetics
  $('.image').hover(function() {
                    $(this).fadeTo(200, .5);},
                    function() {
                    $(this).fadeTo(200, 1);
                    });
  
  //open slideshow functionality
  $('.image').click(function() {
                    $('#page-cover').fadeIn(500);
                    $('.slide-show').fadeIn(500);
                    $('#exit-btn').fadeIn(500);
                    });
  
  $('.slide-show').not('.slide-show-btn, #slide-show-image').click(function() {
                    $('#page-cover').fadeOut(500);
                    $('.slide-show').fadeOut(500);
                    $('#exit-btn').fadeOut(500);
                    });
  
  $('.image').click(function() {
                    var imgSrc = $(this).prop('src');
                    $('#slide-show-image').attr('src', imgSrc);
                    positionImage();
                    for (var i = 0; i < homeGalImgSrcs.length; i++) {
                        if (homeGalImgSrcs[i] == $(this).prop('src'))
                            slideShowPos = i;
                    }
                    });
  $('#last').click(function() {
                    slideShowPos--;
                    if (slideShowPos == -1) slideShowPos = 15;
                    slideShowPos %= 16;
                    var newSrc = homeGalImgSrcs[slideShowPos];
                    $('#slide-show-image').attr('src', newSrc);
                    positionImage();
                 });
  $('#next').click(function() {
                    slideShowPos++;
                    slideShowPos %= 16;
                    var newSrc = homeGalImgSrcs[slideShowPos];
                    $('#slide-show-image').attr('src', newSrc);
                    positionImage();
                 });
});

function adjustHeight(visibleID) {
    var width = $(visibleID).width();
    var textAreaHeight = (width*(91/270))-205;
    $('.form > textarea').height(textAreaHeight);
}

function positionImage() {
    var leftOffset = ($('#page-cover').width()/2) - ($('#slide-show-image').width()/2)-7;
    var topOffset = ($('#page-cover').height()/2) - ($('#slide-show-image').height()/2)-7;
    $('#slide-show-image').css({top: topOffset, left: leftOffset});
}

function positionBtns() {
    var topOffset = ($('#page-cover').height()/2) - ($('slide-show-btn').height()/2);
    $('.slide-show-btn').css({top: topOffset});
}

function centerGalImages() {
    $('.image').filter(':visible').css('left', function() {return ($(this).parent().width()-$(this).width())/2;});
}




