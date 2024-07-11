package data;

import lombok.Data;

/**
 * Класс {@code ErrorResponse} представляет собой объект передачи данных (DTO) для ошибок.
 * Он содержит информацию об ошибке в виде строки.
 *
 * @author SergeyTrbv
 */
@Data
public class ErrorResponse {

    /**
     * Поле {@code error} содержит описание ошибки.
     */
    private String error;
}
