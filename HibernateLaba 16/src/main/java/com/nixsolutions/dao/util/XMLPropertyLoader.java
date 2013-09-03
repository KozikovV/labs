package com.nixsolutions.dao.util;

/**
 * Загрузка свойств соединения.
 * 
 * @author zinchenko
 * 
 */
public interface XMLPropertyLoader {

    /**
     * Получение URL.
     * 
     * @return URL
     */
    public String getUrl();

    /**
     * Получение Driver.
     * 
     * @return Driver
     */
    public String getDriver();

    /**
     * Получение имени пользователя.
     * 
     * @return имя пользователя
     */
    public String getUsername();

    /**
     * Получение пароля.
     * 
     * @return пароль.
     */
    public String getPassword();

    /**
     * Установка пути к файту с свойствами.
     * 
     * @param arg0
     *            путь к файлу
     */
    public void setFileName(String arg0);

}
