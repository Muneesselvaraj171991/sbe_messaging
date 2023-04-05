import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public interface MessageCodec<T> {
    static final String STORED_ENCODING = "UTF-8";

    T deserialize(byte[] input);

    byte[] serialize(T input);


    public static String readString(ByteBuffer b, int index) {
        int len = b.getInt();

        if (len > 0) {
            byte[] buf = new byte[len];
            b.get(buf);
            b.get();
            try {
                return new String(buf, STORED_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e.getMessage());
            }
        } else {
            return "";
        }
    }
    static public ByteBuffer putString(String str, ByteBuffer bb) {
        if (str == null) {
            throw new NullPointerException("Argument str cannot be null");
        }
        if (bb == null) {
            throw new NullPointerException("Argument bb cannot be null");
        }
        if (str.length() > 255) {
            // We use a byte to store the length of the string...
            throw new IllegalArgumentException("Cannot store string: length greater than 255 characters.");
        }

        byte strBytes[];
        try {
            strBytes = str.getBytes(STORED_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        bb.putInt(strBytes.length);
        bb.put(strBytes);
        return bb;
    }
}