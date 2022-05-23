function getBookmarks() {
    bookmarksRequest("http://localhost:8080/my_bookmarks");
}//end of function getBookmarks

let bookmarksList;
let count;

function bookmarksRequest(url) {

    let request = new XMLHttpRequest();

    request.onload = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("Bookmarks received");
            console.log(this.responseText);

            bookmarksList = JSON.parse(this.responseText);
            count = 0;
            if (bookmarksList.length > 0) {
                showBookmarks(bookmarksList[0]);
            } else {
                showMsg();
            }

        } else {
            console.log("Something went wrong");
            console.log(this.responseText);
        }//end of if/else
    };

    request.onerror = function () {
        console.log("ERROR");
        console.log(this.responseText);
    };

    request.open("GET", url);
    request.setRequestHeader("Accept","application/json");
    request.send();

}//end of function bookmarksRequest

let movie_details;

function showBookmarks(bookmark) {
    console.log("Inside showBookmarks");
    console.log(bookmark);

    let url = "http://www.omdbapi.com/?apikey=191c1a26&r=json&t=";

    let tmpTitle = bookmark.replace(" ", "+");
    url = url.concat(tmpTitle);

    let msg = document.querySelector(".bookmarksMsg");
    msg.innerHTML = "";

    let buttonBox = document.querySelector(".next_prev_Btn");
    buttonBox.innerHTML =
        "<div class=prevBtn>" +
            "<button type=button class=movie_btn onclick=prevBookmark()>" +
                "previous" +
            "</button>" +
        "</div>" +
        "<div class=nextBtn>" +
            "<button type=button class=movie_btn onclick=nextBookmark()>" +
                "next" +
            "</button>" +
        "</div>";

    let prevBtn = document.querySelector(".prevBtn");
    prevBtn.style.paddingRight = "25px";

    let nextBtn = document.querySelector(".nextBtn");
    nextBtn.style.paddingLeft = "25px";

    let movie_btn = document.querySelectorAll(".movie_btn");
    for (let i = 0; i < movie_btn.length; i++) {
        movie_btn[i].style.cursor = "pointer";
        movie_btn[i].style.padding = "10px";
        movie_btn[i].style.textTransform = "uppercase";
        movie_btn[i].style.border = "none";
        movie_btn[i].style.borderRadius = "10px";
        movie_btn[i].style.background = "#fbd754";
        movie_btn[i].style.fontWeight = "bold";
    }

    console.log("TITLE: " + tmpTitle);
    console.log(url);

    fetch(url)
        .then(function (response) {
            if (response.status !== 200) {
                console.log(
                    "Looks like there was a problem. Status Code: " + response.status
                    );
                return;
            }//end of if

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
                    "<img src=" + movie_details.poster + " alt=" + movie_details.title + " width=400px height=400px />";

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
                            "<li>Directed by: " +
                                movie_details.director +
                            "</li>" +
                            "<li>Stars: " +
                                movie_details.actors +
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

                console.log(data);

            });
        })
        .catch(function (err) {
            console.log("Fetch Error :-S", err);
        });

}//end of function showBookmarks

function nextBookmark() {

    if (count+1 <= bookmarksList.length-1) {
        console.log("count was: " + count);
        count++;
        console.log("count is: " + count);

        let url = "http://www.omdbapi.com/?apikey=191c1a26&r=json&t=";
        let tmpTitle = bookmarksList[count].replace(" ", "+");
        url = url.concat(tmpTitle);

        console.log("TITLE: " + tmpTitle);
        console.log(url);

        fetch(url)
            .then(function (response) {
                if (response.status !== 200) {
                    console.log(
                        "Looks like there was a problem. Status Code: " + response.status
                    );
                    return;
                }//end of if

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
                        "<img src=" + movie_details.poster + " alt=" + movie_details.title + " width=400px height=400px />";

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
                                "<li>Directed by: " +
                                    movie_details.director +
                                "</li>" +
                                "<li>Stars: " +
                                    movie_details.actors +
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

                    console.log(data);

                });
            })
            .catch(function (err) {
                console.log("Fetch Error :-S", err);
            });

    }//end of if

}//end of function nextBookmark

function prevBookmark() {

    if (count-1 >= 0) {
        console.log("count was: " + count);
        count--;
        console.log("count is: " + count);

        let url = "http://www.omdbapi.com/?apikey=191c1a26&r=json&t=";
        let tmpTitle = bookmarksList[count].replace(" ", "+");
        url = url.concat(tmpTitle);

        console.log("TITLE: " + tmpTitle);
        console.log(url);

        fetch(url)
            .then(function (response) {
                if (response.status !== 200) {
                    console.log(
                        "Looks like there was a problem. Status Code: " + response.status
                    );
                    return;
                }//end of if

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
                        "<img src=" + movie_details.poster + " alt=" + movie_details.title + " width=400px height=400px />";

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
                                "<li>Directed by: " +
                                    movie_details.director +
                                "</li>" +
                                "<li>Stars: " +
                                    movie_details.actors +
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

                    console.log(data);

                });
            })
            .catch(function (err) {
                console.log("Fetch Error :-S", err);
            });

     }//end of if

}//end of function prevBookmark

function showMsg() {

    let buttonBox = document.querySelector(".next_prev_Btn");
    buttonBox.innerHTML = "";

    let msg = document.querySelector(".bookmarksMsg");
    msg.innerHTML = "Bookmarks list is empty";
    msg.style.fontSize = "22px";
    msg.style.fontWeight = "bold";
    msg.style.textAlign = "center";
    msg.style.letterSpacing = "2px";
    msg.style.wordSpacing = "4px";

}//end of function showMsg