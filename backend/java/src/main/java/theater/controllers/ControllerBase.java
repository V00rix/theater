package theater.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import theater.domain.entities.EntityBase;
import theater.domain.exceptions.BaseHttpException;

import javax.servlet.http.HttpServletRequest;

public abstract class ControllerBase<R extends EntityBase, T extends JpaRepository<R, Long>> {

    public abstract T repository();

    @ExceptionHandler(BaseHttpException.class)
    public ModelAndView handleError(HttpServletRequest req, BaseHttpException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    R createNew(@RequestBody R entity) {
        repository().save(entity);
        return entity;
    }
}