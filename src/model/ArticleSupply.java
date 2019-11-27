package model;

public class ArticleSupply {

    private int articleId;
    private int quantity;
    private Double price;

    public ArticleSupply(int articleId, int quantity, Double price) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.price = price;
    }
}
