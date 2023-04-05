import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TestBuffer {

    private ByteBuffer bytes;
    private String testStr = "sekar";

    public TestBuffer() {
        bytes = ByteBuffer.allocate(1000);
        System.out.println("init: " + printBuffer());
    }

    public static void main(String a[]) {
        TestBuffer buf = new TestBuffer();
        try {
            buf.writeBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buf.readBuffer();
    }

    // write testStr to buffer
    private void writeBuffer() throws IOException {
        byte[] b = testStr.getBytes();
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(b));
        // in.read(bytes.array());
        byte[] dst = new byte[b.length];
        in.read(dst);
        bytes.put(dst);
        in.close();
        System.out.println("write: " + printBuffer());
    }

    // read buffer data back to byte array and print
    private void readBuffer() {
        //bytes.flip();
        byte[] b = new byte[bytes.position()];
        //bytes.position(0);
        int pos = bytes.position();
        bytes.flip();   // bytes.rewind(); could achieve the same result here, use which one depends on whether:
        // (1) reading to this bytebuffer is finished and fine to overwrite the current context in this bytebuffer afterwards: can use flip(); or
        // (2) just want to tentatively traverse this bytebuffer from the begining to current position,
        //      and keep writing to this bytebuffer again later from current position.
        bytes.get(b);
        bytes.position(pos);
        System.out.println("data read: " + new String(b));
        System.out.println("read: " + printBuffer());
    }

    public String printBuffer() {
        return "ByteBuffer [limit=" + bytes.limit() + ", capacity=" + bytes.capacity() + ", position="
                + bytes.position() + ", remaining=" + bytes.remaining() + "]";
    }

}