<?php
session_start();
  ?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ajouter un Réalisateur</title>
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
<h1>Ajouter Un Réalisateur</h1>  <br>

  <label for="name">Nom :</label>
  <input type="text" id="name" name="name" required><br>

  <label for="birth_year">Année de naissance : </label>
  <input type="text" id="birth_year" name="birth_year" required><br>

  <input type="submit" name="formsend" id="formsend" value="Ajouter Le Réalisateur">
</form>

<?php


     if (isset($_POST['formsend'])) {
       extract($_POST);

          include 'database.php';
          global $db;

          $c = $db ->prepare("SELECT name FROM realisateur WHERE name =:name AND birth_year=:birth_year");
          $c ->execute(['name'=>$name, 'birth_year'=> $birth_year]);
    
          $result = $c -> rowCount();

          if ($result == 0) {
            $q = $db ->prepare("INSERT INTO realisateur(birth_year,name) VALUES(:birth_year,:name)");
            $q->execute([
                'name'=> $name,
                'birth_year'=> $birth_year
            ]);
            echo "Le réalisateur a bien été Ajouté";
          }
          else{
            echo "Le réalisateur est déja présent sur le site !";
          }
       }
  ?>

</div>





<?php
	}
?>

</body>
</html>