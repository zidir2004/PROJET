paypal.Buttons({
    createOrder: function(data,actions) {
        return actions.order.create({
            purchase_units: [{
                amount:{
                    value:'11.0'
                }
            }]
        });
    },
    onApprove: function(data, actions) {
        return actions.order.capture().then(function(details){
            alert('Transaction Completed by'+ details.payer.name.given_name);
        });
    }
}).render('#paypal-button-container');