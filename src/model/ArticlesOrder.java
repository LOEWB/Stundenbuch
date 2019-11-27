package model;

public class ArticlesOrder {

    private int articleId;
    private int quantity;
    private int price;

    public ArticlesOrder(int articleId, int quantity, int price) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.price = price;
    }
}
