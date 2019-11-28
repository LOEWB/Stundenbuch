package model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderTicket extends AbstractTicket {

    private int clientId;
    private int articleId;

    public OrderTicket(int idTicket, LocalDateTime time, int clientId, int articleId) {
        super(idTicket, time);
        this.clientId = clientId;
        this.articleId = articleId;
    }

    public int getClientId() {
        return clientId;
    }
    public int getArticleId() {
        return articleId;
    }
}
