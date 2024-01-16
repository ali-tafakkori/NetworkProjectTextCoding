import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EncryptionManager {

    private Map<Integer, Integer> keys = new HashMap<>();
    private static EncryptionManager manager;

    public static EncryptionManager getInstance() throws FileNotFoundException {
        if (manager == null) {
            manager = new EncryptionManager();
        }
        return manager;
    }

    public EncryptionManager() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(".\\data\\keys.txt"));
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(":");
            keys.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
    }

    public int random() {
        Set<Integer> numbers = keys.keySet();
        int random = new Random().nextInt(numbers.size());
        return keys.get(random);
    }

    public String encrypt(int keyNumber, String text) {
        int key = keys.get(keyNumber);
        char[] result = text.toCharArray();

        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (result[i] + key);
        }

        return new String(result);
    }
}
