package test;

import codec.Message;
import codec.MessageCodec;

import java.util.Objects;

public class HeadersAndPrimitiveTypes implements MessageCodec<HeadersAndPrimitiveTypes> {
    int a = 100;
    float b = 10.0f;
    double c = 123456778;
    long l = 460960960690960960L;
    boolean be= true;
    char ch = 'm';
    String msg = "Munees";

    @Override
    public boolean equals(Object o) {

        HeadersAndPrimitiveTypes that = (HeadersAndPrimitiveTypes) o;
        return a == that.a && Float.compare(that.b, b) == 0 && Double.compare(that.c, c) == 0 && l == that.l && be == that.be && ch == that.ch && Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, l, be, ch, msg);
    }

    @Override
    public HeadersAndPrimitiveTypes decode(Message payload) {
        a = payload.readInt();
        b = payload.readFloat();
        c = payload.readDouble();
        l = payload.readLong();
        be = payload.readBoolean();
        ch = payload.readChar();
        msg = payload.readString();
        return this;
    }


    @Override
    public void encode(Message payload) {
        payload.writeInt(a);
        payload.writeFloat(b);
        payload.writeDouble(c);
        payload.writeLong(l);
        payload.writeBoolean(be);
        payload.writeChar(ch);
        payload.writeString(msg);

    }
    public static final Creator<HeadersAndPrimitiveTypes> CREATOR = HeadersAndPrimitiveTypes::new;

}
