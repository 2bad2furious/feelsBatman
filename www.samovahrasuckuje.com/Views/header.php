tohle je header

<?php if($logged): ?>
<a href="/EditProfile"><?= $_SESSION["user"]->username(); ?></a><a href="/logout">Logout</a>
<?php else: ?>
    <a href="/login">Log in</a><a href="/register">Register</a>
<?php endif; ?>