package model;

import java.time.LocalDateTime;
import java.util.List;

public class SupplyTicket extends AbstractTicket {

    private int supplierId;
    private ArticlesOrder articleOrder;

    public SupplyTicket(int idTicket, LocalDateTime time, int supplierId, ArticlesOrder articleOrder) {
        super(idTicket, time);
        this.supplierId = supplierId;
        this.articleOrder = articleOrder;
    }
}
