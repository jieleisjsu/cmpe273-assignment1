package wallet;
import org.hibernate.validator.constraints.*;

public class Card{

String card_id;

@NotEmpty
@Length(min=1,message="card name invalid")
String card_name;

@Length(min=1,message="card number invalid")
String card_number;

String expiration_date;
public Card(){}
public Card(String card_id,String card_name,String card_number, String expiration_date){
super();
this.card_id = card_id;
this.card_name = card_name;
this.card_number = card_number;
this.expiration_date = expiration_date;
}
public String getCard_id(){return card_id;}
public String getCard_name(){return card_name;}
public String getCard_number(){return card_number;}
public String getExpiration_date(){return expiration_date;}
public void setCard_id(String card_id){this.card_id = card_id;}
public void setCard_name(String card_name){this.card_name = card_name;}
public void setCard_number(String card_number){this.card_number = card_number;}
public void setExpiration_date(String expiration_date){this.expiration_date = expiration_date;}
}




