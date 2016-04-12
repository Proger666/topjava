package ru.javawebinar.topjava.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Scorpa on 4/12/2016.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
     if (localDateTime != null){
         return java.sql.Timestamp.valueOf(localDateTime);
     }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(java.sql.Timestamp date) {
        if (Objects.nonNull(date)) {
            return date.toLocalDateTime();
        }
        return null;
    }
}
