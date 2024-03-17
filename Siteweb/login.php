
<?php 

 ?>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title></title>
   <link rel="stylesheet" href="style.css">
</head>
<body>


<div class="connexion">
  
<form  method="post">
<h1>Conexion</h1>  

  <label for="email">Email :</label>
  <input type="email" id="lemail" name="lemail" required><br>

  <label for="motdepasse">Mot de passe : </label>
  <input type="password" id="lpassword" name="lpassword" required><br>
   
  <label for="showpassword">Show Password</label>  
  <input type="checkbox" onclick="myFunction()"> <br>
  <input type="submit" name="formlogin" id="formlogin" value="Se conecter">
</form>

</div>

<script>
function myFunction() {
  var x = document.getElementById("lpassword");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>

<?php
  include_once'functions/auth.php' ;
if(est_connecte()){
header('Location:MonCompte.php');
exit();
}


     if (isset($_POST['formlogin'])) {
       extract($_POST);
      
      include 'database.php';
          global $db;
       if (!empty($lpassword) && !empty($lemail)) {
        $q = $db -> prepare("SELECT password FROM user WHERE email = :email");
        $q -> execute(['email'=>$lemail]);
        $result = $q ->fetch();

        $s = $db -> prepare("SELECT id,role,pseudo FROM user WHERE email =:email");
        $s -> execute(['email'=>$lemail]);
        $res = $s ->fetch();
        extract($res);

        if($result == true){
            if(password_verify($lpassword ,$result['password'])){
              echo"conexion reussi";
              $_SESSION['connecte']=1; 
              $_SESSION['email']=$lemail;
              $_SESSION['role'] = $role;
              $_SESSION['id']=$id;
               $_SESSION['pseudo'] = $pseudo;
              header('Location:MonCompte.php');
              exit();
            }
            else{
              echo"le mot de passe est incorect";
            }
        }
        else{
          echo"le compte portant l'email $lemail n'existe pas";
        }
       }

       else{
        echo"veuillez completer l'ensemble des champs";
       }
     }




  ?>


</body>
</html>