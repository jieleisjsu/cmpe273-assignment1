package wallet;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Login{

String login_id;

@NotEmpty
String url;

@NotEmpty
String login;

@NotEmpty
String password;

public Login(){}
public Login(String login_id, String url, String login,String password){
super();
this.login_id = login_id;
this.url = url;
this.login = login;
this.password = password;
}
public String getLogin_id(){return login_id;}
public String getUrl(){return url;}
public String getLogin(){return login;}
public String getPassword(){return password;}
public void setLogin_id(String login_id){this.login_id = login_id;}
public void setUrl(String url){this.url = url;}
public void setLogin(String login){this.login = login;}
public void setPassword(String password){this.password = password;}
}
