package dev.rrlabs.hellorrlabs.config.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Configuration
public class ObjectMapperConfig {

    public static final String DATE_TIME_FORMATTER_WITH_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_TIME_FORMAT_YEAR_MONTH = "yyyyMM";
    public static final String DATE_TIME_FORMATTER_YEAR_MONTH_DAY = "yyyy-MM-dd";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(MapperFeature.USE_ANNOTATIONS, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(
                        (new JavaTimeModule())
                                .addDeserializer(LocalDateTime.class,
                                        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_WITH_MILLISECONDS)))
                                .addSerializer(LocalDateTime.class,
                                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_WITH_MILLISECONDS)))
                                .addSerializer(YearMonth.class,
                                        new YearMonthSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YEAR_MONTH)))
                                .addDeserializer(YearMonth.class,
                                        new YearMonthDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YEAR_MONTH)))
                                .addDeserializer(LocalDate.class,
                                        new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_YEAR_MONTH_DAY)))
                                .addSerializer(LocalDate.class,
                                        new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_YEAR_MONTH_DAY)))
                )
                .findAndRegisterModules();
    }

}
