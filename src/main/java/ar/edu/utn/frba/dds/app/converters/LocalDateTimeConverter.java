package ar.edu.utn.frba.dds.app.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDate) {
    return localDate == null? null: Timestamp.valueOf(localDate);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp date) {
    return date == null? null : date.toLocalDateTime();
  }
}
