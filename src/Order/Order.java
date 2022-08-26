package Order;

public class Order {
    private int tableNumber;
    private int peopleNumber;
    private double bill;
    
    public Order(int tableNumber, int peopleNumber, double bill) {
        this.tableNumber = tableNumber;
        this.peopleNumber = peopleNumber;
        this.bill = bill;
    }
    public Order() {
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public int getPeopleNumber() {
        return peopleNumber;
    }
    public double getBill() {
        return bill;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
    public void setBill(double bill) {
        this.bill = bill;
    }
    @Override
    public String toString() {
        return "Table number: " + tableNumber + ", People number: " + peopleNumber + ", Bill: " + bill;
    }

}
