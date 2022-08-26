package Order;
import java.util.List;

public interface OrderDAO {
    
    public void insertOrder(Order o);  

    public void modifyOrder(int tableNumber, Order o);

    public void deleteOrder(int tableNumber);

    public List<Order> readOrders(); // Return all orders

    public Order readOrder(int numberTable); //Return a single order

    public int[] tablesAvailable(); // Return tables available

}
