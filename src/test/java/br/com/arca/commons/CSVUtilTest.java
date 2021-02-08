package br.com.arca.commons;

import br.com.arca.commons.util.CSVUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVUtilTest {
    @DisplayName("Test to parseCSVScanner without header")
    @Test
    public void parseCSVScannerWithoutHeaderTest() {
        var sc = new Scanner("1;2\n3;4");
        var parseds = CSVUtil.parseCSVScanner(sc, ExampleClass::new);
        assertEquals(new ExampleClass(Arrays.asList("1", "2"), 0), parseds.get(0));
        assertEquals(new ExampleClass(Arrays.asList("3", "4"), 1), parseds.get(1));
    }

    @DisplayName("Test to parseCSVScanner with header")
    @Test
    public void parseCSVScannerWithHeaderTest() {
        var sc = new Scanner("field1;field2\n1;2\n3;4");
        var parseds = CSVUtil.parseCSVScanner(sc, ExampleClass::new, true);
        assertEquals(new ExampleClass(Arrays.asList("1", "2"), 1), parseds.get(0));
        assertEquals(new ExampleClass(Arrays.asList("3", "4"), 2), parseds.get(1));
    }

    @ToString
    @EqualsAndHashCode
    private static class ExampleClass {
        private String firstField;
        private String secondField;
        private int lineNumber;

        ExampleClass(List<String> fields, int lineNumber) {
            firstField = fields.get(0);
            secondField = fields.get(1);
            this.lineNumber = lineNumber;
        }
    }
}
