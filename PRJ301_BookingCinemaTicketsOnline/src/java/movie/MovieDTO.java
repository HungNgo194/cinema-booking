/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class MovieDTO implements Serializable{

    private int movieID;
    private String movieName;
    private String movieContent;
    private String actor;
    private String director;
    private int age;
    private String movieImage;

    public MovieDTO() {
    }

    public MovieDTO(int movieID, String movieName, String movieContent, String actor, String director, int age, String movieImage) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieContent = movieContent;
        this.actor = actor;
        this.director = director;
        this.age = age;
        this.movieImage = movieImage;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieContent() {
        return movieContent;
    }

    public void setMovieContent(String movieContent) {
        this.movieContent = movieContent;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "movieID=" + movieID + ", movieName=" + movieName + ", movieContent=" + movieContent + ", actor=" + actor + ", director=" + director + ", age=" + age + ", movieImage=" + movieImage + '}';
    }
}
