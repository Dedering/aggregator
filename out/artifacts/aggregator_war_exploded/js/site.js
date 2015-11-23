$( document ).ready(function() {
     console.log( "ready!" );


    if ($('#cell-three').length != 0) {
        $('#cell-three').removeClass('col-sm-4').addClass('col-sm-0');
        $('#cell-one').removeClass('col-sm-4').addClass('col-sm-6');
        $('#cell-two').removeClass('col-sm-4').addClass('col-sm-6');
        $('#add-another').click(function() {
            $('#cell-three').show().addClass('col-sm-6');
            $('#cell-one').removeClass('col-sm-6').addClass('col-sm-4');
            $('#cell-two').removeClass('col-sm-6').addClass('col-sm-4');
        });
}




});