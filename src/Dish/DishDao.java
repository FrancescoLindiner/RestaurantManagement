package Dish;

public interface DishDao {
    
    public Dish[] dishList();

    public int dimensionDishList();

    public void insertDish(Dish p);

    public void deleteDish(String dish);
}