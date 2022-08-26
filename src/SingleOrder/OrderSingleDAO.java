package SingleOrder;

import Order.Order;

public interface OrderSingleDAO {

    public void insertSingleOrder(int refTable, String dish);

    public double calculateBill(int refTable);

    public void deleteDishSingleOrder(int tableNumber, String dish);

    public Order viewSingleOrder(int tableNumber);

    public boolean dishContained(int refTable);
}
