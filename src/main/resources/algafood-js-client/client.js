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
        "description": $("#description-field")
    });

    console.log(paymentMethodJson);

    $.ajax({
        url: "http://api.algafood.local:8080/payment-method",
        type: "post",
        data: paymentMethodJson,
        contentType: "application/json"

        success: function(response) {
            alert("Payment method registered!");
            getPaymentMethods();
        }
    });
}

function fillTable(paymentMethods) {
  $("#table tbody tr").remove();

  $.each(paymentMethods, function(i, paymentMethod) {
    var row = $("<tr>");

    row.append(
      $("<td>").text(paymentMethod.id),
      $("<td>").text(paymentMethod.description)
    );

    row.appendTo("#table");
  });
}

$("#btn-get").click(getPaymentMethods);