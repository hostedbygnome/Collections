package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {
    private static final Logger LOG = Logger.getLogger(WarAndPeace.class.getName());
    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    private static final int FREQUENT_WORDS_COUNT = 10;
    private static final int RARE_WORDS_COUNT = 10;

    /** Выбраны коллекции:
     * HashMap для хранения слов и количества их повторений в тексте.
     * PriorityQueue для динамического поддержания 10 наиболее используемых (TOP) и 10 наименее
     * используемых (LAST) слов в тексте.
     * Сложность метода по времени O(n), где n - количество слов в тексте, т. к. необходимо
     * обработать каждое для подсчета количества.
     * Сложность метода по памяти O(m), где m - количество уникальных слов в тексте.
     * Объяснение:
     * Использование PriorityQueue позволяет оптимально поддерживать TOP 10 и LAST 10 слов -
     * за счет того, что сложность операции вставки в эту коллекцию обходится нам в log(10),
     * что является константой. В худшем случае таких операций будет m для TOP и m для LAST.
     * Итоговая сложность по времени: O(n + m * 2 * log(10)), что эквивалентно O(n)
     */
    public static void main(String[] args) {
        final Map<String, Integer> wordsCount = new HashMap<>();
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord(word -> wordsCount.merge(word, 1, Integer::sum));
        Queue<Entry<String, Integer>> frequentWords = new PriorityQueue<>(FREQUENT_WORDS_COUNT,
                Map.Entry.comparingByValue());
        Queue<Entry<String, Integer>> rareWords = new PriorityQueue<>(RARE_WORDS_COUNT,
                Collections.reverseOrder(Map.Entry.comparingByValue()));

        for (Entry<String, Integer> entry : wordsCount.entrySet())
        {
            boolean needAddFrequentWord = frequentWords.size() < FREQUENT_WORDS_COUNT;
            boolean needAddRareWord = rareWords.size() < RARE_WORDS_COUNT;

            if (needAddFrequentWord || needAddRareWord)
            {
                if (needAddFrequentWord)
                {
                    frequentWords.offer(entry);
                }
                if (needAddRareWord)
                {
                    rareWords.offer(entry);
                }
                continue;
            }
            if (frequentWords.peek().getValue() < entry.getValue())
            {
                frequentWords.poll();
                frequentWords.offer(entry);
            }
            if (rareWords.peek().getValue() > entry.getValue())
            {
                rareWords.poll();
                rareWords.offer(entry);
            }
        }
        LOG.info("Most frequent words: " + frequentWords);
        LOG.info("Most rare words: " + rareWords);
    }
}
