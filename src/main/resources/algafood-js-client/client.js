function getPaymentMethods() {
  $.ajax({
    url: "http://api.algafood.local:8080/payment-methods",
    type: "get",

    success: function(response) {
      fillTable(response);
    }
  });
}

function registerPaymentMethod() {
    var paymentMethodJson = JSON.stringify({
        "description": $("#description-field").val()
    });

    console.log(paymentMethodJson);

    $.ajax({
        url: "http://api.algafood.local:8080/payment-methods",
        type: "post",
        data: paymentMethodJson,
        contentType: "application/json",

        success: function(response) {
            alert("Payment method registered!");
            getPaymentMethods();
        },

        error: function(error) {
            if (error.status == 400) {
                var apiException = JSON.parse(error.responseText);
                alert(apiException.userMessage);
            } else {
                alert("Error when registering payment method!");
            }
        }
    });
}

function removePaymentMethod(paymentMethod) {
  $.ajax({
    url: "http://api.algafood.local:8080/payment-methods/" + paymentMethod.id,
    type: "delete",

    success: function(response) {
      alert("Payment method successfully deleted!")
      getPaymentMethods();
    },

    error: function(error) {
        if (error.status == 409) {
            var apiException = JSON.parse(error.responseText);
            alert(apiException.userMessage);
        } else {
            alert("Error when deleting payment method!");
        }
    }

  });
}

function fillTable(paymentMethods) {
  $("#table tbody tr").remove();

  $.each(paymentMethods, function(i, paymentMethod) {
    var row = $("<tr>");

    var linkAction = $("<a href='#'>")
      .text("Delete")
      .click(function(event) {
        event.preventDefault();
        removePaymentMethod(paymentMethod);
      });

    row.append(
      $("<td>").text(paymentMethod.id),
      $("<td>").text(paymentMethod.description),
      $("<td>").append(linkAction)
    );

    row.appendTo("#table");
  });
}

$("#btn-get").click(getPaymentMethods);
$("#btn-register").click(registerPaymentMethod);