package com.example.demo.constants;

public class ExceptionMessages {

    public static final String emailValidationMessage = "Invalid email. It should be in the format: <user>@<domain>.<tld>";
    public static final String letterValidationMessage = "Invalid input. Only letters , dashes and spaces allowed.";
    public static final String notBlankMessage = "The field must not be empty. Please fill it!";

    public static final String dateValidMessage = "Date must be before today.";

    public static final String ageValidMessage = "You must be over 14 to be able to register";

    public static final String passwordValidationMessage = "Invalid password: it should be at least 6 characters long, contain at least one digit, one special symbol, one lowercase letter and one uppercase letter";

    public static final String iDnotFoundExceptionMessage = "ID NOT FOUND";

    public static final String nameNotFoundExceptionMessage = "NAME NOT FOUND";

    public static final String postTypeNotFoundMessage = "TYPE NOT FOUND";

    public static final String organisationAlreadyExistMessage = "There is already a registered organisation with email ";

    public static final String emailOrganisationAlreadyExists = "DUPLICATED EMAIL. Insert another";

    public static final String postAlreadyExistMessage = "There is already duplicated post with name ";

    public static final String emptyResult = "No result";

}
