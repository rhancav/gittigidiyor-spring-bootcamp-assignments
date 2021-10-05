package dev.schoolmanagement.utility;

import dev.schoolmanagement.exceptions.StudentAgeNotValidException;
import org.mapstruct.Mapper;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UtilityMethods {
    /**Validates the students age and throws
     *{@link StudentAgeNotValidException} if it is
     * greater then 40 and smaller then 18.
     * @param birthDay of the student with the {@link LocalDate} type.
     */
    public static void validateAge(LocalDate birthDay){
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDay, currentDate).getYears();
        if (age < 18 || age > 40){
            throw new StudentAgeNotValidException("Students age must be greater then 18 and smaller then 40.");
        }
    }

    /**
     * Capitalizes the given string. The main purpose is to
     * make sure that all the user string inputs persisted with their
     * first char uppercased. So, no more "ugly" namings.
     * @param s String to be capitalized.
     * @return the capitalized string.
     */
    public static String uppercaseFirstChar(String s){
        return StringUtils.capitalize(s);
    }


}

