package ru.naumen.collection.task2;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    public Ticket(long id, String client)
    {
        this.id = id;
        this.client = client;
    }

    public long getId()
    {
        return id;
    }
}
