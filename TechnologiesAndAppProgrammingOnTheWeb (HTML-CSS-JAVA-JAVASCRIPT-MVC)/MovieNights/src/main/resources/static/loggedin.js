var movie_details;
var input = document.querySelector("#movie-field");
var timeout = null;
input.addEventListener("keyup", function (e) {
  clearTimeout(timeout);

  timeout = setTimeout(function () {
    let url = "http://www.omdbapi.com/?apikey=191c1a26&r=json&t=";
    if (input.value != "") {
      let title = "";
      input.value = input.value.trim();

      let count = 0;
      for (let i = 0; i < input.value.length; i++) {
        if (input.value[i] == " ") {
          count++;
        } else {
          title = title.concat(input.value[i]);
          count = 0;
        }

        if (count == 1) title = title.concat("+");
      } //end of for

      url = url.concat(title);

      console.log("original title: " + input.value);
      console.log("modified title: " + title);
      console.log("url: " + url);

      fetch(url)
        .then(function (response) {
          if (response.status !== 200) {
            console.log(
              "Looks like there was a problem. Status Code: " + response.status
            );
            return;
          }

          //obtaiting the neccessary data from the json object
          response.json().then(function (data) {
            movie_details = {
              actors: data.Actors, //used
              country: data.Country, //used
              director: data.Director, //used
              genre: data.Genre, //used
              plot: data.Plot, //used
              poster: data.Poster, //used
              rated: data.Rated, //used
              released: data.Released, //used
              runtime: data.Runtime, //used
              title: data.Title, //used
              writer: data.Writer, //used
              year: data.Year, //used
              imdbRating: data.imdbRating, //used
              imdbVotes: data.imdbVotes, //used
            };

            console.log(
              "Actors: " +
                movie_details.actors +
                "\nCountry: " +
                movie_details.country +
                "\nDirector: " +
                movie_details.director +
                "\nGenre: " +
                movie_details.genre +
                "\nPlot: " +
                movie_details.plot +
                "\nPoster: " +
                movie_details.poster +
                "\nRated: " +
                movie_details.rated +
                "\nReleased: " +
                movie_details.released +
                "\nRuntime: " +
                movie_details.runtime +
                "\nTitle: " +
                movie_details.title +
                "\nYear: " +
                movie_details.year +
                "\nIMDB Rating: " +
                movie_details.imdbRating +
                "\nIMDB Votes: " +
                movie_details.imdbVotes
            );

            //removing the h1 element to create more space for the metadata of the movie the user asked for
            let deleteMSG = document.querySelector("#user-msg");
            deleteMSG.textContent = "";
            deleteMSG.style.paddingBottom = "0px";

            let movieInfo = document.querySelector(".movie-details");
            let poster = document.querySelector(".poster");
            let info = document.querySelector(".details");

            poster.innerHTML =
              "<p id=posterInfo>" +
              movie_details.rated +
              " | " +
              movie_details.runtime +
              " | " +
              movie_details.genre +
              " | " +
              movie_details.released +
              " (" +
              movie_details.country +
              ")" +
              "</p>" +
              "<img src=" +
              movie_details.poster +
              " alt=" +
              movie_details.title +
              " width=400px height=400px />";

            //p element for info which are placed over poster
            let extraInfo = document.querySelector("#posterInfo");
            extraInfo.style.fontWeight = "bold";
            extraInfo.style.width = "400px";
            extraInfo.style.paddingBottom = "5px";
            extraInfo.style.textAlign = "center";

            //metadata
            info.innerHTML =
              "<div class=title_rating>" +
              "<div class=title_year>" +
              "<div id=title>" +
              movie_details.title +
              "</div>" +
              "<div id=year>" +
              " (" +
              movie_details.year +
              ")" +
              "</div>" +
              "</div>" +
              "<div class=rating_votes>" +
              "<div id=rating_box>" +
              "<div id=ratingIcon>" +
              "<img src='./icons/starIcon.png' width=32px height=32px/>" +
              "</div>" +
              "<div id=rated>" +
              "<div id=number>" +
              movie_details.imdbRating +
              "<sub>" +
              "/10" +
              "</sub>" +
              "</div>" +
              "<div id=votes>" +
              movie_details.imdbVotes +
              "</div>" +
              "</div>" +
              "</div>" +
              "</div>" +
              "</div>" +
              "<div id=metadata>" +
              "<ul class=metadataList>" +
              "<li id=plot>" +
              movie_details.plot +
              "</li>" +
              "<li id=btnItem>" +
              "<button type=button class=btnMore onclick='onButtonClickMore()'>" +
              "<p>" +
              "More" +
              "</p>" +
              "</button>" +
              "</li>" +
              "</ul>" +
              "</div>";

            //title_rating
            let title_rating = document.querySelector(".title_rating");
            title_rating.style.display = "flex";
            title_rating.style.flexDirection = "row";
            title_rating.style.justifyContent = "center";
            title_rating.style.marginBottom = "20px";

            //title_year
            let title_year = document.querySelector(".title_year");
            title_year.style.display = "flex";
            title_year.style.flexDirection = "row";
            title_year.style.fontWeight = "bold";
            title_year.style.fontSize = "32px";

            //title
            let title = document.querySelector("#title");
            title.style.paddingRight = "10px";

            //rating
            let rating_votes = document.querySelector(".rating_votes");
            rating_votes.style.paddingLeft = "20px";

            //rating box
            let rating = document.querySelector("#rating_box");
            rating.style.display = "flex";
            rating.style.flexDirection = "row";

            //rated
            let rated = document.querySelector("#rated");
            rated.style.margin = "auto";

            //imdb rating
            let number = document.querySelector("#number");
            number.style.fontWeight = "bold";
            number.style.fontSize = "20px";

            //imdb votes
            let votes = document.querySelector("#votes");
            votes.style.fontSize = "12px";

            //sub
            let sub = document.querySelector("sub");
            sub.style.fontWeight = "normal";
            sub.style.fontSize = "initial";

            //list element which contains the metadata
            let infoList = document.querySelector(".metadataList");
            infoList.style.listStyleType = "none";

            //li element for plot
            let infoPlot = document.querySelector("#plot");
            infoPlot.style.marginBottom = "5px";

            //btnItem
            let btnItem = document.querySelector("#btnItem");
            btnItem.style.display = "flex";
            btnItem.style.justifyContent = "center";

            //btnMore
            let btnMore = document.querySelector(".btnMore");
            btnMore.style.width = "60px";
            btnMore.style.height = "30px";
            btnMore.style.marginTop = "10px";
            btnMore.style.border = "none";
            btnMore.style.borderRadius = "10px";
            btnMore.style.backgroundColor = "#fbd754";
            btnMore.style.cursor = "pointer";
            btnMore.style.fontWeight = "bold";
            btnMore.style.fontSize = "14px";

            console.log(data);
          });
        })
        .catch(function (err) {
          console.log("Fetch Error :-S", err);
        });
    } //end of if
  }, 1500);
});

//function for saving a bookmark in server
function saveMovie() {
  saveMovieRequest("http://localhost:8080/save_movie", movie_details.title);
}//end of function saveMovie

function saveMovieRequest(url, title) {

   let request = new XMLHttpRequest();
    console.log(movie_details.title);

   request.onload = function () {
      if (this.readyState == 4 && this.status == 200) {
         console.log("Movie saved");
      } else {
         console.log("Something went wrong");
      }
   };

   request.onerror = function () {
      console.log("ERROR");
   };

   request.open("POST", url);
   request.setRequestHeader("Content-Type","text/plain");
   request.send(title);

}//end of function saveMovieRequest

//function for showing more details about the movie
function onButtonClickMore() {
  let metadata = document.querySelector("#metadata");
  let metadataList = document.querySelector(".metadataList");
  let btnSave = document.querySelector(".btnMore");

  btnSave.parentNode.removeChild(btnSave);
  metadataList.innerHTML +=
    "<li>Directed by: " +
        movie_details.director +
    "</li>" +
    "<li>Stars: " +
        movie_details.actors +
    "</li>";

  metadata.innerHTML +=
    "<button type=button class=btnSave onclick=saveMovie()>" +
        "<div id=plusIcon>" +
            "<img src='./icons/plusIcon.png' width=32px height=32px />" +
        "</div>" +
        "<div id=add>" +
            "<p>" +
                "Add to Bookmarks" +
            "</p>" +
        "</div>" +
    "</button>";

  //buttonSave
  let buttonSave = document.querySelector(".btnSave");
  buttonSave.style.width = "250px";
  buttonSave.style.height = "50px";
  buttonSave.style.marginTop = "20px";
  buttonSave.style.display = "flex";
  buttonSave.style.flexDirection = "row";
  buttonSave.style.justifyContent = "center";
  buttonSave.style.borderRadius = "10px";
  buttonSave.style.border = "none";
  buttonSave.style.backgroundColor = "#fbd754";
  buttonSave.style.cursor = "pointer";

  //plusIcon
  let plusIcon = document.querySelector("#plusIcon");
  plusIcon.style.paddingLeft = "20px";

  //addText
  let add = document.querySelector("#add");
  add.style.fontWeight = "bold";
  add.style.fontSize = "16px";
  add.style.margin = "auto";
}//end of function onButtonClickMore