package helpers;

import data.Authorization;
import org.testng.annotations.DataProvider;

/**
 * Класс {@code DataProviderTest} для предоставления данных для тестовых методов через DataProvider.
 *
 * @author SergeyTrbv
 */
public class DataProviderTest {

    /**
     * Метод предоставляет номер страницы для теста.
     *
     * @return Массив объектов, содержащий номер страницы
     */
    @DataProvider(name = "page" +
            "Number")
    public Object[] pageNumbers() {
        return new Object[]{2};
    }

    /**
     * Метод предоставляет данные для успешной авторизации.
     *
     * @return Массив объектов, содержащий объекты авторизации
     */
    @DataProvider(name = "successfulAuthorization")
    public Object[] correctLogIns() {
        return new Object[]{new Authorization(
                "eve.holt@reqres.in", "cityslicka")};
    }

    /**
     * Метод предоставляет данные для неуспешной авторизации из-за отсутствия пароля.
     *
     * @return Массив объектов, содержащий объекты авторизации
     */
    @DataProvider(name = "unSuccessfulAuthorization")
    public Object[] incorrect_log_ins() {
        return new Object[]{new Authorization("peter@klaven", "")};
    }

    /**
     * Метод предоставляет URL и ожидаемое количество тегов для теста.
     *
     * @return Двумерный массив объектов, содержащий URL и ожидаемое количество тегов
     */
    @DataProvider(name = "tagCountProvider")
    public Object[][] tagCountProvider() {
        return new Object[][]{
                {"https://gateway.autodns.com/", 14}
        };
    }
}
