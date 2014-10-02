package wallet;

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
import javax.validation.Valid;

@RestController
public class BankController
{
Random randomGenerator = new Random();
private int login_id=randomGenerator.nextInt(35000);
Map<String, List<Map<String,Bank>>> bankData = new HashMap<String, List<Map<String,Bank>>>();

//Addbank
@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.POST)
public Bank addbank(@Valid @RequestBody Bank bank, @PathVariable("user_id") String user_id){
login_id= login_id+11;
String bank_id= "b-"+Integer.toString(login_id);
Bank new_bank=new Bank(bank_id, bank.getAccount_name(), bank.getRouting_number(), bank.getAccount_number());
int flag=0;
Iterator itr = bankData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String, Bank>> temp=(List<Map<String,Bank>>)bankData.get(user_id);
Map<String, Bank> map_new_bank= new HashMap<String, Bank>();
map_new_bank.put(bank_id, new_bank);
temp.add(map_new_bank);
flag=1;
break;
}}

if(flag==0){
List<Map<String,Bank>> list= new ArrayList<Map<String,Bank>>();
Map<String, Bank> map_new_bank= new HashMap<String, Bank>();
map_new_bank.put(bank_id, new_bank);
list.add(map_new_bank);
bankData.put(user_id,list);
}
return new_bank;
}

//list bank
@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.GET)
public List<Map<String,Bank>> viewAllBanks(@PathVariable("user_id") String user_id){
System.out.println(user_id);
return bankData.get(user_id);
}

//Delete a bank login
@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts/{bank_id}", method=RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteBank(@PathVariable("user_id") String user_id, @PathVariable("bank_id") String bank_id){
int flag=0;
Iterator itr = bankData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String,Bank>> list=(List<Map<String,Bank>>)bankData.get(user_id);
Iterator itrlist = list.listIterator();
while (itrlist.hasNext()) {
if(((Map<String,Bank>)itrlist.next()).containsKey(bank_id)){
itrlist.remove();
}}break;}}}


}
