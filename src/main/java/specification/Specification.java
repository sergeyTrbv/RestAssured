package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Класс {@code Specification} для настройки спецификаций запросов и ответов для тестирования API.
 *
 * @author SergeyTrbv
 */
public class Specification {

    /**
     * Метод для создания спецификации запроса с базовым URI и типом содержимого JSON.
     *
     * @return RequestSpecification спецификация запроса
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Метод для создания спецификации ответа с ожидаемым статус-кодом 201.
     *
     * @return ResponseSpecification спецификация ответа
     */
    public static ResponseSpecification responseSpec201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    /**
     * Метод для создания спецификации ответа с ожидаемым статус-кодом 200.
     *
     * @return ResponseSpecification спецификация ответа
     */
    public static ResponseSpecification responseSpec400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    /**
     * Метод для установки спецификации запроса.
     *
     * @param requestSpec спецификация запроса
     */
    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }


    /**
     * Метод для установки спецификации ответа.
     *
     * @param responseSpec спецификация ответа
     */
    public static void installSpec(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Метод для установки спецификации запроса и ответа.
     *
     * @param requestSpec  спецификация запроса
     * @param responseSpec спецификация ответа
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Метод для удаления установленных спецификаций запроса и ответа.
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }

}
