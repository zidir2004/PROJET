<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Films</title>
</head>
<body>
<?php include 'menunav.php'; ?>
<div class="page-wrap">
  <div class="movies">
  <ul>
    <?php include_once 'database.php';
                   global $db;
                   $req = $db ->query('SELECT id,url_image,titre,year,description FROM movie ');
                   while($donnees = $req ->fetch()){
                ?>
    <li>
      <img src=<?php echo($donnees['url_image']) ?> alt="Movie 1">
      <h3><?php echo($donnees['titre']) ?></h3>
      
      <a href="pagefilm.php?film=<?=$donnees['id']?>">View Details</a>
    </li>
     <?php } ?>
  </ul>
</div>
</div>  

<?php include'footer.php' ?> 
</body>
</html>