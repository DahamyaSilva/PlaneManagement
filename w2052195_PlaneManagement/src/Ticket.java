import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private int row;

    private int seat;

    private int price;

    private Person person;

    public Ticket(Person person, int row, int seat, int price) { //creating a constructor
        this.person = person;
        this.row = row;
        this.seat = seat;
        this.price = price;

    }

    //creating getters
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    //creating setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getRowLetter(int row) {//method to convert row index to letters

        String row_letter = "";
        switch (row) {
            case 0:
                row_letter = "A";
                break;
            case 1:
                row_letter = "B";
                break;
            case 2:
                row_letter = "C";
                break;
            case 3:
                row_letter = "D";
                break;

        }
        return row_letter;
    }

    /**
     * returns ticket information as a string
     * @return
     */
    public String ticketInfo() {

        return "Customer Information: " + "\n" +getPerson().customerInfo() + "\n" + "Ticket information: " + "\n" +
                "Row: " + getRowLetter(getRow()) + "\n" +
                "Seat: " + (getSeat()+1) + "\n" +
                "Price: " + "£" + getPrice();

    }

    /**
     * saves the ticket information into a text file
     */
    public void save()  {
        String fileName = getRowLetter(getRow()) + (getSeat()+1) + ".txt";

        try(FileWriter ticketInfo_writer = new FileWriter(fileName)){
            ticketInfo_writer.write(ticketInfo());
        }catch (IOException e){
            System.out.println("Error");
        }
    }

    /**
     * deletes the created file when cancelling the seat
     */
    public void delete(){
        String fileName = getRowLetter(getRow()) + (getSeat()+1) + ".txt";

        File file = new File (fileName);

        if (file.exists()){
            file.delete();
        }
        else {
            System.out.println("File doesn't exist.");
        }

    }


}
