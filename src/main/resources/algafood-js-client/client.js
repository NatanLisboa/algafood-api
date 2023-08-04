function getRestaurants() {
  $.ajax({
    url: "http://api.algafood.local:8080/restaurants",
    type: "get",

    success: function(response) {
      $("#content").text(JSON.stringify(response));
    }
  });
}

function closeRestaurant() {
  $.ajax({
    url: "http://api.algafood.local:8080/restaurants/1/closure",
    type: "put",

    success: function(response) {
      alert("The restaurant was closed!")
    }
  });
}

$("#button").click(getRestaurants);