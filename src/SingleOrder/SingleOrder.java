package SingleOrder;

import Dish.Dish;

public class SingleOrder{
    
    private int codSingleOrder;
    private int refTable;
    private Dish refDish;

    public SingleOrder(int codSingleOrder, int refTable, Dish dish) {
        this.codSingleOrder=codSingleOrder;
        this.refTable = refTable;
        this.refDish = dish;
    }

    public int getCodSingleOrder(){
        return codSingleOrder;
    }

    public int getRefTable() {
        return refTable;
    }

    public Dish getRefDish() {
        return refDish;
    }

    public void setCodSingleOrder(int codSingleOrder){
        this.codSingleOrder=codSingleOrder;
    }

    public void setRefTable(int refTable) {
        this.refTable = refTable;
    }

    public void setRefDish(Dish refDish) {
        this.refDish = refDish;
    }
}