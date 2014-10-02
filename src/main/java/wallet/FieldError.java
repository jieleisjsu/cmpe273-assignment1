package wallet;

public class FieldError{
 
 private String field;

private String message;
public String getMessage()
{
return message;
}
public void setMessage(String message)
{
this.message=message;
}
 public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
}}
