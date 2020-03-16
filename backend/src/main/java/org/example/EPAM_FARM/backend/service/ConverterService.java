package org.example.EPAM_FARM.backend.service;

import java.time.LocalDate;

public interface ConverterService {

    Float convertFromStringToFloat(String name);

    LocalDate convertStringToLocalDate(String date);

}
