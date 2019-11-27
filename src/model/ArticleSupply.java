package model;

public class ArticleSupply {

    private int articleId;
    private int quantity;
    private int price;
    
    
    public ArticleSupply(int articleId, int quantity, int price) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.price = price;
    }

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
    
    
}
