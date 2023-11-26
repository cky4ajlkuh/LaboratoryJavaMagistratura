package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 1)
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(generator = "album_id_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs;

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        song.setAlbum(this);
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Album: " +
                "\n Name: " + name +
                "\n Genre: " + genre;
    }
}
