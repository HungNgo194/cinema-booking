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
    private String content;
    private String actor;
    private String director;
    private int age;

    public MovieDTO() {
    }

    public MovieDTO(int movieID, String movieName, String content, String actor, String director, int age) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.content = content;
        this.actor = actor;
        this.director = director;
        this.age = age;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Movie{" + "movieID=" + movieID + ", movieName=" + movieName + ", content=" + content + ", actor=" + actor + ", director=" + director + ", age=" + age + '}';
    }

}
