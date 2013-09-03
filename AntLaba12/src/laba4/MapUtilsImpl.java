package laba4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.task4.MapUtils;
/**
 * @author zinchenko
 *
 */
public class MapUtilsImpl implements MapUtils {

    /**
     * Функция поиска тройных символьных сочетаний в строке.
     * 
     * @param s
     *            строка
     */
    @Override
    public Map<String, Integer> findThrees(final String s) {

        String[] words = s.split(" ");

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < words.length; i++) {
            List<String> threeCharsStrings = subsOfWord(words[i]);
            for (int j = 0; j < threeCharsStrings.size(); j++) {
                String treeChar = threeCharsStrings.get(j);
                addToMap(treeChar, map);
            }
        }

        return map;
    }

    /**
     * Поиск тройных буквенных сочетаний в слове.
     * 
     * @param word
     *            слово
     * @return найденные тройные буквенные сочетания
     */
    private List<String> subsOfWord(final String word) {

        int n = word.length() - 2;
        List<String> subs = new ArrayList<String>();
        String s = null;

        for (int i = 0; i < n; i++) {
            s = word.substring(i, i + 3);
            if (isTrue(s)) {
                subs.add(s);
            }
        }

        return subs;
    }

    /**
     * Проверка корректноси тройного буквенного сочетания.
     * 
     * @param s
     *            тройное буквенное сочетание
     * @return {@code true} если корректно.
     */
    private boolean isTrue(final String s) {

        String regex = "[a-zA-Z0-9]{3}";

        boolean b = s.matches(regex);

        return b;
    }

    /**
     * Внесение тройного буквенного сочетания в map. Если эквивалентное
     * буквенное сочетание уже содержится тогда колличестко таких буквенных
     * сочетаний инкрементируется, в противном случае добавляется новое.
     * 
     * @param threeChar
     *            тройное буквенное сочетание
     * @param map
     *            ссылка на объект Map, куда будет добавлено букв. сочетание
     */
    private void addToMap(final String threeChar, final Map<String, Integer> map) {
        if (map.containsKey(threeChar)) {
            int n = map.get(threeChar);
            n++;
            map.put(threeChar, n);
        } else {
            map.put(threeChar, 1);
        }
    }

}
