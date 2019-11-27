package model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderTicket extends AbstractTicket {

    private int clientId;
    private List<Integer> articlesIds;

    public OrderTicket(int idTicket, LocalDateTime time, int clientId, List<Integer> articlesIds) {
        super(idTicket, time);
        this.clientId = clientId;
        this.articlesIds = articlesIds;
    }
}
