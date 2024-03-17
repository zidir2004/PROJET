<?php 
session_start();
 ?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>administration</title>
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

	$req = $db ->prepare('DELETE FROM user WHERE id= ?');
	$req->execute(array($supprime));
}
if(isset($_GET['role']) AND !empty($_GET['role'])){
	$role = (int) $_GET['role'];

	$req = $db ->prepare('UPDATE user SET role = 1 WHERE id= ?');
	$req->execute(array($role));
}
$membres = $db ->query('SELECT * FROM user');




 ?>
<div class="membre">
	<h2>Gestion utiilisateurs</h2><br>
	<h4>Attention lors de la manipulation toute action est irréversible</h4><br>
<table>
	<tr>
      <td>Id</td>
      <td>Pseudo</td>
      <td>Email</td>
      <td>Supprimer</td>
      <td>Promouvoir</td>
	</tr>	
	
	<?php while ($m = $membres -> fetch()) { ?>
	<tr>
		<td><?= $m['id'] ?></td> 
		<td><?= $m['pseudo']?></td>  
		<td><?= $m['email']?></td> 
		<td><a href="admin.php?supprime=<?=$m['id'] ?>">supprimer</a></td> 
		<?php if($m['role']==0) { ?> 
		<td><a class="promouvoir" href="admin.php?role=<?=$m['id'] ?>">promouvoir </a></td>   
	    <?php } ?> 
	</tr>
	<?php } ?>
</table>
</div>



<?php } ?>
</body>
</html>