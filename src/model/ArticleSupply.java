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

    public int getArticleId() {
        return articleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
