package wallet;

import javax.validation.Valid;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;  
import java.text.Format;  
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
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
public class CardController
{
Random randomGenerator = new Random();
private int card_number=randomGenerator.nextInt(20000);
Map<String, List<Map<String,Card>>> cardData = new HashMap<String, List<Map<String,Card>>>();


//Add card details
@RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.POST)
public Card addCard(@Valid @RequestBody Card card, @PathVariable("user_id") String user_id){
card_number= card_number+51;
String card_id= "c-"+Integer.toString(card_number);	
Calendar date = Calendar.getInstance();
date.setTime(new Date());
date.add(Calendar.YEAR,1);
Format f = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
String expiration_date=f.format(date);
Card new_card=new Card(card_id, card.getCard_name(), card.getCard_number(), card.getExpiration_date());


//Map Iterator to check if the HashMap has value or not and add card details
int flag=0;
Iterator itr = cardData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String, Card>> list=(List<Map<String, Card>>)cardData.get(user_id);
Map<String, Card> map_new_card= new HashMap<String, Card>();
map_new_card.put(card_id, new_card);
list.add(map_new_card);
flag=1;
break;
}}
if(flag==0){
List<Map<String, Card>> list1= new ArrayList<Map<String,Card>>();
Map<String, Card> map_new_card= new HashMap<String, Card>();
map_new_card.put(card_id, new_card);
list1.add(map_new_card);
cardData.put(user_id,list1);
}
return new_card;
}


//Get list of cards
@RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.GET)
public List<Map<String,Card>> viewAllCards(@PathVariable("user_id") String user_id){
System.out.println(user_id);
return cardData.get(user_id);	
}

//delete card details
@RequestMapping(value="/api/v1/users/{user_id}/idcards/{card_id}", method=RequestMethod.DELETE)
@ResponseStatus(HttpStatus.NO_CONTENT)
 public void deleteCard(@PathVariable("user_id") String user_id, @PathVariable("card_id") String card_id){
//Hashmap iterator to check if map has the value or not
int flag=0;
Iterator itr = cardData.entrySet().iterator();
while (itr.hasNext()) {
Map.Entry pairs = (Map.Entry)itr.next();
if(pairs.getKey().equals(user_id)){
List<Map<String, Card>> temp=(List<Map<String, Card>>)cardData.get(user_id);
Iterator itr_list = temp.listIterator();
while (itr_list.hasNext()) {
if(((Map<String,Card>)itr_list.next()).containsKey(card_id)){
itr_list.remove();
}}
break;
}}}




}

