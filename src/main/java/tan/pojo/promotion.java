package tan.pojo;

public class promotion {
    private String id;
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public String getId() {
        return id;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setId(String id) {
        this.id = id;
    }
    public promotion(){}

    public promotion(String id, int dis){
        this.id=id;
        this.discount=dis;
    }
    @Override
    public String toString() {
      
        return String.valueOf(this.discount);
    }

}
