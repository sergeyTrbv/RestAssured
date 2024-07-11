package data;

import lombok.Data;

/**
 * Класс {@code ColorDto} представляет собой объект передачи данных (DTO) для информации о цвете.
 * Он содержит уникальный идентификатор, название цвета, год, когда цвет был популярен или представлен,
 * шестнадцатеричное представление цвета и значение Pantone.
 *
 * @author ВашеИмя
 */
@Data
public class ColorDto {

    /**
     * Уникальный идентификатор цвета.
     */
    private Integer id;

    /**
     * Название цвета.
     */
    private String name;

    /**
     * Год, когда цвет был популярен или представлен.
     */
    private Integer year;

    /**
     * Шестнадцатеричное представление цвета.
     */
    private String color;

    /**
     * Значение Pantone, связанное с цветом.
     */
    private String pantone_value;

    /**
     * Конструктор для создания объекта {@code ColorDto} с заданными параметрами.
     *
     * @param id            уникальный идентификатор цвета
     * @param name          название цвета
     * @param year          год, когда цвет был популярен или представлен
     * @param color         шестнадцатеричное представление цвета
     * @param pantone_value значение Pantone, связанное с цветом
     */
    public ColorDto(Integer id, String name, Integer year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    /**
     * Конструктор по умолчанию для создания объекта {@code ColorDto}.
     */
    public ColorDto() {
    }
}
