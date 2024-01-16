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
        return (int) numbers.toArray()[random];
    }

    public String encrypt(int keyNumber, String text) {
        int key = keys.get(keyNumber);
        char[] result = new char[text.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (text.charAt(i) + key);
        }
        return new String(result);
    }
    public String decrypt(int keyNumber, String encryptedData) {
        int key = keys.get(keyNumber);
        char[] result = new char[encryptedData.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) (encryptedData.charAt(i) - key);
        }
        return new String(result);
    }
}
