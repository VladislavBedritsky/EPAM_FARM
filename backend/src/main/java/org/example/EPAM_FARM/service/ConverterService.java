package org.example.EPAM_FARM.service;

import java.time.LocalDate;

public interface ConverterService {

    Float convertFromStringToFloat(String name);

    LocalDate convertStringToLocalDate(String date);
}
