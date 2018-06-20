package theater.rest;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import theater.domain.entities.EntityBase;
import theater.domain.exceptions.BaseHttpException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class ControllerBase<E extends EntityBase, T extends JpaRepository<E, Long>> {
    protected final T repository;

    protected ControllerBase(T repository) {
        this.repository = repository;
    }

    @ExceptionHandler(BaseHttpException.class)
    public ModelAndView handleError(HttpServletRequest req, BaseHttpException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, E> getAll() {
        var entities = repository.findAll();
        var response = new HashMap<String, E>();

        for (var entity : entities) {
            response.put(entity.getId().toString(), entity);
        }
        return response;
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    E getFirst() {
        return repository.findAll().get(0);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Long createOrUpdateFirst(@RequestBody E newEntity) {
        var entities = repository.findAll();
        if (entities.isEmpty()) {
            create(newEntity);
            return newEntity.getId();
        } else {
            var entity = entities.get(0);
            entity.update(newEntity);
            repository.save(entity);
            return entity.getId();
        }
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<Long, String> getNames() {
        var results = repository.findAll(new Sort(Sort.Direction.ASC, "id"));
        var response = new HashMap<Long, String>();

        results.forEach(e -> response.put(e.getId(), e.stringValue()));

        return response;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    E create(@RequestBody E entity) {
        repository.save(entity);
        return entity;
    }

    @RequestMapping(value = "/delete/{entityId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void delete(@PathVariable Long entityId) {
        repository.delete(repository.findById(entityId).orElseThrow());
    }

    @RequestMapping(value = "/id/{entityId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    E getSpecific(@PathVariable Long entityId) {
        return repository.findById(entityId).orElseThrow();
    }

    @RequestMapping(value = "/id/{entityId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void update(@RequestBody E newEntity, @PathVariable Long entityId) {
        var old = repository.findById(entityId).orElseThrow();
        old.update(newEntity);
        repository.save(old);
    }
}