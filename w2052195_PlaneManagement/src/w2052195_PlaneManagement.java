import java.util.*;
import java.util.Scanner;

public class w2052195_PlaneManagement {

    public static Ticket[][] array_tickets = {new Ticket[14], new Ticket[12], new Ticket[12], new Ticket[14]};

    public static void main(String[] args) {

        System.out.println("Welcome to the Plane Management application");
        System.out.println();

        int[][] seats = {new int[14], new int[12], new int[12], new int[14]}; //creating a 2D array


        Scanner input = new Scanner(System.in);

        int option;
        do {

        System.out.println("*".repeat(50) + "\n*" + " ".repeat(18) + "MENU OPTIONS" + " ".repeat(18) + "*" + "\n" + "*".repeat(50)); //printing the user menu
        System.out.println(
                "\t1) Buy a seat " +
                        "\n\t2) Cancel a seat " +
                        "\n\t3) Find first available seat " +
                        "\n\t4) Show seating plan " +
                        "\n\t5) Print ticket information and total sales " +
                        "\n\t6) Search tickets " +
                        "\n\t0) Quit");
        System.out.println("*".repeat(50));


            try { //handling the exception that occurs when the user enters a wrong input with a try catch block
                System.out.println("Select an option: ");
                option = input.nextInt();

                if (option < 0 || option > 6) { //checking if the user enters a valid input for the option
                    System.out.println("Please enter a valid option (0-6) ");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid. Please try again.");
                input.nextLine();
                option = -1; //forces the loop to continue
            }

            switch (option) { //switch case to process the menu options according to the input of the user for option
                case 1:
                    buy_seat(seats);
                    break;
                case 2:
                    cancel_seat(seats);
                    break;
                case 3:
                    find_first_available(seats);
                    break;
                case 4:
                    show_seating_plan(seats);
                    break;
                case 5:
                    print_ticket_info();
                    break;
                case 6:
                    search_tickets(seats);
                    break;
            }


        } while (option != 0);

        if (option == 0) { //terminates the program if the user enter 0 for the option
            System.out.println("Goodbye!");
        }

    }

    /**
     * asks the user to enter row letter and seat number
     * checks if the seat is available and book the seat
     * @param seats
     */

    public static void buy_seat(int[][] seats) {

        int row = rowInfo(); //calling the method rowInfo to get the user input for the row
        int seat = seatInfo(row); //calling the method seatInfo to get the user input for the seat

        if (row >= 0 && row < seats.length && seat >= 0 && seat < seats[row].length) {
            if (seats[row][seat] == 0) { // checks if the seat is available
                seats[row][seat] = 1; // marks the seat as 1 which indicates that the seat is taken

                Person customer = customerInfo(); // calls the method customerInfo
                createTicket(customer, row, seat, tickets_Price(seat)); //calls the method createTicket

                System.out.println("Seat at row " + (char) ('A' + row) + " and seat number " + (seat + 1) + " is sold.");

            } else {
                System.out.println("Seat at row " + (char) ('A' + row) + " and seat number " + (seat + 1) + " is not available.");
            }

        }

    }

    /**
     * asks the user to enter row letter and seat number
     * checks the availability of the seat and cancel the booking if it is booked
     *
     * @param seats
     */

    public static void cancel_seat(int[][] seats) {

        int row = rowInfo(); //calling the method rowInfo to get the user input for the row
        int seat = seatInfo(row); //calling the method seatInfo to get the user input for the seat

        if (row >= 0 && row < seats.length && seat >= 0 && seat < seats[row].length) {
            if (seats[row][seat] == 1) {  // checks if the seat is taken
                seats[row][seat] = 0; // marks the seat as 0 which indicates that the seat is available

                array_tickets[row][seat].delete(); //deletes the text file that is created when cancelling the seat
                array_tickets[row][seat] = null; //marks the seat as null when a specific seat is cancelled

                System.out.println("Ticket for seat at row " + (char) ('A' + row) + " and seat number " + (seat + 1) + " is cancelled.");

            } else {
                System.out.println("Seat at row " + (char) ('A' + row) + " and seat number " + (seat + 1) + " is already available.");
            }

        }

    }

    public static void find_first_available(int[][] seats) {

        for (int r = 0; r < seats.length; r++) { //checks the first available seat by going through seat by seat
            for (int s = 0; s < seats[r].length; s++) {
                if (seats[r][s] == 0) {
                    System.out.println("Seat at row " + (char) ('A' + r) + " and seat number " + (s + 1) + " is the first seat available.");
                    return;
                }
            }
        }
        System.out.println("No seats are available.");
    }

    public static void show_seating_plan(int[][] seats) {

        for (int r = 0; r < seats.length; r++) { //shows the seating plan and marks the available seats as "O" and taken seats as "X"
            for (int s = 0; s < seats[r].length; s++) {
                if (seats[r][s] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }

    }


    /**
     * gets the user input for the row_letter
     * checks the validity of the input
     * converts the row_letter into a number and store it in rowIndex
     *
     * @return rowIndex
     */
    public static int rowInfo() { //method to get the seat row and check the validity

        Scanner input = new Scanner(System.in);

        System.out.println("Enter row letter: ");
        char row_letter = Character.toUpperCase(input.next().charAt(0));

        while (true) {

            if (row_letter < 'A' || row_letter > 'D') { // checks if the user enters a valid seat
                System.out.println("Invalid. Enter row letter (A-D)");
                row_letter = Character.toUpperCase(input.next().charAt(0));
            } else {
                break;
            }
        }

        int rowIndex = 0;
        switch (row_letter) { //converts the row letter into a row number
            case 'A':
                rowIndex = 0;
                break;
            case 'B':
                rowIndex = 1;
                break;
            case 'C':
                rowIndex = 2;
                break;
            case 'D':
                rowIndex = 3;
                break;
        }
        return rowIndex;
    }

    /**
     * gets the user input for the seat_number
     * checks the validity of the input
     * @param rowIndex
     * @return seat_number - 1
     */
    public static int seatInfo(int rowIndex) { //method to get the seat number and check the validity

        Scanner input = new Scanner(System.in);

        int seat_number;

        while (true) {

            System.out.println("Enter seat number: ");

            try {

                seat_number = input.nextInt();

                if (rowIndex == 0 && (seat_number < 1 || seat_number > 14)) { //checks if the user enters a valid seat number
                    System.out.println("Invalid. Must be a number between 1-14");


                } else if (rowIndex == 1 && (seat_number < 1 || seat_number > 12)) {
                    System.out.println("Invalid. Must be a number between 1-12");


                } else if (rowIndex == 2 && (seat_number < 1 || seat_number > 12)) {
                    System.out.println("Invalid. Must be a number between 1-12");


                } else if (rowIndex == 3 && (seat_number < 1 || seat_number > 14)) {
                    System.out.println("Invalid. Must be a number between 1-14");


                } else {
                    break;
                }

            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) { // handle the exception that occurs when a wrong input is entered
                System.out.println("Invalid input.");
                input.next();
            }


        }
        return seat_number - 1;
    }

    public static int tickets_Price(int seat_number){ //method to assign the prices of the seats
        int price = 0;

        if(seat_number>=0 && seat_number<6){
            price = 200;
        }

        else if(seat_number>5 && seat_number<10){
            price = 150;
        }

        else if(seat_number>9 && seat_number<15){
            price = 180;
        }
        return price;
    }

    /**
     * gets the input for the customer information (name, surname, email)
     * checks the validity of the email
     *
     * @return a new person object
     */
    public static Person customerInfo() { //creating a method to input user details

        Scanner input = new Scanner(System.in);

        System.out.println("Enter name: ");
        String customer_name = input.next();

        System.out.println("Enter surname: ");
        String customer_surname = input.next();

        String customer_email;

        System.out.println("Enter email: ");

        while (true) { //checking if the user inputs a valid email

            customer_email = input.next();

            if ( customer_email.contains("@") && customer_email.contains(".")){
                break;
            }
            else {
                System.out.println("Please enter a valid email.");
            }

        }

        return new Person(customer_name, customer_surname, customer_email);
    }


    /**
     * creates a new ticket object
     * assign the values into a 2D array
     * saves the details in a text file
     *
     *
     * @param person
     * @param row
     * @param seat
     * @param price
     * @return ticket
     */
    public static Ticket createTicket(Person person, int row, int seat, int price){ //method to create new ticket objects

        Ticket ticket = new Ticket (person,row, seat, price); //creating a new ticket object

        array_tickets[row][seat] = ticket;//assigning it to an array
        array_tickets[row][seat].save(); //saves the ticket information in a text file

        return ticket;
    }

    /**
     * checks if any tickets have been sold
     * if so prints the information about them including the total amount of all the sold tickets
     */

    public static void print_ticket_info(){ //method to print the ticket information

        boolean ticketSold =  false; //indicates that no tickets have been sold
        int total_amount = 0;

        for (int i = 0; i < array_tickets.length; i++) {
            for (int j = 0; j < array_tickets[i].length; j++) {

                Ticket ticket = array_tickets[i][j];

                if(ticket != null){ //checks if the array is not empty
                    ticketSold = true;

                    System.out.println(ticket.ticketInfo());

                    total_amount = total_amount+ ticket.getPrice();

                    System.out.println("Total amount: " + "£" + total_amount);

                    System.out.println();
                }
            }
        }

        if (!ticketSold){
            System.out.println("No tickets have been sold.");
        }

    }

    /**
     * asks the user to enter row letter and seat number
     * checks if the seat is available
     * if not prints the information about the ticket
     * @param seats
     */

    public static void search_tickets(int[][] seats){ //method to check if a specific seat is taken or not and to print the information if it is taken

        int row = rowInfo();
        int seat = seatInfo(row);

        if (row >= 0 && row < seats.length && seat >= 0 && seat < seats[row].length) {
            if (seats[row][seat] == 1) {

                System.out.println("This seat is already taken. ");
                System.out.println();

                Ticket ticket = array_tickets[row][seat];

                String seat_owner = ticket.ticketInfo();//prints the seat information
                System.out.println(seat_owner);

            }
            else {

                System.out.println("This seat is available.");
            }

        }
    }

}
