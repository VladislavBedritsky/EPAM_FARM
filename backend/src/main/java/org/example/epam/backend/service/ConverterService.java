package org.example.epam.backend.service;

import java.time.LocalDate;

/**
 * interface ConverterService
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface ConverterService {

    /**
     * Get float from string.
     *
     * @param name String which need to be converted to float
     * @return float
     */
    Float convertFromStringToFloat(String name);

    /**
     * Get LocalDate from string.
     *
     * @param date String which need to be converted to LocalDate
     * @return LocalDate
     */
    LocalDate convertStringToLocalDate(String date);

}
