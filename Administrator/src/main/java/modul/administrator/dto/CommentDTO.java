package modul.administrator.dto;

import modul.administrator.model.Comment;
import modul.administrator.model.RegisteredUser;
import modul.administrator.model.Unit;

import java.util.Date;

public class CommentDTO {

    private Long id;
    private String text;
    private Date dateOfPublication;
    private Boolean approved;
    private UnitDTO unit;
    private RegisteredUserDTO registeredUser;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String text, Date dateOfPublication, Boolean approved, Unit unit, RegisteredUser registeredUser){
        this.setId(id);
        this.setText(text);
        this.setDateOfPublication(dateOfPublication);
        this.setApproved(approved);
        this.setUnit(new UnitDTO(unit));
        this.setRegisteredUser(new RegisteredUserDTO(registeredUser));
    }

    public CommentDTO(Comment comment){
        this(comment.getId(),comment.getText(),comment.getDateOfPublication(),comment.isApproved(),comment.getUnit(),comment.getRegisteredUser());
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

    public RegisteredUserDTO getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUserDTO registeredUser) {
        this.registeredUser = registeredUser;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }
}
