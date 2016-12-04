<img id="preview"
     src="/upload/prof_pics/<?= (file_exists("upload/prof_pics/" . Globals::parseId($_SESSION["user"]->id()))) ? Globals::parseId($_SESSION["user"]->id()) . ".png" : "def.jpg" ?>"><?= $form->returnHtml(); ?>
<script>

    $("#preview").on("load", function () {
        function callback() {
            console.info($("#preview"))
            if ($("#preview").height() >= $("#preview").width()) $("#preview").css({height: "200px", width: "auto"})
            else $("#preview").css({width: "200px", height: "auto"})
        }

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#preview').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
        callback();
    }
    $("form input[type=file]").change(function () {
        readURL(this)
    });

    callback();
    });
</script>