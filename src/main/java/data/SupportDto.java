package data;

import lombok.Data;

/**
 * Класс {@code SupportDto} представляет собой объект передачи данных (DTO) для информации о поддержке.
 * * Этот класс содержит URL для связи с поддержкой и текстовое описание информации о поддержке.
 *
 * @author SergeyTrbv
 */
@Data
public class SupportDto {

    /**
     * Поле {@code url} содержит URL для связи с поддержкой.
     */
    private String url;

    /**
     * Поле {@code text} содержит текстовое описание информации о поддержке.
     */
    private String text;


    /**
     * Конструктор без аргументов для создания объекта {@code SupportDto}.
     */
    public SupportDto() {
    }
}