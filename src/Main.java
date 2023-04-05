import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;

public class Main {

    public static Object byteBufferToObject(ByteBuffer byteBuffer)
            throws Exception {
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        Object object = deSerializer(bytes);
        return object;
    }

    public static Object deSerializer(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bytes));
        return objectInputStream.readObject();
    }

    public static void main(String[] args) throws Exception {
        Student obj = new Student(123, 4567);

byte[] bytes = obj.serialize(obj);
       Student afterDe = obj.deserialize(bytes);


        System.out.println(afterDe.toString());

DataClass da= new DataClass("sekar", "Madhiya Muness");
byte[] bArr = da.serialize(da);

        System.out.println(da.deserialize(bArr).toString());
    }

    }