package data;

import lombok.Data;

/**
 * Класс {@code DataUserDto} представляет собой объект передачи данных (DTO) для пользователя.
 *
 * @author SergeyTrbv
 */
@Data
public class DataUserDto {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Integer id;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Имя пользователя.
     */
    private String first_name;

    /**
     * Фамилия пользователя.
     */
    private String last_name;

    /**
     * URL аватара пользователя.
     */
    private String avatar;

    /**
     * Конструктор без аргументов для создания объекта {@code DataUserDto}.
     */
    public DataUserDto() {
    }

    /**
     * Параметризованный конструктор для DataUserDto.
     *
     * @param id         Уникальный идентификатор пользователя.
     * @param email      Адрес электронной почты пользователя.
     * @param first_name Имя пользователя.
     * @param last_name  Фамилия пользователя.
     * @param avatar     URL аватара пользователя.
     */
    public DataUserDto(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }
}

