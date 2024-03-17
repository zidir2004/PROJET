<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>supfilm</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>


<?php 

include 'menunav.php'; 
include 'navadmin.php';
if($_SESSION['role']==1){
	



include 'database.php';
global $db;

if(isset($_GET['supprime']) AND !empty($_GET['supprime'])){
	$supprime = (int) $_GET['supprime'];

	$req = $db ->prepare('DELETE FROM movie WHERE id= ?');
	$req->execute(array($supprime));
}

$film = $db ->query('SELECT * FROM movie');




 ?>
<div class="membre">
	<h2>Gestion des Films</h2><br>
	<h4>Attention lors de la manipulation toute action est irréversible</h4><br>
<table>
	<tr>
      <td>Id</td>
      <td>titre</td>
      <td>Année</td>
      <td>Réalisateur</td>
      <td>Supprimer</td>
	</tr>	
	
	<?php while ($f = $film -> fetch()) { ?>
	<tr>
		<td><?= $f['id'] ?></td> 
		<td><?= $f['titre']?></td>  
		<td><?= $f['year']?></td> 
		<td><?php
    $req = $db ->prepare("select name FROM realisateur WHERE id=:id");
    $req->execute([
    'id'=>$f['id_realisateur']
    ]);
    $r = $req -> fetch();
    extract($r);
    echo ("$name");
?> </td>
		<td><a href="suppFilm.php?supprime=<?=$f['id'] ?>">supprimer</a></td> 
	</tr>
	<?php } ?>
</table>
</div>



<?php } ?>


</div>
</body>
</html>