 <!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title></title>
   <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="inscription">
  <form  method="post">
  <h1>Inscription</h1>
  <label for="pseudo">Pseudo :</label>
  <input type="text" id="pseudo" name="pseudo" required><br>

  <label for="email">Email :</label>
  <input type="email" id="semail" name="semail" required><br>

  <label for="motdepasse">Mot de passe :</label>
  <input type="password" id="password" name="password" required><br>

  <label for="motdepasse">confirmer Mot de passe :</label>
  <input type="password" id="cpassword" name="cpassword" required><br>

  <input type="submit" name="formsend" id="formsend" value="S'inscrire" >
</form>
</div>



<?php


     if (isset($_POST['formsend'])) {
       extract($_POST);

       if (!empty($password) && !empty($cpassword) && !empty($pseudo) && !empty($semail)) {
         if($password == $cpassword){
          
          $hashpass = password_hash($password, PASSWORD_DEFAULT);

          include 'database.php';
          global $db;

          $c = $db ->prepare("SELECT email FROM user WHERE email = :email OR pseudo = :pseudo");
          $c ->execute(['email'=> $semail , 'pseudo'=> $pseudo]);
    
          $result = $c -> rowCount();

          if ($result == 0) {
            $q = $db ->prepare("INSERT INTO user(email,pseudo,password) VALUES(:email,:pseudo,:password)");
            $q->execute([
                'email'=> $semail,
                'pseudo'=> $pseudo,
                'password'=> $hashpass
            ]);
            echo "le compte a été crée";
          }
          else{
            echo "Un Email ou un pseudo existe déja !";
          }
         }
          else{
          echo"les mots de passes ne correpondent pas";
         }
       }
     }

  ?>


</body>
</html>