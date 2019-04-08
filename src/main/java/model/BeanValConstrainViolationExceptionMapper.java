package model;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BeanValConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        System.out.println("BeanValConstrainViolationExceptionMapper in action");

        ConstraintViolation cv = (ConstraintViolation) e.getConstraintViolations().toArray()[0];
        //oh yeah... you need to shell out some $$$ !
        return Response.status(Response.Status.PAYMENT_REQUIRED)
                .entity(cv)
                .build();
    }

}