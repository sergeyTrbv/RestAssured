package data;

import lombok.Data;

/**
 * Класс {@code Authorization} представляет собой объект для хранения данных авторизации.
 * Он содержит электронную почту и пароль пользователя.
 *
 * @author SergeyTrbv
 */
@Data
public class Authorization {

    /**
     * Электронная почта пользователя.
     */
    private String email;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Конструктор для создания объекта {@code Authorization} с заданными электронной почтой и паролем.
     *
     * @param email    электронная почта пользователя
     * @param password пароль пользователя
     */
    public Authorization(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Конструктор для создания объекта {@code Authorization} только с электронной почтой.
     *
     * @param email электронная почта пользователя
     */
    public Authorization(String email) {
        this.email = email;
    }

    /**
     * Конструктор по умолчанию для создания объекта {@code Authorization}.
     */
    public Authorization() {
    }
}