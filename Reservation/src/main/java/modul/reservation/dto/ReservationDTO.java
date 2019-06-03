package modul.reservation.dto;

import java.util.Date;
import java.util.List;

public class ReservationDTO {
    private Long id;
    private Date start;
    private Date end;
    private boolean confirmed;
    private Date possibleCancellationDate;
    private double price;
    private Long unit;
    private List<Long> includes;
    private Long registredUser;


}
