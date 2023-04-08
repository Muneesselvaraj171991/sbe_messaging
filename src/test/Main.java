package test;

import codec.Message;

public class Main {

    public static void main(String args[]) {


        Student stu = new Student("Muneeswari", "CSE", 778);

        Message codec = Message.newInstance();

        // Payload encoding
        byte[] payloadBytes = codec.getMessagePayload(stu);

        //Payload-decoding
        Message deCodec = Message.newInstance();
        Student codecAfterDecoding = deCodec.parseMessage(payloadBytes, Student.CREATOR);

        assert stu.equals(codecAfterDecoding);

        // Test 2
        Message beforeMessageCodec2 = Message.newInstance();

        HeadersAndPrimitiveTypes headersAndPrimitiveTypes = new HeadersAndPrimitiveTypes();

        for(int header=0; header<63; header++) {
            beforeMessageCodec2.addHeader("ke"+header, "va"+header);
        }

        byte[] payload2 = beforeMessageCodec2.getMessagePayload(headersAndPrimitiveTypes);
        //Payload-decoding
        Message deCodec2 = Message.newInstance();
        HeadersAndPrimitiveTypes headersAndPrimitiveTypesAfterDecodec = deCodec2.parseMessage(payload2, HeadersAndPrimitiveTypes.CREATOR);

        for(int header=0; header<63; header++) {
            assert beforeMessageCodec2.getHeaders().get("ke"+header).equals(deCodec2.getHeaders().get("ke"+header));
        }

        assert headersAndPrimitiveTypes.equals(headersAndPrimitiveTypesAfterDecodec);






    }

}
