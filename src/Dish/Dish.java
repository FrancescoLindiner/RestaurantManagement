package Dish;

public class Dish {
    
    private String dish;
    private String codDish;
    private double price;
    
    public Dish(String dish, String codDish, double price) {
        this.dish = dish;
        this.codDish = codDish;
        this.price = price;
    }

    public String getDishCod() {
        return codDish;
    }

    public String getDish() {
        return dish;
    }

    public double getPrice() {
        return price;
    }

    public void setDishCod(String codDish) {
        this.codDish = codDish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}