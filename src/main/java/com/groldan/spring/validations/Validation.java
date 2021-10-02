package com.groldan.spring.validations;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.groldan.spring.exceptions.ErrorException;
import com.groldan.spring.exceptions.ValidationException;

public class Validation {
	  private static final String EMAIL_REGEX = "[^@]+@[^.]+\\..+[a-zA-Z]{1,}";
	  private static final int EMAIL_MIN = 6;
	  private static final int EMAIL_MAX = 60;
	  private static final int PASSWORD_MIN = 4;
	  private static final int PASSWORD_MAX = 12;
	  private static final String UPPERCASE = "[A-Z]{1}+";
	  private static final String LOWERCASE = "[a-z]+";
	  private static final String NUMBER = "[0-9]{2}+";
	  private static final String CHARACTERS_ALLOWED_NO_SPACE = "[a-zA-Z0-9]+";
	  private static final String VALIDATION_ERROR = "El valor %s es inválido";

	  private Validation() {
	  }

	  /**
	   * A Validation method to validate string input matches all regex patterns
	   *
	   * @param input the input filed to validate
	   * @param caller the class instance invoking this method
	   * @param regex array of string patterns to test against
	   */
	  private static void contains(String input, Object caller, String... regex) {
	    Pattern pattern;
	    isValidNonEmpty(input, caller);
	    for (String aRegex : regex) {
	      pattern = Pattern.compile(aRegex);
	      if (!pattern.matcher(input).find()) {
	        handleValidationException(caller, input);
	      }
	    }
	  }

	  /**
	   * Validate value between max and min
	   *
	   * @param input the input value to be validated
	   * @param min optional - the minimum value inclusive
	   * @param max optional - the maximum value inclusive
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidMaxMin(
	      BigDecimal input, BigDecimal min, BigDecimal max, Object caller) {
	    isValidNotNull(input, caller);
	    if (min != null && input.compareTo(min) < 0) {
	      handleValidationException(caller, input);
	    }
	    if (max != null && input.compareTo(max) > 0) {
	      handleValidationException(caller, input);
	    }
	  }

	  /**
	   * Validates that a value is not null
	   *
	   * @param input the value to validate
	   * @param caller the class instance invoking this method
	   */
	  private static void isValidNotNull(Object input, Object caller) {
	    if (input == null) {
	      handleValidationException(caller, input);
	    }
	  }

	  /**
	   * Validates that a String value is not null or empty
	   *
	   * @param input the String to validate
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidNonEmpty(String input, Object caller) {
	    isValidNotNull(input, caller);
	    if (input.isEmpty()) {
	      handleValidationException(caller, input);
	    }
	  }

	  /**
	   * Validate length between max and min
	   *
	   * @param input the input value to be validated
	   * @param min the minimum value inclusive
	   * @param max the maximum value inclusive
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidLength(String input, int min, int max, Object caller) {
	    isValidNonEmpty(input, caller);
	    int inputD = input.length();
	    if (inputD < min || inputD > max) {
	      handleValidationException(caller, input);
	    }
	  }

	  /**
	   * Validate that input is matching specific length bounds and format
	   *
	   * @param input the input value to be validated
	   * @param minLength the minimum length inclusive
	   * @param maxLength the maximum length inclusive
	   * @param regex the regular expression to use for the check
	   * @param caller the class instance invoking this method
	   */
	  private static void isValid(
	      String input, int minLength, int maxLength, String regex, Object caller) {
	    isValidLength(input, minLength, maxLength, caller);
	    if (!Pattern.compile(regex).matcher(input).matches()) {
	      handleValidationException(caller, input);
	    }
	  }

	  /**
	   * Validate email format
	   *
	   * @param email the email address to validate
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidEmail(String email, Object caller) {
	    isValid(email, EMAIL_MIN, EMAIL_MAX, EMAIL_REGEX, caller);
	  }

	
	  /**
	   * Validate that input is a string matching specific length bounds
	   *
	   * @param input the input value to be validated
	   * @param minLength the minimum length inclusive
	   * @param maxLength the maximum length inclusive
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidString(String input, int minLength, int maxLength, Object caller) {
	    isValid(input, minLength, maxLength, CHARACTERS_ALLOWED_NO_SPACE, caller);
	  }

	  /**
	   * Validate that input is a string matching password requirements
	   *
	   * @param input the input value to be validated
	   * @param caller the class instance invoking this method
	   */
	  public static void isValidPassword(String input, Object caller) {
	    isValidString(input, PASSWORD_MIN, PASSWORD_MAX, caller);
	    contains(input, caller, UPPERCASE, LOWERCASE, NUMBER);
	  }

	  /**
	   * Throws and logs a {@link ValidationException} for validation failure
	   *
	   * @param caller the class instance invoking this method
	 * @throws ValidationException 
	   */
	  public static void handleValidationException(Object caller, Object input){
		   ValidationException errorException = new ValidationException(
	       String.format(VALIDATION_ERROR, input), "0");	
	    throw errorException;
	  }	 
}
