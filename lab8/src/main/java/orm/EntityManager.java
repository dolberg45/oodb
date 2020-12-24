package orm;

public interface EntityManager {

    /**
     * Метод сохраняет в БД объект var1
     */
    void persist(Object var1) throws Exception;

    /**
     * Метод обновляет в БД данные, соответствующие объекту
     */
    Object merge(Object var1);

    /**
     * Метод удаляет объект из БД
     */
    void remove(Object var1);

    /**
     * Метод запрашивает из базы данных информацию, соответствующую первичному ключу var2.
     * Полученные данные инициализируют объект типа Class<T>
     * Метод возвращает созданный объект
     * @param object - тип создаваемого объекта
     * @param var - значение первичного ключа
     */
    Object find(Class<?> object, Object var);

    void refresh(Object var1);

}
