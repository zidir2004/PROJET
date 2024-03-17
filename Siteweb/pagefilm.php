<?php 
session_start();
 ?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Détails du film</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>

	<?php 
include_once'menunav.php';
include_once'database.php';
global $db;




if(isset($_GET['film']) AND !empty($_GET['film'])){
	$film = (int) $_GET['film'];

	$req = $db ->prepare("select * FROM movie WHERE id= ?");
	$req->execute(array($film));
	$donnees=$req->fetch();
	extract($donnees);
}
?>

 <main>
        <section class="carousel">
            <ul class="carousel-items">
                <li class="carousel-item">
                    <div class="cards">
                        <h2 class="card-titles"><?php echo($donnees['titre']) ?></h2><br>
                        <img style="width:250px;height:250px;"src=<?php echo($donnees['url_image']) ?> />
                        <div class="cards_description">
                        	<h2>Description :</h2> <br>
                        	<p><?php echo ($donnees['description'])?></p> <br>
                        	<p> <strong> Date de sortie : </strong> <?php echo($donnees['year'])  ?></p>
                            <p> <strong> Genre : </strong> <?php echo($donnees['genre'])  ?></p> 
                            <p> <strong> Réalisateur : </strong><?php $req = $db ->prepare("select name FROM realisateur WHERE id=:id");
                              $req->execute([
                              'id'=>$donnees['id_realisateur']
                                             ]);
                               $r = $req -> fetch();
                               extract($r);
                                echo ("$name"); ?>
                        </p>
                            <p> <strong>  rating : </strong> <?php 
                             $n =$db ->prepare("select AVG(value)as value from rating where movie_id=:movie_id");
                             $n -> execute([
                             'movie_id'=>$donnees['id']
                             ]);
                             $m = $n->fetch();
                             echo($m['value']);
                        ?>  </p> <br>
                             <?php 
                              include_once'functions/auth.php';
                              
                              if(!est_connecte()){
                              exit();
                              }
                              else{
                                 if (isset($_POST['formrating'])) {
                                  extract($_POST);
                                  $q = $db ->prepare("INSERT INTO rating(value,movie_id,id_user) VALUES(:value,:movie_id,:id_user)");
                                  $q->execute([
                                  'value'=> $rating,
                                  'movie_id'=> $donnees['id'],
                                  'id_user'=> $_SESSION['id']
                                     ]);
                              }
                              $req = $db -> prepare("select id from rating where movie_id=:movie_id and id_user =:id_user");
                              $req -> execute([
                              'movie_id'=>$donnees['id'],
                              'id_user' => $_SESSION['id']
                              ]);
                             


                              $res = $req -> rowCount();
                              if ($res==0) {?>
                              <form method="post">
                              	<input type="number" step="1"  min="0" max="5" name="rating" placeholder="Note du Film">
                              	<input type="submit" name="formrating" id="formrating" value="Noter">
                              </form>
                              <?php 
                              
                              ?>

                             <?php }else{
                              echo("<strong> impossible de noter a nouveau le film ,tu l'as déja noté </strong> ");
                              }
                          }
                              	?>

                            
                        </div>
                    </div>
                </li>
            </ul>
        </section>
    </main>

<div class="commentaire">
<?php
if (isset($_POST['envoyer'])) {
extract($_POST);
$q = $db ->prepare("INSERT INTO comment(comment,id_movie,id_user) VALUES(:comment,:id_movie,:id_user)");
$q->execute([
'comment'=> $comment,
'id_movie'=> $donnees['id'],
'id_user'=> $_SESSION['id']
]);
}


 $r = $db -> prepare("select id from comment where id_movie=:id_movie and id_user=:id_user");
                              $r-> execute([
                              'id_movie'=>$donnees['id'],
                              'id_user' => $_SESSION['id']
                              ]);
                              $count = $r -> rowCount();
                                if ($count==0) {
?>
<form  method="post" class="commentform">
    <input type="text" name="comment" placeholder="votre commentaire">
    <input type="submit" name="envoyer">
</form>
<?php } ?>
<br> <h2>Commentaires</h2><br>
  <ul class="commentaires">
<?php
//requete php un peu compliqué avec une petite jointure entre les tables comment et user
$c = $db -> prepare("select pseudo,comment from comment,user where comment.id_user = user.id AND comment.id_movie =:id_movie limit 7");
                              $c-> execute([
                              'id_movie'=>$donnees['id']
                              ]);
                            while($comment = $c ->fetch()){
?>
    <li>
      <h4>Pseudo : <?php 
      echo($comment['pseudo']) ;?></h4>
      <p><?= $comment['comment'] ?></p>
    </li>
<?php } ?>
</div>

<?php include_once'footer.php' ?>

</body>
</html>