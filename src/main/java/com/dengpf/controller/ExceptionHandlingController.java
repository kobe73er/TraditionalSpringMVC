package com.dengpf.controller;

import com.dengpf.Exception.SupportInfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

@Controller
@RequestMapping("/local")
public class ExceptionHandlingController {

    protected Logger logger;

    public ExceptionHandlingController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @RequestMapping("/dataIntegrityViolation")
    String throwDataIntegrityViolationException() throws SQLException {
        logger.info("Throw DataIntegrityViolationException");
        throw new DataIntegrityViolationException("Duplicate id");
    }

    /**
     * Always throws a <tt>SupportInfoException</tt>. Must be caught by an
     * exception handler.
     *
     * @return Nothing - it always throws the exception.
     * @throws SupportInfoException Always thrown.
     */
    @RequestMapping("/supportInfoException")
    String throwCustomException() throws Exception {
        logger.info("Throw SupportInfoException");
        throw new SupportInfoException("Custom exception occurred");
    }


    /**
     * Convert a predefined exception to an HTTP Status code
     */
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        logger.error("Request raised a DataIntegrityViolationException");
        // Nothing to do
    }

    /**
     * Demonstrates how to take total control - setup a model, add useful
     * information and return the "support" view name. This method explicitly
     * creates and returns
     *
     * @param req       Current HTTP request.
     * @param exception The exception thrown - always {@link SupportInfoException}.
     * @return The model and view used by the DispatcherServlet to generate
     * output.
     * @throws Exception
     */
    @ExceptionHandler(SupportInfoException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception)
            throws Exception {

        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        logger.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date().toString());
        mav.addObject("status", 500);

        mav.setViewName("support");
        return mav;
    }

    @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
    public String redirect() {

        return "redirect:/pages/dengpf.html";
    }
}