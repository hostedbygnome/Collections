package ru.naumen.collection.task1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     * Выбраны коллекции:
     * ArrayList, т. к. выгоднее изначально выделить необходимую память под дубликаты - это позволит
     * осуществлять вставку быстрее O(1).
     * HashSet для быстрого поиска элемента O(1) и исключения дубликатов.
     * Сложность метода по времени O(n), т. к. необходимо пройти все элементы коллекции collA/collB
     * Сложность метода по памяти O(n), т. к. необходимо преобразовать коллекцию collB во множество.
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        List<User> result = new ArrayList<>(Math.min(collA.size(), collB.size()));
        Set<User> collBSet = new HashSet<>(collB);
        for (User user : collA)
        {
            if (collBSet.contains(user))
            {
                result.add(user);
            }
        }
        return result;
    }
}
