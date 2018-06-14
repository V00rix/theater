package theater.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import theater.domain.exceptions.BaseHttpException;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
public abstract class ControllerBase {

    @ExceptionHandler(BaseHttpException.class)
    public ModelAndView handleError(HttpServletRequest req, BaseHttpException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}