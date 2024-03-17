<?php
session_start();
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Ajout News</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<?php 

if($_SESSION['role']==1){
include 'menunav.php';  
include 'navadmin.php';

?>

<div class="filmform">
<form  method="post">
<h1>Ajouter Une News</h1>  <br>

  <label for="titre">titre :</label>
  <input type="text" id="titre" name="titre" required><br>

  <label for="text">text : </label>
  <input type="text" id="text" name="text" required><br>

  <label for="url_image">url image : </label>
  <input type="text" id="url_image" name="url_image" required><br>

  <input type="submit" name="formsend" id="formsend" value="Ajouter La News">
</form>

<?php


     if (isset($_POST['formsend'])) {
       extract($_POST);

          include 'database.php';
          global $db;

          
            $q = $db ->prepare("INSERT INTO news(titre,text,id_user,url_image) VALUES(:titre,:text,:id_user,:url_image)");
            $q->execute([
                'titre'=> $titre,
                'text'=> $text,
                'id_user'=>$_SESSION['id'],
                'url_image'=>$url_image
            ]);
            echo "La News a bien été ajouté";
         
       }
  ?>

</div>





<?php
	}
?>

</body>
</html>