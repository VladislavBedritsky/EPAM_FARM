package org.example.EPAM_FARM.backend.service.impl;

import org.example.EPAM_FARM.backend.service.ConverterService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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