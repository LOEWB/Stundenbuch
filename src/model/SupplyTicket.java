package model;

import java.time.LocalDateTime;

public class SupplyTicket extends AbstractTicket {

    private int supplierId;
    private ArticleSupply articleSupply;

    public SupplyTicket(int idTicket, LocalDateTime time, int supplierId, ArticleSupply articleSupply) {
        super(idTicket, time);
        this.supplierId = supplierId;
        this.articleSupply = articleSupply;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public ArticleSupply getArticleSupply() {
        return articleSupply;
    }
}
