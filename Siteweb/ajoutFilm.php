<?php 
session_start();
 ?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>AjoutFilm</title>
	<link rel="stylesheet" href="style.css">
</head>
</head>
<body>

<?php 

if($_SESSION['role']==1){
include 'menunav.php';  
include 'navadmin.php';

?>

<div class="filmform">
<form  method="post">
<h1>Ajouter Un nouveau Film</h1>  <br>

  <label for="titre">Titre :</label>
  <input type="text" id="titre" name="titre" required><br>

  <label for="year">Année : </label>
  <input type="text" id="year" name="year" required><br>

  <label for="genre">Genre :</label>
  <input type="text" id="genre" name="genre" required><br>

  <label for="description">Description :</label>
  <input type="text" id="description" name="description" required><br>

  
  	<?php
    function connexion_requete($req)
{
			// ouverture de connexion
	$db = new mysqli("localhost", "root", "", "siteweb");
	$db->set_charset('utf8');
	if($db->connect_errno){
		die("erreur $db->connect_error");
	}
	else
	{
	$data = $db->query($req);
	return $data;
	}
}
    $q = "select name,id from realisateur";
    $d = connexion_requete($q);
    echo "<label for='realisateur'> realisateur :</label>";
    echo"<select name='realisateur'>";
    while($row = $d ->fetch_assoc()){
        $rea = $row["name"];
        $id =$row["id"];
        echo"<option value= $id> $rea </option> <br>";
    }
echo"</select> </p>";
?>
  <label for="image">Lien de l'Image :</label>
  <input type="text" id="url_image" name="url_image" required><br>

  <input type="submit" name="formsend" id="formsend" value="Ajouter Le Film">
</form>

<?php


     if (isset($_POST['formsend'])) {
       extract($_POST);

          include 'database.php';
          global $db;

          $c = $db ->prepare("SELECT titre FROM movie WHERE titre = :titre AND year = :year");
          $c ->execute(['titre'=>$titre, 'year'=> $year]);
    
          $result = $c -> rowCount();

          if ($result == 0) {
            $q = $db ->prepare("INSERT INTO movie(titre,year,genre,id_realisateur,description,url_image,id_realisateur_a_realise) VALUES(:titre,:year,:genre,:id_realisateur,:description,:url_image,:id_realisateur_a_realise)");
            $q->execute([
                'titre'=> $titre,
                'year'=> $year,
                'genre'=> $genre,
                'id_realisateur'=> $realisateur,
                'description'=> $description,
                'url_image'=> $url_image,
                'id_realisateur_a_realise'=> $realisateur
            ]);
            echo "Le film a bien été Ajouté";
          }
          else{
            echo "Le film est déja présent sur le site !";
          }
         
         
       }
     

  ?>

</div>





<?php
	}
?>


</body>
</html>