package modul.administrator.service;

import modul.administrator.dto.CommentDTO;
import modul.administrator.model.Comment;
import modul.administrator.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<CommentDTO> getNotApproved(){
        List<Comment> all = commentRepository.findAll();
        List<Comment> notApproved = new ArrayList<>();
        for(Comment comm : all)
            if(!comm.isApproved())
                notApproved.add(comm);

            return DTOList.comments(notApproved);
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public CommentDTO approve(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment does not exist!");

        comment.get().setApproved(true);
        commentRepository.save(comment.get());

        return new CommentDTO(comment.get());
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public CommentDTO forbid(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment does not exist!");

        comment.get().setApproved(false);
        commentRepository.save(comment.get());

        return new CommentDTO(comment.get());
    }
}
