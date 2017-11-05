/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.java.coursework.utils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import io.swagger.annotations.Contact;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueEmailException;

/**
 * <p>This class provides methods to check Contact objects against arbitrary requirements.</p>
 *
 * @author Joshua Wilson
 * @see Contact
 * @see ContactRepository
 * @see javax.validation.Validator
 */
public class CustomerValidator {
	@Inject
    private Validator validator;


    /**
     * <p>Validates the given Contact object and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.<p/>
     *
     *
     * <p>If the error is caused because an existing contact with the same email is registered it throws a regular validation
     * exception so that it can be interpreted separately.</p>
     *
     *
     * @param contact The Contact object to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException If contact with the same email already exists
     */
    public    void validateContact(Customer customer) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        	
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

     
    }

   
}
