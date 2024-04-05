package ru.naumen.collection.task2;

import java.util.HashMap;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {
    /**
     * Количество купленных билетов. В данной задаче необходимо только для определения id билета.
     */
    private long ticketsAmount = 0;

    /**
     * Выбрана коллекция HashMap, т. к. необходимо по id билета (ключ) получать тип товара,
     * временная сложность получения элемента по ключу - O(1).
     * Функции {@link Long#hashCode()} и {@link Long#equals} реализованы внутри класса {@link Long}.
     * В рамках задачи необходимо быстро получать тип товара по билету, поэтому коллекции HashMap более чем достаточно.
     * Дополнительные опции, которые предлагают другие реализации интерфейса {@link Map} не требуются.
     * Тем более они несут в себе дополнительные расходы в виде увеличения сложности операций/памяти.
     */
    private final Map<Long, ComboLunchType> purchasedComboLunches = new HashMap<>();

    /**
     * Купить билет
     * @param client имя клиента
     * @return билет
     */
    public Ticket buyTicket(String client)
    {
        long id = ticketsAmount++;
        return new Ticket(id, client);
    }

    /**
     * Докупить набор еды к билету
     * @param ticket билет, к которому нужно докупить набор еды
     * @param comboLunch набор еды
     */
    public void buyComboLunch(Ticket ticket, ComboLunchType comboLunch)
    {
        purchasedComboLunches.put(ticket.getId(), comboLunch);
    }

    /**
     * Получить набор еды, купленный к билету.
     * Основной метод задачи. Сложность получения набора еды по id билета равна O(1).
     * @param ticket билет, по которому необходимо найти товары
     */
    public ComboLunchType getFoodByTicket(Ticket ticket)
    {
        return purchasedComboLunches.get(ticket.getId());
    }
}
