package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Ticket {

    private  int idTicket;
    private String nature;
    private String hour;

    public Ticket(int idTicket, String nature, String hour){
        this.idTicket = idTicket;
        this.nature = nature;
        this.hour = hour;
    }

    public ArrayList<Ticket> createTicketList(){
        ArrayList<Ticket> ticketsList = new ArrayList<Ticket>();
        String txtDate=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
            for(int i=0; i < 10; i++){
                ticketsList.add(new Ticket(i,"order",txtDate));
            }
          return ticketsList;
    }
}
