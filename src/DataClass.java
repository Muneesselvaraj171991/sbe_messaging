import java.nio.ByteBuffer;

public class DataClass implements MessageCodec<DataClass>{
    private String key;
    private String msg;

    @Override
    public String toString() {
        return "DataClass{" +
                "key='" + key + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    DataClass(String key, String msg ) {
        this.key = key;
        this.msg = msg;

    }
    @Override
    public DataClass deserialize(byte[] input) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(input);
        byteBuffer.rewind();
        return new DataClass(MessageCodec.readString(byteBuffer, 0), MessageCodec.readString(byteBuffer));
    }

    @Override
    public byte[] serialize(DataClass input) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        MessageCodec.putString(key, byteBuffer);
        MessageCodec.putString(msg, byteBuffer);
        byteBuffer.flip();
        return byteBuffer.array();

    }
}
