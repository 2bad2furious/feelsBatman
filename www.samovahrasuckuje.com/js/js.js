$(window).on("load", function () {

    messageManagement();
    style();

    $(document).resize(function () {
        mainHeight();
    });

    function mainHeight() {
        $("main").css("min-height", $(window).height() - $("header").outerHeight() - $("footer").outerHeight() -($("main").outerHeight()-$("main").innerHeight()));
    }

    function style() {

        mainHeight()
    }

    function messageManagement() {
        if ($(".messages span").length >= 1) {
            $(".messages").fadeIn(50);
            setTimeout(function () {
                $(".messages").fadeOut(500);
            }, msgCount * 1500);
        }
    }

});