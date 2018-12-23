$(document).ready(function () {
    $(document).scroll(function () {
        if ($(window).scrollTop() > $('.forscroll').height()) {
            $('.navbar-wrapper').addClass('is-sticky');
        } else {
            $('.navbar-wrapper').removeClass('is-sticky');
        }
    });
});
