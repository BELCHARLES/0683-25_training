package com.generalutils.modelValidation;

import java.util.HashMap;
import java.util.Map;

import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.GeneralUtils;
import com.ztasks.jdbc.models.Nominee;

public class NomineeValidation {

    public static Map<String, String> validateNominee(Nominee nominee) throws InvalidArgumentException {
        Map<String, String> errorMap = new HashMap<>();

        String name = nominee.getName();
        int age = nominee.getAge();
        String relationship = nominee.getRelationship();

        try {
            GeneralUtils.validateTextField(name, "Name");
        } catch (ValidationException e) {
            errorMap.put("name", e.getMessage());
        }

        try {
            GeneralUtils.validateAge(age);
        } catch (ValidationException e) {
            errorMap.put("age", e.getMessage());
        }

        try {
            GeneralUtils.validateTextField(relationship, "Relationship");
        } catch (ValidationException e) {
            errorMap.put("relationship", e.getMessage());
        }

        return errorMap;
    }
}

