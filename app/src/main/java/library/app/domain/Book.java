package library.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String isbn;

  private String name;

  private String genre;

  private String description;

  private String author;

  public Book(){
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJenre() {
    return genre;
  }

  public void setJenre(String genre) {
    this.genre = genre;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String to_String(){
    return "{ "+
        "ID: " + getId() +
        " ,ISBN: " + getIsbn() +
        " ,ISBN: " + getIsbn() +
        " ,Name: " + getName() +
        " ,Jenre: " + getJenre() +
        " ,Description: " + getDescription() +
        " ,Author: " + getAuthor() + " }";
  }
}