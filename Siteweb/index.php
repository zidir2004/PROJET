<?php 


 ?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Accueil</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<?php include 'menunav.php'; ?>

<header>
	<h1>Les dérnieres sorties :</h1>
</header>
<div class="page-wrap">
<main>
        <section class="carousel">
            <ul class="carousel-items">
               <?php include 'database.php';
                   global $db;
                   $req = $db ->query('SELECT id,url_image,titre,year FROM movie ORDER BY year desc limit 5');
                   while($donnees = $req ->fetch()){
                ?>
                <li class="carousel-item">
                    <div class="card">
                        <h2 class="card-title"><?php echo($donnees['titre']) ?></h2>
                        <img style="width:250px;height:250px;"src=<?php echo($donnees['url_image']) ?> />
                        <div class="card-content">
                            <p>Date de sortie : <?php echo($donnees['year'])  ?></p>
                            <a href="pagefilm.php?film=<?=$donnees['id'] ?>" class="button">Read more</a>
                        </div>
                    </div>
                </li>
                <?php } ?>
            </ul>
        </section>
    
  
</main>
</div>
<?php include_once'footer.php' ?>

</body>
</html>