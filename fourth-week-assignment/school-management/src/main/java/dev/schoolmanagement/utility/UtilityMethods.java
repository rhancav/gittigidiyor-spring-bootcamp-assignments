package dev.schoolmanagement.utility;

import dev.schoolmanagement.exceptions.StudentAgeNotValidException;

import java.time.LocalDate;
import java.time.Period;

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
}

