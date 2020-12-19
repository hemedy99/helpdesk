package egoz.go.tz.helpdesk.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egoz.go.tz.helpdesk.enums.RequestCategoryEnum;
import egoz.go.tz.helpdesk.enums.RequestPriorityEnum;
import egoz.go.tz.helpdesk.enums.RequestStatusEnum;
import egoz.go.tz.helpdesk.models.Request;
import egoz.go.tz.helpdesk.repository.RequestRepository;

@Service
public class RequestService {
  @Autowired
  private RequestRepository requestRepo; 

  @PersistenceContext
    private EntityManager entityManager;
  
  public List<Request> getAllRequests(RequestStatusEnum requestStatus, RequestPriorityEnum requestPriority,RequestCategoryEnum requestCategory, LocalDateTime fromDate,LocalDateTime toDate, int page, int size) {
    //return requestRepo.findAll(pagerequest).getContent();
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Request> query = cb.createQuery(Request.class);
    Root<Request> request = query.from(Request.class);
    
    Path<String> statusPath = request.get("requestStatus");
    Path<String> priorityPath = request.get("requestPriority");
    Path<String> categoryPath = request.get("requestCategory");
    Path<LocalDateTime> createdDatePath = request.get("createdDate");

    List<Predicate> predicates = new ArrayList<>();
    if(requestStatus != null )
    predicates.add(cb.like(statusPath, "%"+requestStatus+"%"));
    if(requestPriority != null )
    predicates.add(cb.like(priorityPath, "%"+requestPriority+"%"));
    if(requestCategory != null )
    predicates.add(cb.like(categoryPath, "%"+requestCategory+"%"));
    if(fromDate != null && toDate != null)
    predicates.add(cb.between(createdDatePath, cb.literal(fromDate), cb.literal(toDate)));
    if(predicates.isEmpty()) {
        query.select(request);
    }else {
        query.select(request)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
    
    }
    TypedQuery<Request> typedQuery = entityManager.createQuery(query);
    typedQuery.setFirstResult(page);
    typedQuery.setMaxResults(size);
    return typedQuery.getResultList();

}

public List<Request> getAllRequest(Pageable pagerequest) {
  return requestRepo.findAll(pagerequest).getContent();
  }

   public Request saveRequest(@Valid Request request)  {
    return requestRepo.save(request);
    }

    // public List<Request> getRequestByStatus(RequestStatusEnum requestStatus) {
    //     return requestRepo.findAllByStatus(requestStatus);
    //     }

}
