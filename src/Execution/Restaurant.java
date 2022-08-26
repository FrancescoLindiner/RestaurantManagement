package Execution;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Dish.Dish;
import Dish.DishDaoImpl;
import Order.Order;
import Order.OrderDAOImpl;
import SingleOrder.OrderSingleDAOImpl;

/**
 * @author Francesco Lindiner
 * @version 1.0
 */
public class Restaurant {

    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("C:\\Users\\frali\\Desktop\\GestioneRistorante\\logo.png");

        int choose = -1;
        OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
        DishDaoImpl dishDaoImpl = new DishDaoImpl();
        OrderSingleDAOImpl singleOrderDAOImpl = new OrderSingleDAOImpl();

        while (choose != 6) {
            choose = JOptionPane
                    .showInternalOptionDialog(null, "Choose an option", "Restaurant management",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.ERROR_MESSAGE, icon, new Object[] { "Enter new order",
                                    "Order display", "Modify an order", "Delete an order",
                                    "Delte all orders", "Other options", "Exit" },
                            "");
            switch (choose) {
                case 0:
                    List<Order> orderDimension = orderDAOImpl.readOrders();

                    if (orderDimension.size() >= 30) {
                        JOptionPane.showMessageDialog(null, "The restaurant is full");
                        break;
                    }

                    boolean controlDish = false;
                    boolean booleanInsert = false;
                    boolean isInsert = false;
                    int[] tables = orderDAOImpl.tablesAvailable();

                    do {
                        try {
                            if (tables.length == 30)
                                JOptionPane.showMessageDialog(null, "The restaurant is full");
                            String stringTableNumber = JOptionPane.showInputDialog(null,
                                    "Insert the table number\nTables available:\n"
                                            + orderDAOImpl.printTablesAvailable());
                            if (stringTableNumber == null) {
                                break;
                            }
                            int tableNumberTMP = Integer.parseInt(stringTableNumber);
                            if (tableNumberTMP > 30 || tableNumberTMP < 1) {
                                JOptionPane.showMessageDialog(null,
                                        "The table number must be between 0 and 30");
                                break;
                            }
                            Order orderToInsert = orderDAOImpl.readOrder(tableNumberTMP); // Return the order for
                                                                                                 // that table

                            if (orderToInsert == null) { // If order==null, it means that I can insert the order
                                String stringPersonNumber = JOptionPane.showInputDialog(null, "How many people are?");
                                int personNumberTMP = Integer.parseInt(stringPersonNumber);

                                orderDAOImpl.insertOrder(new Order(tableNumberTMP, personNumberTMP, 0.0));
                                do {
                                    Dish[] dishList = dishDaoImpl.dishList();
                                    String[] option = new String[dishDaoImpl.dimensionDishList()];
                                    for (int i = 0; i < option.length; i++) {
                                        option[i] = dishList[i].getDish();
                                    }
                                    String dish = (String) JOptionPane.showInputDialog(null,
                                            "Enter the dishes", "", JOptionPane.QUESTION_MESSAGE, null, option,
                                            option);
                                    if (dish == null) {
                                        controlDish = true;
                                        break;
                                    }
                                    singleOrderDAOImpl.insertSingleOrder(tableNumberTMP, dish);
                                    if (JOptionPane.showConfirmDialog(null, "Order inserted, do you want to insert another one??",
                                            " ",
                                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                        isInsert = true;
                                    } else {
                                        isInsert = false;
                                    }
                                } while (isInsert);
                                double totalBill;
                                if (controlDish && !(singleOrderDAOImpl.dishContained(tableNumberTMP))) { // If controllaPietanze==false it means that
                                                                                                        // the cancel button has been clicked, and if no
                                                                                                        // dishes have been entered, I have to delete the order
                                    orderDAOImpl.deleteOrder(tableNumberTMP);
                                    break;
                                } else {
                                    totalBill = singleOrderDAOImpl.calculateBill(tableNumberTMP);
                                }
                                JOptionPane.showMessageDialog(null, "The total bill is  " + totalBill + " €");

                                orderDAOImpl.insertModifiedOrder(
                                        new Order(tableNumberTMP, personNumberTMP, totalBill));
                                JOptionPane.showMessageDialog(null, "Order inserted");
                                booleanInsert = true;

                            } else if ((orderToInsert != null)
                                    && (JOptionPane.showConfirmDialog(null, "Table busy, would you like to try again?",
                                            " ",
                                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
                                booleanInsert = false;
                            } else {
                                booleanInsert = true;
                            }
                        } catch (NullPointerException e) {
                            break;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input");
                            break;
                        }
                    } while (!booleanInsert);

                    break;
                case 1:
                    int option = JOptionPane
                            .showInternalOptionDialog(null, "What do you want to view?", "Restaurant management",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.ERROR_MESSAGE, icon,
                                    new Object[] { "View all tables ", "View single table account" },
                                    "");
                    switch (option) {
                        case 0:
                            String string = "";
                            List<Order> ordini = orderDAOImpl.readOrders();
                            for (Order o : ordini) {
                                string += "Table number: " + o.getTableNumber() + " Number of people: "
                                        + o.getPeopleNumber()
                                        + " Bill: " + o.getBill() + "\n";
                            }
                            if (string == "") {
                                JOptionPane.showMessageDialog(null, "There are no orders");
                            } else {
                                JOptionPane.showMessageDialog(null, string);
                            }
                            break;
                        case 1:
                            boolean ctrl = true;

                            try {
                                List<Order> listaOrdini = orderDAOImpl.readOrders();
                                if (listaOrdini.size() == 0) {
                                    JOptionPane.showMessageDialog(null, "There are no orders");
                                } else {
                                    List<Order> orderList = orderDAOImpl.readOrders();
                                    do {
                                        String mString = "\nTable number\n";
                                        for (Order o : orderList) {
                                            mString += o.getTableNumber() + " ";
                                        }
                                        String stringTable = JOptionPane
                                                .showInputDialog("Which table do you want to view?" + mString);
                                        if (stringTable == null) {
                                            break;
                                        }
                                        int table = Integer.parseInt(stringTable);
                                        Order order = singleOrderDAOImpl.viewSingleOrder(table);

                                        if (order.getTableNumber() == 0 && (JOptionPane.showConfirmDialog(null,
                                                "Order does not exist or nothing has been ordered on that table, do you want to try again?",
                                                " ",
                                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
                                            ctrl = true;
                                        } else {
                                            if (!ctrl || order.getTableNumber() == 0)
                                                break;
                                            String[] dishes = dishDaoImpl.dishListToModify(table);
                                            String p = "";
                                            for (int i = 0; i < dishes.length; i++) {
                                                p += dishes[i] + "\n";
                                            }
                                            JOptionPane.showMessageDialog(null,
                                                    order.toString()
                                                            + "\nOrder list: " + p);
                                            ctrl = false;
                                        }
                                    } while (ctrl);
                                }
                                break;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid input");
                            }
                        default:
                            break;
                    }
                    break;
                case 2:
                    List<Order> o = orderDAOImpl.readOrders();
                    if (o.size() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no orders");
                        break;
                    }
                    boolean ctrlOrderModified = false;
                    List<Order> ordersList = orderDAOImpl.readOrders();
                    String mString = "";
                    for (Order order : ordersList) {
                        mString += "Table number: " + order.getTableNumber() + " Number of people: "
                                + order.getPeopleNumber()
                                + " Bill: " + order.getBill() + "\n";
                    }
                    do {
                        try {
                            String numeroTavoloS = JOptionPane.showInputDialog(null,
                                    "Which order do you want to change?\nOrder list: \n" + mString);
                            if (numeroTavoloS == null) {
                                break;
                            }
                            int tableNumber = Integer.parseInt(numeroTavoloS);
                            if (orderDAOImpl.readOrder(tableNumber) != null) {
                                int peopleNumberNew = Integer
                                        .parseInt(JOptionPane.showInputDialog(null, "How many people are?  "));
                                int operazione = JOptionPane
                                        .showInternalOptionDialog(null, "What operation do you want to do?",
                                                "Restaurant management",
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                new Object[] { "Insert a dish", "Delete a dish" },
                                                "");
                                switch (operazione) {
                                    case 0:
                                        do {
                                            Dish[] dishList = dishDaoImpl.dishList();
                                            String[] options = new String[dishDaoImpl.dimensionDishList()];
                                            for (int i = 0; i < options.length; i++) {
                                                options[i] = dishList[i].getDish();
                                            }
                                            String dish = (String) JOptionPane.showInputDialog(null,
                                                    "Enter the dishes", "", JOptionPane.QUESTION_MESSAGE, null,
                                                    options,
                                                    options);

                                            singleOrderDAOImpl.insertSingleOrder(tableNumber, dish);
                                            if (JOptionPane.showConfirmDialog(null,
                                                    "Order inserted, do you want to insert another one?",
                                                    " ",
                                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                                isInsert = true;
                                            } else {
                                                isInsert = false;
                                            }
                                        } while (isInsert);

                                        break;
                                    case 1:
                                        do {
                                            String[] dishList = dishDaoImpl
                                                    .dishListToModify(tableNumber);
                                            String dish = (String) JOptionPane.showInputDialog(null,
                                                    "Select the dish to delete", "",
                                                    JOptionPane.QUESTION_MESSAGE, null,
                                                    dishList,
                                                    dishList);

                                            singleOrderDAOImpl.deleteDishSingleOrder(tableNumber, dish);
                                            if (JOptionPane.showConfirmDialog(null,
                                                    "Deleted dish, do you want to delete another?",
                                                    " ",
                                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                                isInsert = true;
                                            } else {
                                                isInsert = false;
                                            }
                                        } while (isInsert);

                                    default:
                                        break;
                                }

                                double totalBill = singleOrderDAOImpl.calculateBill(tableNumber);
                                JOptionPane.showMessageDialog(null, "The total bill is: " + totalBill);
                                orderDAOImpl.modifyOrder(tableNumber,
                                        new Order(tableNumber, peopleNumberNew, totalBill));
                                JOptionPane.showMessageDialog(null, "Modified order");
                                ctrlOrderModified = true;
                            } else if (orderDAOImpl.readOrder(tableNumber) == null
                                    && (JOptionPane.showConfirmDialog(null, "Order does not exist, you want to try again?", " ",
                                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
                                ctrlOrderModified = false;
                            } else {
                                ctrlOrderModified = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input");
                            break;
                        } catch (NullPointerException e) {
                            break;
                        }

                    } while (!ctrlOrderModified);
                    break;
                case 3:
                    List<Order> orders = orderDAOImpl.readOrders();
                    if (orders.size() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no orders");
                        break;
                    }
                    boolean ctrlOrder = false;
                    int tableNumberToDelete;
                    List<Order> orderListToDelete = orderDAOImpl.readOrders();
                    String list = "\n";
                    for (Order order : orderListToDelete) {
                        list += order.getTableNumber() + " ";
                    }
                    do {
                        try {
                            String stringTableNumber = JOptionPane.showInputDialog(null,
                                    "Enter the number of the table to be deleted:  " + list);
                            if (stringTableNumber == null) {
                                break;
                            }
                            tableNumberToDelete = Integer.parseInt(stringTableNumber);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input");
                            break;
                        }
                        if (orderDAOImpl.readOrder(tableNumberToDelete) != null) {
                            orderDAOImpl.deleteOrder(tableNumberToDelete);
                            JOptionPane.showMessageDialog(null, "Order deleted");
                            ctrlOrder = true;
                        } else if (orderDAOImpl.readOrder(tableNumberToDelete) == null
                                && (JOptionPane.showConfirmDialog(null, "Order does not exist, do you want to try again?", " ",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION))
                            ctrlOrder = false;
                        else
                            ctrlOrder = true;
                    } while (!ctrlOrder);
                    break;
                case 4:
                    List<Order> orders2 = orderDAOImpl.readOrders();
                    if (orders2.size() == 0) {
                        JOptionPane.showMessageDialog(null, "There are no orders");
                        break;
                    }
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all orders?", " ",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        orderDAOImpl.deleteAll();
                        JOptionPane.showMessageDialog(null, "All orders have been deleted");
                    }
                    break;
                case 5:
                    int op;
                    do {
                        op = JOptionPane
                                .showInternalOptionDialog(null, "Choose an option", "Restaurant management",
                                        JOptionPane.YES_NO_CANCEL_OPTION,
                                        JOptionPane.ERROR_MESSAGE, icon,
                                        new Object[] { "Total sum of the account", "Total number of people",
                                                "Insert dish", "Delete dish",
                                                "View all dishes", "Go back",
                                                "Exit" },
                                        "");
                        switch (op) {
                            case 0:
                                double totalBill = 0.0;
                                List<Order> orders3 = orderDAOImpl.readOrders();
                                for (Order order : orders3) {
                                    totalBill += order.getBill();
                                }
                                JOptionPane.showMessageDialog(null, "Total amount: " + totalBill + " €");
                                break;
                            case 1:
                                int totalPeople = 0;
                                List<Order> orders4 = orderDAOImpl.readOrders();
                                for (Order order : orders4) {
                                    totalPeople += order.getPeopleNumber();
                                }
                                JOptionPane.showMessageDialog(null, "Total amount: " + totalPeople);
                                break;
                            case 2:
                                boolean isContained = false;
                                String dish;
                                int exit = 0;
                                String codDish;
                                double price = -1;
                                Dish[] dishList = dishDaoImpl.dishList();

                                do {
                                    isContained = false;
                                    dish = JOptionPane.showInputDialog("Insert dish");
                                    if (dish == null)
                                        break;
                                    for (Dish p : dishList) {
                                        if (p.getDish().equals(dish))
                                            isContained = true;
                                    }
                                    if (isContained) {
                                        exit = JOptionPane.showConfirmDialog(null,
                                                "Dish already present, do you want to try again?", " ",
                                                JOptionPane.YES_NO_OPTION);
                                        if (exit == 1)
                                            break;
                                    }
                                } while (isContained);
                                if (exit == 1 || dish == null)
                                    break;
                                do {
                                    isContained = false;
                                    codDish = JOptionPane.showInputDialog("Enter the dish code");
                                    if (codDish == null)
                                        break;
                                    for (Dish p : dishList) {
                                        if (p.getDishCod().equals(codDish))
                                            isContained = true;
                                    }
                                    if (isContained) {
                                        exit = JOptionPane.showConfirmDialog(null,
                                                "Code already present, do you want to try again?", " ",
                                                JOptionPane.YES_NO_OPTION);
                                        if (exit == 1)
                                            break;
                                    }
                                } while (isContained || codDish == null);
                                if (exit == 1)
                                    break;
                                try {
                                    String stringPrice = JOptionPane.showInputDialog("Enter the price");
                                    if (stringPrice == null)
                                        break;
                                    price = Double.parseDouble(stringPrice);
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Invalid input");
                                    break;
                                }

                                dishDaoImpl.insertDish(new Dish(dish, codDish, price));
                                JOptionPane.showMessageDialog(null, "Inserted dish");
                                break;
                            case 3:
                                Dish[] dishList2 = dishDaoImpl.dishList();
                                String[] m = new String[dishDaoImpl.dimensionDishList()];
                                for (int i = 0; i < dishList2.length; i++) {
                                    m[i] = dishList2[i].getDish();
                                }
                                String dishToDelete = (String) JOptionPane.showInputDialog(null,
                                        "Select the dish to delete", "",
                                        JOptionPane.QUESTION_MESSAGE, null, m, m);
                                if (dishToDelete == null)
                                    break;
                                dishDaoImpl.deleteDish(dishToDelete);
                                JOptionPane.showMessageDialog(null, "Dish eliminated");
                                break;
                            case 4:
                                String message = "";
                                Dish[] dish2 = dishDaoImpl.dishList();
                                for (Dish p : dish2) {
                                    message += "Dish: " + p.getDish() + ", Dish code: "
                                            + p.getDishCod() + ", Price: " + p.getPrice() + "€\n";
                                }
                                if (message == "") {
                                    JOptionPane.showMessageDialog(null, "There are no dishes");
                                } else {
                                    JOptionPane.showMessageDialog(null, message);
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.exit(0);
                                break;

                        }
                    } while (op != 5);
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}