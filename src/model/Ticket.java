package model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

abstract class AbstractTicket {

    private int idTicket;
    private LocalDateTime time;

    public AbstractTicket(int idTicket, LocalDateTime time){
        this.idTicket = idTicket;
        this.time = time;
    }

    public int getIdTicket(){ return idTicket; }
    public LocalDateTime getTime(){ return time; }
}
