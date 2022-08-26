package Dish;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Statement;

public class DishDaoImpl implements DishDao {

    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    final String DB_URL = "jdbc:mysql://localhost:3306/Ristorante";

    Statement stmt = null;
    Connection conn = null;

    /**
     * Return the dish list
     */
    @Override
    public Dish[] dishList() {
        int dimension = dimensionDishList();
        Dish[] dishList = new Dish[dimension];
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "SELECT * FROM DISH P";
            System.out.println("Selecting record into the table...");

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next() && i != dimension) {
                String dish = rs.getString("dish");
                String dishCod = rs.getString("cod_dish");
                double price = rs.getDouble("price");
                dishList[i] = new Dish(dish, dishCod, price);
                i++;
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
        return dishList;
    }

    /**
     * Return the dimension of the dish list
     */
    @Override
    public int dimensionDishList() {
        int dimension = 0;
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "SELECT count(cod_dish) FROM DISH P";

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dimension = rs.getInt("count(cod_dish)");
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
        return dimension;
    }

    /**
     * This method returns the list of dishes of the relative table
     * @param tableNumber number of the table where to change the order
     * @return an array that contains the dishes
     */
    public String[] dishListToModify(int tableNumber) { // return the list of dishes of the table x
        int dimension = dimensionDishListToModify(tableNumber); // return the size of the list
        String[] dishList = new String[dimension];
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "select d.dish from dish d, single_order so where so.ref_dish=d.dish and ref_table = " + tableNumber + ";";

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next() && i != dimension) {
                String dishCod = rs.getString("d.dish");
                dishList[i] = dishCod;
                i++;
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
        return dishList;
    }

    /**
     * Returns the dimension of the dish list to modify
     * @param tableNumber
     * @return the dimension
     */
    private int dimensionDishListToModify(int tableNumber) {
        int dimension = 0;
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "select count(d.cod_dish) from dish d, single_order so where so.ref_dish=d.dish and ref_table = " + tableNumber + ";";

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dimension = rs.getInt("count(d.cod_dish)");
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
        return dimension;
    }

    /**
     * This method insert the dish list in the database
     */
    @Override
    public void insertDish(Dish dish) {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "INSERT INTO DISH VALUES(\"" + dish.getDish() + "\", \"" + dish.getDishCod() + "\", " + dish.getPrice() + ");";

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
     * This method delete the dish from the database
     */
    @Override
    public void deleteDish(String dish) {
        try {
            Class.forName(DRIVER).getConstructor().newInstance();

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ristorante?", "root", "root");

            String sql = "DELETE FROM DISH WHERE dish = \"" + dish + "\";";

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
}