# sbe_messaging

The idea is to sending the Message of any data class from one device to another in the form of bytes.

public interface MessageCodec<T> {

   public T decode(Message payload);

   public void encode(Message payload);

    /**
     *  Creater interface must be implemented in data class for decoding the message.
      * @param <T> Data model class
     */
   public interface Creator<T> {

        public T createFromMessage();


    }
    
    
    Data class must implments MessageCodec inorder to send a messages, right now message will accept maximum of 63 headers and payload of data class. 
    
    And also we can convert the message to its data class by using message.parseMessage(payload, Creater), If you want to parse the payload Data class must implments Creater interface. 
