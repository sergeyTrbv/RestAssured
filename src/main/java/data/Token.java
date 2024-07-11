package data;

import lombok.Data;

/**
 * Класс {@code Token} для представления токена аутентификации.
 *
 * @author SergeyTrbv
 */
@Data
public class Token {

    private String token;

    /**
     * Конструктор по умолчанию
     */
    public Token(String token) {
        this.token = token;
    }

    /**
     * Конструктор без аргументов для созданя объекта {@code Token}.
     */
    public Token() {
    }
}
