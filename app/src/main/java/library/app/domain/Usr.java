package library.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usr")
public class Usr {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String login;

  private String password;

  private String token;

  public Usr(){
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }  

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }  

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}