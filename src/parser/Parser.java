package parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>Prime Number Multiplier</h1>
 * This program reads a file and parses an array in the format m = {654, 855, 93, 52, 83, 266}.
 * Then it multiplies each prime number in the list by its ordinal number in the list.
 * It presents the code (comments are required). It prints everything listed and specific fragments of the lists.
 *
 * <p><b>Note:</b> The ordinal number of an element in the list is its position in the list starting from 1.</p>
 *
 * @author dzmitry.shuhskevich@gmail.com
 * @version 1.0
 * @since 21.02.2024
 */
public class Parser {

    private final static String DELIMITER_PATTERN = "[,\\n]";
    private final static String START_BRACKET = "{";
    private final static String END_BRACKET = "}";

    /**
     * Method for reading a file and parsing an array
     * @param file the file to read
     * @return a list of integers from the file
     */
    public static List<Long> readFileAndParseArray(File file) throws FileNotFoundException {
        List<Long> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(DELIMITER_PATTERN);
            boolean startParsing = false;
            while (scanner.hasNext()) {
                String next = scanner.next().trim();
                if (next.startsWith(START_BRACKET)) {
                    startParsing = true;
                    next = next.substring(1);
                }
                if (next.contains(END_BRACKET)) {
                    next = next.substring(0, next.indexOf(END_BRACKET));
                    if (!next.isEmpty()) {
                        numbers.add(Long.parseLong(next));
                    }
                    break;
                }
                if (startParsing  && !next.isEmpty()) {
                    numbers.add(Long.parseLong(next));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found with name: " + file.getName());
            throw e;
        }
        return numbers;
    }
}
