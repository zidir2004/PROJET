<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="style.css">
	<title>Contact</title>
</head>
<body>
<?php include 'menunav.php'; ?>

<div class="page-wrap">
<div class="compteur">
    <?php
include'functions/compteur.php';
ajouter_vue();
$vues = nombre_vues();
  ?>
    il y'a eu <?= $vues ?> vues total

</div>
<div class="donate">
    <p>Cher utilisateur, notre site a besoin de votre soutien pour continuer à fournir des contenus de qualité gratuitement. Les dons que nous recevons nous aident à payer les frais d'hébergement, à rémunérer nos auteurs et à améliorer l'expérience utilisateur. Chaque contribution compte et nous sommes reconnaissants pour tout montant que vous pourrez donner. Merci d'avance pour votre générosité.</p>
    <form action="#" method="post">
  <input type="number" step="0.5" min="0" name="montant" placeholder="Montant du don">
  <button type="submit">Donner</button>
</form>

</div>

<div class="payment">
    <div id="paypal-button-container"></div>    
 
<script src="https://www.paypal.com/sdk/js?client-id=Acz5lfvXoPXX08mljWXGqIxoH6N4B8NN11AZWlU6uWVKu5OKaQy89hJYfSlD3oPf3RmA44FZYq3w2at7&currency=EUR"></script>
<script >paypal.Buttons({
    createOrder: function(data,actions) {
        return actions.order.create({
            purchase_units: [{
                amount:{
                    value:'10.0'
                }
            }]
        });
    },
    onApprove: function(data, actions) {
        return actions.order.capture().then(function(details){
            alert('Transaction Completed by'+ details.payer.name.given_name);
        });
    }
}).render('#paypal-button-container');</script>

</div>
   

</div>
<?php include 'footer.php'; ?>
</body>
</html>