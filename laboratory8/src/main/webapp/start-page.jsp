<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Album"%>
<%@ page import="model.Song"%>
<%@ page import="model.Artist"%>

<html>
<form>
    <head>
        <title> Добро пожаловать </title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false"%>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>

<body style="background-image: url('background.jpg');">
<%
    List<Artist> artists = (List) request.getAttribute("artists");
    List<Album> albums = (List) request.getAttribute("albums");
    List<Song> songs = (List) request.getAttribute("songs");
%>
<div class="container">
    <div class="row" align="center">
        <table class="table_dark">
            <thread>
            <tr>
                <th>ID Artist</th>
                <th>Name</th>
            </tr>
            </thread>
            <body>
                  <% for(Artist artist : artists) { %>
                     <tr>
                        <td style="text-align:center"> <%= artist.getId() %> </td>
                        <td style="text-align:center"> <%= artist.getName() %> </td>
                     <tr>
                  <% } %>
            </body>
        </table>
        <div class="buttons" style="text-align: center;">
            <p>
                <a href="artist" class="button7">Change artists</a>
            </p>
        </div>
    </div>
    <div class="row" align="center">
        <table class = "table_dark">
            <thread>
            <tr>
                <th>Artist Name</th>
                <th>Album Name</th>
                <th>Genre</th>
            </tr>
            </thread>
            <body>
                  <% for(Album album : albums) { %>
                     <tr>
                        <td style="text-align:center"> <%= album.getArtist().getName() %> </td>
                        <td style="text-align:center"> <%= album.getName() %> </td>
                        <td style="text-align:center"> <%= album.getGenre() %> </td>
                     <tr>
                  <% } %>
            </body>
        </table>
        <div class="buttons" style="text-align: center;">
            <p>
                <a href="album" class="button7">Change albums</a>
            </p>
        </div>
    </div>
    <div class="row" align="center">
        <table class= "table_dark">
            <thread>
            <tr>
                <th>Album Name</th>
                <th>Song Name</th>
                <th>Duration</th>
            </tr>
            </thread>
            <body>
                  <% for(Song song : songs) { %>
                     <tr>
                        <td style="text-align:center"> <%= song.getAlbum().getName() %> </td>
                        <td style="text-align:center"> <%= song.getName() %> </td>
                        <td style="text-align:center"> <%= song.getDuration() %> </td>
                     <tr>
                  <% } %>
            </body>
        </table>
        <div class="buttons" style="text-align: center;">
            <p>
                <a href="song" class="button7">Change songs</a>
            </p>
        </div>
    </div>
</div>

</body>
</form>
</html>
