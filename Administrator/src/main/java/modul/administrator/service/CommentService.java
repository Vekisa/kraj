package modul.administrator.service;

import modul.administrator.dto.CommentDTO;
import modul.administrator.model.Comment;
import modul.administrator.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO approve(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment does not exist!");

        comment.get().setApproved(true);
        commentRepository.save(comment.get());

        return new CommentDTO(comment.get());
    }

    public CommentDTO forbid(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment does not exist!");

        comment.get().setApproved(false);
        commentRepository.save(comment.get());

        return new CommentDTO(comment.get());
    }
}
