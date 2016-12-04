<a id="headerHome" href="/">Home</a>
<?php if($logged): ?>
<a id="headerGoToEditProfile" href="/EditProfile"><?= $_SESSION["user"]->username(); ?></a>
    <a id="headerLogOut" href="/logout">Logout</a>
<?php else: ?>
    <a id="headerLogIn" href="/login">Log in</a><a id="headerRegister" href="/register">Register</a>
<?php endif; ?>