package com.example.projectwebjava.validator;

import com.example.projectwebjava.constraint.ValidLogin;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class LoginConstraintValidator implements ConstraintValidator<ValidLogin, String> {
    @Override
    public void initialize(ValidLogin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/messages.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageResolver resolver = new PropertiesMessageResolver(props);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                new LengthRule(6, 20),
                new CharacterRule(PolishCharacterData.LowerCase, 1),
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(login));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
