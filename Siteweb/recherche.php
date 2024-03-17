<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<?php include_once'menunav.php'; ?>

<div class="mainrecherche">
	


<div class="resultat">
<?php

include_once'database.php';
global $db;
if (isset($_POST['recherche']) AND !empty($_POST['recherche'])) {
	extract($_POST);
	$film = $db ->query('SELECT id,titre,year From movie WHERE titre like "%'.$recherche.'%" ');
	$r =$film->rowCount();


if($r==0){
	echo('aucun film trouvé !');
}
else{
	while($res = $film ->fetch()){
		?>
<a href="pagefilm.php?film=<?=$res['id'] ?>"><?=$res['titre'] ?> (<?=$res['year']?>)</a><br>
  
<?php
	}


}
}
else{
$film = $db ->query('SELECT id,titre,year From movie');
	$r =$film->rowCount();
	while($res = $film ->fetch()){
		?>
<a href="pagefilm.php?film=<?=$res['id'] ?>"><?=$res['titre'] ?> (<?=$res['year']?>)</a><br>
  
<?php
}
}
  ?>
</div>

</div>

<?php include_once'footer.php' ?>
</body>
</html>