/*
*
*    Custom JS
*
 */

$( document ).ready(function() {

    /* Login Message */
    if ($('#typed-headline').length != 0) {
        $("#typed-headline").typed({
            strings: ["Obey, Consume, Login<br><span>NEWS AGGREGATOR</span>"]
        });
    }

    /* Login Failed Message */
    if ($('#typed-failed-login').length != 0) {
        $("#typed-failed-login").typed({
            strings: ["Login Failed, <br><span>please try again</span>"]
        });
    }
});