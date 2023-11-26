<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Album"%>
<html>
    <head>
        <title> Albums </title>

	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false"%>
        <link rel="stylesheet" type="text/css" href="style.css"/>
            <style>
            .text-field {
              margin-bottom: 1rem;
              border: 3px
            }

            .text-field__label {
              border: 3px
              display: block;
              margin-bottom: 0.25rem;
              margin: 1px 1px 1px 1px;
            }

            .text-field__input {
              margin: 2px 2px 2px 2px;
              display: block;
              width: 20%;
              height: calc(2.25rem + 2px);
              padding: 0.375rem 0.75rem;
              font-family: inherit;
              font-size: 1rem;
              font-weight: 100;
              line-height: 1.5;
              color: #212529;
              background-color: #fff;
              background-clip: padding-box;
              border: 1px solid #bdbdbd;
              border-radius: 0.25rem;
              transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            button.button7 {
              font-weight: 700;
              color: white;
              text-decoration: none;
              padding: .8em 1em calc(.8em + 3px);
              border-radius: 3px;
              background: rgb(64,199,129);
              box-shadow: 0 -3px rgb(53,167,110) inset;
              transition: 0.2s;
            }
            button.button7:hover { background: rgb(53, 167, 110); }
            button.button7:active {
              background: rgb(33,147,90);
              box-shadow: 0 3px rgb(33,147,90) inset;
            }
            .row {
            	white-space: normal;
            	display: inline-block;
            	width: 50%;
            	vertical-align: top;
            	margin-right: 5%;
            	font-size: 14px;
            }
            .form-group {
              display: flex;
              flex-direction: row;
              justify-content: center;
              align-items: center;
              margin: 3px 3px 0 0;
              width: 90%;
              height: 7%;
            }
            label {
                font: 14pt Impact;
                color: black;
            }
            </style>
            <script>
                var insertArtistsNames;
                var updateArtistsNames;

				function setInsertArtists(input) {
					let result = "";
					getInsertNames(input);
					for (var i = 0; i < insertArtistsNames.length; i++) {
						result += "<option>" + insertArtistsNames[i] + "</option>";
					}
					document.getElementById("insertElements").innerHTML = result;
				}

                function getInsertNames(input) {
                    if (input != "") {
                	    $.ajax({
                            type: "GET",
                            url: "autofill?autofillArtists=" + input,
                            async: false,
                            dataType: "json",
                            success: function (data) {
                                insertArtistsNames = data;
                            }
    				    });
    				}
                }

				function setUpdateArtists(input) {
					let result = "";
					getUpdateNames(input);
					for (var i = 0; i < updateArtistsNames.length; i++) {
						result += "<option>" + updateArtistsNames[i] + "</option>";
					}
					document.getElementById("updateElements").innerHTML = result;
				}

                function getUpdateNames(input) {
                    if (input != "") {
                	    $.ajax({
                            type: "GET",
                            url: "autofill?autofillArtists=" + input,
                            async: false,
                            dataType: "json",
                            success: function (data) {
                                updateArtistsNames = data;
                            }
    				    });
    				}
                }
            </script>
    </head>
<body style="background-image: url('background.jpg');">
<%
    List<Album> albums = (List) request.getAttribute("albums");
%>
    <div class="container">
        <div class="row" align="center">
            <table class="table_dark">
                <thread>
                    <tr>
                        <th>ID Album</th>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Artist</th>
                    </tr>
                </thread>
                <body>
                    <% for(Album album : albums) { %>
                        <tr>
                            <td style="text-align:center"> <%= album.getId() %> </td>
                            <td style="text-align:center"> <%= album.getName() %> </td>
                            <td style="text-align:center"> <%= album.getGenre() %> </td>
                            <td style="text-align:center"> <%= album.getArtist().getName() %> </td>
                        <tr>
                    <% } %>
                </body>
            </table>
        </div>
        <div class="row">
            <form method="post" action="album">
                <div class="text-field">
                        <div class ="form-group">
                            <label>Delete album with Name:</label>
                            <input type="text" name="deleteName" class="text-field__input" placeholder="name">
                        </div>
                        <div class ="form-group">
                            <label>Update album with Name:</label>
                            <input type="text" name="updateName" class="text-field__input" placeholder="name">
                        </div>
                        <div class ="form-group">
                            <label>New name:</label>
                            <input type="text" name="newName" class="text-field__input" placeholder="New name">
                            <label>New Genre:</label>
                            <input type="text" name="updateGenre" class="text-field__input" placeholder="New Genre">
                            <label>Other Artist:</label>
                            <input list="listUpdateArtists" type="text" id="updateArtist" name="updateArtist" class="text-field__input" placeholder="Other Artist">
                            <script>
							    var input1 = document.getElementById("updateArtist");
								input1.oninput = function () {
								    setUpdateArtists(input1.value);
								}
							</script>
                            <datalist id="listUpdateArtists">
                                <div id="updateElements"></div>
                            </datalist>
                        </div>
                        <div class ="form-group">
                            <label>Insert album:</label>
                            <input type="text" name="insertName" class="text-field__input" placeholder="Name">
                            <input type="text" name="insertGenre" class="text-field__input" placeholder="Genre">
                            <input list="listInsertArtists" type="text" name="insertArtist" id="insertArtist" class="text-field__input" placeholder="Artist">
                            <script>
							    var input2 = document.getElementById("insertArtist");
								input2.oninput = function () {
								    setInsertArtists(input2.value);
								}
							</script>
                            <datalist id="listInsertArtists">
                                <div id="insertElements"></div>
                            </datalist>
                        </div>
                    <p align="center">
                        <button class="button7" type="submit">Save changes</button>
                        <a href="/" class="button7">Back</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
