import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.regex.Pattern;

public class Student  implements MessageCodec<Student>{
    public int getRegister() {
        return register;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    private int register ;

    private int rollNumber;

    public Student(int rollNumber, int  register) {

        this.rollNumber = rollNumber;
        this.register = register;

    }

    @Override
    public String toString() {
        return "Student{" +
                ", register='" + register + '\'' +
                ", rollNumber=" + rollNumber +
                '}';
    }


    @Override
    public Student deserialize(byte[] input) {
        ByteBuffer buffer = ByteBuffer.wrap(input);
        return new Student(buffer.getInt(), buffer.getInt());
    }

    @Override
    public byte[] serialize(Student input) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(input.getRegister());
        buffer.putInt(input.getRollNumber());
        buffer.flip();
        return buffer.array();
    }
}
