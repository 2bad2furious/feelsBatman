<a id="headerHome" href="/"><?= $data["home"]; ?></a>
<?php if($logged): ?>
<a id="headerGoToEditProfile" href="/EditProfile"><?= $data["username"]; ?></a>
    <a id="headerLogOut" href="/logout"><?= $data["logout"] ?></a>
<?php else: ?>
    <a id="headerLogIn" href="/login"><?= $data["login"]; ?></a><a id="headerRegister" href="/register"><?= $data["register"] ?></a>
<?php endif; ?>