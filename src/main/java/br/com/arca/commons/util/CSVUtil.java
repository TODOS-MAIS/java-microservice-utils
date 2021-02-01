package br.com.arca.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CSVUtil {
    private static final String TOKEN = ",";

    public static <T> List<T> parseCSVScanner(Scanner scanner, BiFunction<List<String>, Integer, T> parser, boolean withHeader) {
        var list = new ArrayList<T>();
        var lineNumber = 0;

        if(withHeader) {
            lineNumber++;
            scanner.next();
        }

        while(scanner.hasNextLine()) {
            var line = scanner.next();
            var lineTokenized = Arrays.stream(line.split(TOKEN)).map(String::trim).collect(Collectors.toList());
            var parsedObject = parser.apply(lineTokenized, lineNumber++);
            list.add(parsedObject);
        }

        return list;
    }

    public static <T> List<T> parseCSVScanner(Scanner scanner, BiFunction<List<String>, Integer, T> parser) {
        return parseCSVScanner(scanner, parser, false);
    }
}
