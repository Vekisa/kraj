package modul.administrator.dto;

import modul.administrator.model.Comment;
import modul.administrator.model.Object;
import modul.administrator.model.RegisteredUser;

import java.util.Date;

public class CommentDTO {

    private Long id;
    private String text;
    private Date dateOfPublication;
    private Boolean approved;
    private ObjectDTO objectDTO;
    private RegisteredUserDTO registeredUserDTO;

    public CommentDTO(Long id, String text, Date dateOfPublication, Boolean approved, Object object, RegisteredUser registeredUser){
        this.setId(id);
        this.setText(text);
        this.setDateOfPublication(dateOfPublication);
        this.setApproved(approved);
        this.setObjectDTO(new ObjectDTO(object));
        this.setRegisteredUserDTO(new RegisteredUserDTO(registeredUser));
    }

    public CommentDTO(Comment comment){
        this(comment.getId(),comment.getText(),comment.getDateOfPublication(),comment.isApproved(),comment.getObject(),comment.getRegisteredUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public ObjectDTO getObjectDTO() {
        return objectDTO;
    }

    public void setObjectDTO(ObjectDTO objectDTO) {
        this.objectDTO = objectDTO;
    }

    public RegisteredUserDTO getRegisteredUserDTO() {
        return registeredUserDTO;
    }

    public void setRegisteredUserDTO(RegisteredUserDTO registeredUserDTO) {
        this.registeredUserDTO = registeredUserDTO;
    }
}
