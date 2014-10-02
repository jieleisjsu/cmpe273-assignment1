package wallet;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Bank{

String bank_id;

String account_name;

@NotEmpty
String routing_number;

@NotEmpty
String account_number;
public Bank(){}
public Bank(String bank_id, String account_name,String routing_number, String account_number) 
{
super();
this.bank_id = bank_id;
this.account_name = account_name;
this.routing_number = routing_number;
this.account_number = account_number;
}
public String getBank_id(){return bank_id;}
public String getAccount_name(){return account_name;}
public String getRouting_number(){return routing_number;}
public String getAccount_number(){return account_number;}
public void setBank_id(String bank_id){this.bank_id = bank_id;}
public void setAccount_name(String account_name){this.account_name = account_name;}
public void setRouting_number(String routing_number){this.routing_number = routing_number;}
public void setAccount_number(String account_number){this.account_number = account_number;}
}
