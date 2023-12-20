package dataentities.akaunting;

import lombok.Getter;
import lombok.Setter;

public class Customer {
    @Setter @Getter private String name ="";
    @Setter @Getter private String Address="123 Elm Street";
    @Setter @Getter private String city="Toronto";
    @Setter @Getter private String state="TO";
    @Setter @Getter private String country="Canada";
    @Setter @Getter private String currency = "Argentine Peso";
    @Setter @Getter private String Email;
    //private String

}
