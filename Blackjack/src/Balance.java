public class Balance{
    private int balance;

    public void setBalance(int diff) {
        balance += diff;
    }

    public int getBalance() {
        return balance;
    }
    
    public Balance(int balance) {
        this.balance = balance;
    }
    
}
