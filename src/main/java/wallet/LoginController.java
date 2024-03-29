package wallet;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Random;
import java.lang.Math;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class LoginController
{
Random randomGenerator = new Random();
private int login_no=randomGenerator.nextInt(50000);
Map<String, List<Map<String,Login>>> loginData = new HashMap<String, List<Map<String,Login>>>();

//Add Login
@RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.POST)
public Login addLogin(@Valid @RequestBody Login login, @PathVariable("user_id") String user_id){
login_no= login_no+10;
String login_id= "l-"+Integer.toString(login_no);	
Login new_login=new Login(login_id, login.getUrl(), login.getLogin(), login.getPassword());
int flag=0;
Iterator itr = loginData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String, Login>> l=(List<Map<String,Login>>)loginData.get(user_id);
Map<String, Login> map_new_login= new HashMap<String, Login>();
map_new_login.put(login_id, new_login);
l.add(map_new_login);
flag=1;
break;
}}

if(flag==0){
List<Map<String,Login>> list= new ArrayList<Map<String,Login>>();
Map<String, Login> map_new_login= new HashMap<String, Login>();
map_new_login.put(login_id, new_login);
list.add(map_new_login);
loginData.put(user_id,list);
}
return new_login;
}

//list login
@RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.GET)
public List<Map<String,Login>> viewAllLogins(@PathVariable("user_id") String user_id){
System.out.println(user_id);
return loginData.get(user_id);
}

//delete
@RequestMapping(value="/api/v1/users/{user_id}/weblogins/{login_id}", method=RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteLogin(@PathVariable("user_id") String user_id, @PathVariable("login_id") String login_id){
int flag=0;
Iterator itr = loginData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String,Login>> list=(List<Map<String,Login>>)loginData.get(user_id);
Iterator itrl = list.listIterator();
while (itrl.hasNext()) {
if(((Map<String,Login>)itrl.next()).containsKey(login_id)){
itrl.remove();}}break;}}}


}
