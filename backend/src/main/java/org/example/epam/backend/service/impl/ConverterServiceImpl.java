package org.example.epam.backend.service.impl;

import org.example.epam.backend.service.ConverterService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * ConvertService interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    public Float convertFromStringToFloat(String name) {
        return Float.parseFloat(name);
    }

    @Override
    public LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date);
    }
}
