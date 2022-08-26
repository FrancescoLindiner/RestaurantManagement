package SingleOrder;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Order.Order;

public class OrderSingleDAOImpl implements OrderSingleDAO {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    final String DB_URL = "jdbc:mysql://localhost:3306/Ristorante";
    Statement stmt = null;
    Connection conn = null;

    /**
     * This method insert a single order
     */
    @Override
    public void insertSingleOrder(int refTable, String dish) {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "INSERT INTO SINGLE_ORDER (ref_table, ref_dish) VALUES (" + refTable + ", \""
                    + dish + "\" );";

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
     * This method calculate the bill of a table
     */
    @Override
    public double calculateBill(int refTable) {
        double totalBill = 0.0;
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "select sum(D.price) from DISH D, SINGLE_ORDER SO where SO.ref_dish=D.dish and ref_table= "
                    + refTable + " ;";

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                totalBill = rs.getInt("sum(D.price)");
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
        return totalBill;
    }

    /**
     * This method eliminates a dish from a single order 
     */
    @Override
    public void deleteDishSingleOrder(int tableNumber, String dish) {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "delete from SINGLE_ORDER where ref_table = " + tableNumber + " and ref_dish = \""
                    + dish + "\";";
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
     * This method is for displaying a single order
     */
    public Order viewSingleOrder(int tableNumber) {
        Order order = new Order();
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "select o.table_number, o.people_number, sum(D.price) from ORDERS o, SINGLE_ORDER SO, DISH D where O.table_number=SO.ref_table and D.dish=SO.ref_dish and table_number= "
                    + tableNumber;

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int tableNumberTMP = rs.getInt("o.table_number");
                int peopleNumberTMP = rs.getInt("o.people_number");
                double billTMP = rs.getDouble("sum(D.price)");
                order = new Order(tableNumberTMP, peopleNumberTMP, billTMP);
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
        return order;
    }

    /**
     * This method returns true if dishes have been ordered on the table
     */
    @Override
    public boolean dishContained(int refTable) {
        String dish = "";
        boolean isDish=false;
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "select ref_dish from SINGLE_ORDER where ref_table= " + refTable + ";"; 

            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dish = rs.getString("ref_dish");
            }
            System.out.println(dish);
            if(dish == ""){
                isDish=false;;
            }
            else 
                isDish=true;

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
        return isDish;
    }

}