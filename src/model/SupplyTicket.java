package model;

import java.time.LocalDateTime;
import java.util.List;

public class SupplyTicket extends AbstractTicket {

    private int supplierId;
    private List<ArticlesOrder> articlesOrders;

    public SupplyTicket(int idTicket, LocalDateTime time, int supplierId, List<ArticlesOrder> articlesOrders) {
        super(idTicket, time);
        this.supplierId = supplierId;
        this.articlesOrders = articlesOrders;
    }
}
