package modul.backend.service;

import modul.backend.dto.UnitDTO;
import modul.backend.model.Comment;
import modul.backend.model.Plan;
import modul.backend.model.PriceSchedule;
import modul.backend.model.Unit;
import modul.backend.repository.CommentRepository;
import modul.backend.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private CommentRepository commentRepository;

    public UnitDTO findById(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist!");

        return new UnitDTO(unit.get());
    }

    public List<UnitDTO> getAll(){
        return DTOList.units(unitRepository.findAll());
    }

    public List<Comment> getComments(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist!");

        List<Comment> comments = commentRepository.getForUnit(id);

        return comments;
    }

    public List<Plan> getPlanForYear(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit does not exit!");

        PriceSchedule pS = null;
        if(!unit.get().getPriceSchedule().isEmpty()){
            System.out.println("NISAM PRAZAN");
            pS = unit.get().getPriceSchedule().get(0);
            for(PriceSchedule priceSchedule : unit.get().getPriceSchedule())
                if(pS.getMade().before(priceSchedule.getMade()))
                    pS = priceSchedule;

            List<Plan> plans = new ArrayList<>();

            for(Plan p : pS.getPlan()) {
                System.out.println("POREDIM: " + p.getToDate() + " " + new Date());
                if (p.getToDate().after(new Date())) {
                    System.out.println("UBACIO");
                    plans.add(p);
                }
            }

            return plans;
        }

        return new ArrayList<>();

    }
}
