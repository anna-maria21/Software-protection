public class Coder {

    private StringBuilder key;

    public Coder(StringBuilder key) {
        this.key = key;
    }

    // coding the key
    public StringBuilder encrypt(StringBuilder message) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                result.append(' ');
            } else {
                result.append((char) ((message.charAt(i) - 97 + key.charAt(i % key.length()) - 96) % 26 + 'a'));
            }
        }

        return result;

    }

}
