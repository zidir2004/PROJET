<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>News</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<?php include 'menunav.php'; ?>
<div class="page-wrap">
 <main>
        <section class="carousel">
        	 <?php include 'database.php';
                   global $db;
                   $req = $db ->query('SELECT url_image,titre,text,id_user FROM news limit 4');
                   while($donnees = $req ->fetch()){
                ?>
            <ul class="carousel-items">
                <li class="carousel-item">
                    <div class="card">
                        <h2 class="card-title"><?php echo($donnees['titre']) ?></h2> <br>
                        <img style="width:250px;height:250px;"src=<?php echo($donnees['url_image']) ?> /> <br>
                        <div class="card-content">
                            <p> <?php echo($donnees['text'])  ?></p> <br>
                            <p><strong> article publié par : <?php $r = $db ->prepare('SELECT pseudo FROM user where id=:id');  
                             $r->execute([
                              'id'=>$donnees['id_user']
                             ]);
                             $p=$r->fetch();
                             echo($p['pseudo']);
                        ?></strong> </p>
                        </div>
                    </div>
                </li>  
            </ul>
            <?php } ?>
        </section>
    </main>
</div>


<?php include_once'footer.php' ?>
</body>
</html>