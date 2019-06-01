package modul.administrator.dto;

import modul.administrator.model.Comment;

import java.util.Date;

public class CommentDTO {

    private Long id;
    private String text;
    private Date dateOfPublication;
    private Boolean approved;

    public CommentDTO(Long id, String text, Date dateOfPublication, Boolean approved){
        this.setId(id);
        this.setText(text);
        this.setDateOfPublication(dateOfPublication);
        this.setApproved(approved);
    }

    public CommentDTO(Comment comment){
        this(comment.getId(),comment.getText(),comment.getDateOfPublication(),comment.isApproved());
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
}
