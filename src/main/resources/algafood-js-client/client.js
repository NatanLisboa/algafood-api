function getRestaurants() {
  $.ajax({
    url: "http://api.algafood.local:8080/restaurants",
    type: "get",

    success: function(response) {
      $("#content").text(JSON.stringify(response));
    }
  });
}

$("#button").click(getRestaurants);