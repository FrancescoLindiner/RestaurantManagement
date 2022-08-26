package Order;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class OrderDAOImpl implements OrderDAO {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    final String DB_URL = "jdbc:mysql://localhost:3306/Ristorante";
    Statement stmt = null;
    Connection conn = null;

    /**
     * This method insert an order
     */
    @Override
    public void insertOrder(Order o) {

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "INSERT INTO ORDERS VALUES(" + o.getTableNumber() + "," + o.getPeopleNumber() + ","
                    + o.getBill() + ")";

            System.out.println("Inserting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method delete an order
     */
    @Override
    public void deleteOrder(int tableNumberToDelete) {

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "DELETE FROM ORDERS O WHERE O.table_number = " + tableNumberToDelete + ";";
            System.out.println("Deleting record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    /**
     * This method returns the list of the orders
     */
    @Override
    public List<Order> readOrders() {

        List<Order> orders = new ArrayList<>();

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "SELECT * FROM ORDERS";
            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int tableNumberTMP = rs.getInt("table_number");
                int peopleNumberTMP = rs.getInt("people_number");
                double billTMP = rs.getDouble("bill");

                Order o = new Order(tableNumberTMP, peopleNumberTMP, billTMP);
                orders.add(o);

            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    /**
     * This method is for modify an order
     */
    @Override
    public void modifyOrder(int tableNumber, Order o) {
        
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "UPDATE ORDERS O SET O.people_number = " + o.getPeopleNumber() + ", O.bill = " + o.getBill() + "where O.table_number = " + tableNumber +";";
            System.out.println("Updating record into the table...");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns the order relative to a table
     */
    @Override
    public Order readOrder(int tableNumber) {

        Order o = null;

        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "SELECT * FROM ORDERS O WHERE O.table_number = " + tableNumber;
            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int tableNumberTMP = rs.getInt("table_number");
                int peopleNumberTMP = rs.getInt("people_number");
                double billTMP = rs.getDouble("bill");
                o = new Order(tableNumberTMP, peopleNumberTMP, billTMP);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return o;
    }

    /**
     * This method eliminates all orders 
     */
    public void deleteAll() {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "DELETE FROM ORDERS";
            System.out.println("Deleting record into the table...");

            stmt = conn.createStatement();

            stmt.executeUpdate(sql);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * This method insert an modified order
     */
    public void insertModifiedOrder(Order order) {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "UPDATE ORDERS O SET O.bill = " + order.getBill() + "WHERE O.table_number = " + order.getTableNumber() + ";";

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns an array with all available tables
     */
    @Override
    public int[] tablesAvailable() {

        List<Order> tables = readOrders();
        int[] tablesAvailable = new int[11];

        boolean[] tablesOccupied = new boolean[11];

        for (int i = 0; i < tables.size(); i++) {
            tablesOccupied[tables.get(i).getTableNumber()] = true;
        }
        for (int i = 0; i < tablesOccupied.length; i++) {
            if(!tablesOccupied[i])
                tablesAvailable[i]=i;
        }   

        return tablesAvailable;
    }

    public String printTablesAvailable(){
        int[] tables = tablesAvailable();
        String string = "";
        for (int i = 1; i < tables.length; i++) {
            if(tables[i]==0) string += "X, ";
            else
                string += tables[i] + ", ";
        }
        string = string.substring(0, string.length()-2); // To delete the last comma
        return string;
    }
}