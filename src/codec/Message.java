package codec;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

final public class Message {
    private static final int HEADERS_LENGTH = 63;
    private static final int HEADERS_MAX_BYTES = 1023;
    private static final int PAYLOAD_CAPACITY = 256000; // 256*1000
    private static  Message sMessage;
    public  synchronized static  Message getInstance() {
        if(sMessage == null) {
            sMessage = new Message();
        }
        return sMessage;
    }

    /**
     * For Testing
     * @return  new Message instance
     */
    public static Message newInstance() {
        return new Message();

    }


    private static final byte[] EMPTY = new byte[0];
    ByteBuffer byteBuffer;
    private Map<String, String> headers;

    private Message() {
        byteBuffer = ByteBuffer.allocate(PAYLOAD_CAPACITY+HEADERS_MAX_BYTES);
        headers = new HashMap<>();

    }

    public void setHeader(String header, Object object) {
        headers.put(header, object.getClass().getName());
    }

    public String readString() {
        int stringLen = byteBuffer.getInt();
        byte[] nameBytes = new byte[stringLen];
        byteBuffer.get(nameBytes);
        return new String(nameBytes);
    }

    public void writeString(String str) {
        byte[] nameBytes = str.getBytes();
        byteBuffer.putInt(nameBytes.length);
        byteBuffer.put(nameBytes);
    }

    public void writeInt(int value) {
        byteBuffer.putInt(value);

    }

    public void writeBoolean(boolean value) {
        byteBuffer.put(value ? (byte) 1 : (byte) 0);

    }

    public void writeDouble(double value) {
        byteBuffer.putDouble(value);

    }

    public void writeLong(long value) {
        byteBuffer.putLong(value);

    }

    public void writeFloat(float value) {
        byteBuffer.putFloat(value);

    }

    public void writeChar(char value) {
        byteBuffer.putChar(value);

    }

    public int readInt() {
        return byteBuffer.getInt();
    }

    public boolean readBoolean() {
        return byteBuffer.get() == 1;
    }

    public float readFloat() {
        return byteBuffer.getFloat();
    }

    public double readDouble() {
        return byteBuffer.getDouble();
    }

    public long readLong() {
        return byteBuffer.getLong();
    }

    public char readChar() {
        return byteBuffer.getChar();
    }


    public void addHeader(String key, String value) {
        if (headers.size() < HEADERS_LENGTH) {
            headers.put(key, value);
        } else {
            throw new IllegalArgumentException("You can add only maximum of:" + HEADERS_LENGTH + " headers");

        }
    }

    public byte[] getMessagePayload(MessageCodec type) {
        int headerSize = headers.size();
        //Writing Headers to bytebuffer
        writeInt(headerSize);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            writeString(header.getKey());
            writeString(header.getValue());

        }

        // Writing Message payload to bytebuffer
        String name = type.getClass().getName();
        writeString(name);
        type.encode(this);
        byteBuffer.flip();
        return byteBuffer.array();
    }

    public <T extends MessageCodec> T parseMessage(byte[] payload, MessageCodec.Creator creator) {

        byteBuffer = ByteBuffer.wrap(payload);
        int headerLength = byteBuffer.getInt();
        //read headers from payload
        for (int index = 0; index < headerLength; index++) {
            headers.put(readString(), readString());
        }

        T obj = (T) creator.createFromSerializer();
        if (obj.getClass().getName().equals(readString())) {
            obj.decode(this);
        } else {
            headers.clear();
            throw new IllegalStateException("Payload does not match with CREATER type: ");

        }
        return obj;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

}


