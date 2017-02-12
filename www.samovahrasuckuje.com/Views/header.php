<a id="headerHome" href="/"><?= $data["home"]; ?></a>
<?php if($logged): ?>
<a id="headerGoToEditProfile" href="/EditProfile"><?= $data["username"]; ?></a>
    <a id="headerLogOut" href="/Logout"><?= $data["logout"] ?></a>
<?php else: ?>
    <a id="headerLogIn" href="/Login"><?= $data["login"]; ?></a><a id="headerRegister" href="/Register"><?= $data["register"] ?></a>
<?php endif; ?>