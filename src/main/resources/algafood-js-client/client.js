function getRestaurants() {
  $.ajax({
    url: "http://api.algafood.local:8080/restaurants",
    type: "get",

    success: function(response) {
      $("#content").text(response);
    }
  });
}

$("#button").click(getRestaurants);