package codec;

import codec.Message;

public interface MessageCodec<T> {

   public T decode(Message payload);

   public void encode(Message payload);

    /**
     *  Creater interface must be implemented in data class for decoding the message.
      * @param <T> Data model class
     */
   public interface Creator<T> {

        public T createFromSerializer();


    }


}