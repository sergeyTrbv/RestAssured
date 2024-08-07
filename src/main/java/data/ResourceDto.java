package data;

import lombok.Data;

import java.util.List;

/**
 * Класс {@code ResourceDto} представляет собой объект передачи данных (DTO) для ресурсов.
 * Он содержит информацию о странице, количестве элементов на странице, общем количестве элементов,
 * общем количестве страниц, списке данных пользователей и информации о поддержке.
 *
 * @author SergeyTrbv
 */
@Data
public class ResourceDto {

    /**
     * Номер текущей страницы.
     */
    private Integer page;

    /**
     * Количество элементов на странице.
     */
    private Integer per_page;

    /**
     * Общее количество элементов.
     */
    private Integer total;

    /**
     * Общее количество страниц.
     */
    private Integer total_pages;

    /**
     * Список данных пользователей.
     */
    private List<DataUserDto> data;

    /**
     * Информация о поддержке.
     */
    private SupportDto support;


    /**
     * Конструктор для создания объекта {@code ResourceDto} с заданными параметрами.
     *
     * @param page        номер текущей страницы
     * @param per_page    количество элементов на странице
     * @param total       общее количество элементов
     * @param total_pages общее количество страниц
     * @param data        список данных пользователей
     * @param support     информация о поддержке
     */
    public ResourceDto(Integer page, Integer per_page, Integer total, Integer total_pages,
                       List<DataUserDto> data, SupportDto support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    /**
     * Конструктор без аргументов для создания объекта {@code ResourceDto}.
     */
    public ResourceDto() {
    }
}
