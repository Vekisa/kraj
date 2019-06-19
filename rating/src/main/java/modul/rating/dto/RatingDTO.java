package modul.rating.dto;

import modul.rating.model.Object;
import modul.rating.model.Rating;
import modul.rating.model.RegisteredUser;

public class RatingDTO {
    private Long id;
    private int mark;
    private ObjectDTO object;
    private RegisteredUserDTO registeredUser;

    public RatingDTO(Long id, int mark, Object object, RegisteredUser registeredUser){
        this.setId(id);
        this.setMark(mark);
        this.setObject(new ObjectDTO(object));
        this.setRegisteredUser(new RegisteredUserDTO(registeredUser));
    }

    public RatingDTO(Rating rating){
        this(rating.getId(),rating.getMark(),rating.getObject(),rating.getRegisteredUser());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }

    public RegisteredUserDTO getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUserDTO registeredUser) {
        this.registeredUser = registeredUser;
    }
}
