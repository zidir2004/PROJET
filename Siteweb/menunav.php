<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>test</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <header id="main-header">
    
    <a id="logo-header" href="index.php">
      <span class="site-name"><img src="logo.png"></span>
    </a> <!-- logo-header -->

    <nav>
      <div class="nav-links">
      <ul>
        <li class="search">
            <form action="recherche.php" method="post">
              <input type="text" name="recherche" placeholder="Rechercher">
              <button type="submit" name="envoyer" placeholder="ok"></button>
            </form>
          </li>
                 <li><a href="index.php">Accueil</a></li>
                 <li><a href="Films.php">Films</a></li>
                 <li><a href="MonCompte.php">Mon Compte</a></li>
                 <li> <a href="News.php">News</a></li>
                 <li><a href="Contact.php">Contact</a></li>
                 <li>
            <?php include_once'functions/auth.php' ?>
            <?php
              if (est_connecte()) :?>
             <a href="logout.php">Se déconnecter </a></li>
              <?php endif ?>  
          </li>
          <li>
             <?php
              if (est_connecte() AND $_SESSION['role']==1):?>
            <a href="admin.php">Administration</a>
            <?php endif ?> 
          </li>
          
      </ul>
    </div>
    
    </nav>
    <img src="menu-btn.png" class="menu-hamburger">

  </header>

<script>
        const menuHamburger = document.querySelector(".menu-hamburger")
        const navLinks = document.querySelector(".nav-links")
 
        menuHamburger.addEventListener('click',()=>{
        navLinks.classList.toggle('mobile-menu')
        })
</script>

</body>
</html>