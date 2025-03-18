public class Person {

    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email){//creating a constructor
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //creating getters
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){

        return email;
    }


    //creating setters
    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * returns customer information as a string
     * @return
     */
    public String customerInfo(){

        return "First Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Email: " + getEmail();

    }

}
