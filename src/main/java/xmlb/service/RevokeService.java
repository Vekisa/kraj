package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlb.model.Revoke;
import xmlb.repository.RevokeRepository;


import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class RevokeService {
    @Autowired
    private RevokeRepository revokeRepository;

    public void newRevoke(Revoke revoke) {
        revokeRepository.save(revoke);
    }

    public List<Revoke> getAll(){ return revokeRepository.findAll();}
    public Collection<Revoke> findByAlias(String alias){ return revokeRepository.findByAlias(alias);}
}
