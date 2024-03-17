<?php 
include_once'functions/auth.php'; 
forcer_utilisateur_connecte();
 ?>
 <!DOCTYPE html>
 <html>
 <head>
 	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<title>Mon Compte</title>
  <link rel="stylesheet" href="style.css">
 </head>
 <body>
 


<div class="profil">
  <?php include_once 'menunav.php'; ?>
<div class="bienvenu">
  <h2>Nous sommes heureux de vous revoir <?php echo $_SESSION['pseudo'];  ?> </h2>
</div>
<div class="stat">
  
            <ul class="info">
               <?php include_once 'database.php';
                   global $db;
                   $req = $db ->prepare("SELECT id FROM rating WHERE id_user=:id_user");
                   $req -> execute([
                    'id_user'=>$_SESSION['id']
                   ]);
                   $donnees = $req -> rowCount();
                   $r = $db ->prepare("SELECT id FROM news WHERE id_user=:id_user");
                   $r -> execute([
                    'id_user'=>$_SESSION['id']
                   ]);
                   $d = $r -> rowCount();
                   $c = $db ->prepare("SELECT id FROM comment WHERE id_user=:id_user");
                   $c -> execute([
                    'id_user'=>$_SESSION['id']
                   ]);
                   $comment = $c -> rowCount();
                ?>
                <li class="carousel-item">
                    <div class="info">
                        <div class="card-content">
                            <p>Votre émail : <?php echo($_SESSION['email'])  ?></p> <br>
                            <p>Nombre de films évalués : <?php echo ($donnees) ?></p> <br>
                            <p>Nombre de news publié : <?php echo ($d) ?></p> <br>
                            <p>Nombre de film commenté : <?= $comment ?> </p> <br>
                            <p>

                              <?php

// préparation de la requete en utilisant une clause inner join pour joindre la table movie avec la table rating en utilisant l'id du film  La requête regroupe ensuite les films par genre et compte le nombre de films pour chaque genre. La clause HAVING permet de filtrer les résultats pour ne conserver que les genres ayant au moins 3 films évalués par l'utilisateur. La requête se termine par un tri des résultats par ordre décroissant de nombre de films, et une limite à un seul résultat (le genre de film préféré). ps: il s'agit de ma requete la plus difficile présente sur le projet en utilisant une requete présente dans les bonus.

$stmt = $db->prepare('
    SELECT genre, COUNT(*) as nb_films
    FROM movie
    INNER JOIN rating ON movie.id = rating.movie_id
    WHERE rating.id_user = ?
    GROUP BY genre
    HAVING nb_films > 2
    ORDER BY nb_films DESC
    LIMIT 1
');

 // execution de la requete préparé en utilisant l'id stocke dans la variable de session
$stmt->execute([$_SESSION['id']]);

// Récupération du résultat
$resultat = $stmt->fetch();


if ($resultat) {
    echo 'Votre genre de film préféré est : ' .$resultat['genre'];
} else {
    echo 'vous n\'avez pour l\'instant aucun genre préféré';
}
?></p>

                        </div>
                    </div>
                </li>
                
            </ul>
        </section>
</div>


</div>


 

 

 </body>
 </html>

