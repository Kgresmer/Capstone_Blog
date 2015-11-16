/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.validation;

/**
 *
 * @author apprentice
 */
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author warde
 */

// #1: Mark this class as advice that should be applied to Controller components
@ControllerAdvice
public class RestValidationHandler {

    // #2: Specify which exception this handler can handle
    @ExceptionHandler(MethodArgumentNotValidException.class)
    
    // #3: Specify the Http Status code to return when an error occurs
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    
    // #4: Let Spring know that we have something to return in the body of the
    // response. In this case it will be a ValidationErrorContainer containing
    // a ValidationError object for each field that did not pass validation.
    @ResponseBody
    public ValidationErrorContainer
            processValidationErrors(MethodArgumentNotValidException e) {
        // #5: get the Binding Result and all field errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // #6: Create a new ValidationError for each fieldError in the Binding Result
        ValidationErrorContainer errors = new ValidationErrorContainer();
        for (FieldError currentError : fieldErrors) {
            errors.addValidationError(currentError.getField(),
                    currentError.getDefaultMessage());
        }
        return errors;
    }
}
