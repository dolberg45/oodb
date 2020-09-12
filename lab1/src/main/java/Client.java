public class Client extends Human {

    private int balance;

    public Client(String name, String surname, int age, String phoneNumber, String email, int balance) {
        super(name, surname, age, phoneNumber, email);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
