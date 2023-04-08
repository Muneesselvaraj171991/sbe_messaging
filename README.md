# sbe_messaging

The idea is to sending the Message of any data class from one device to another in the form of bytes.

My assumption from the task is as below,

![sequence_diagram](https://user-images.githubusercontent.com/38101471/230706031-1a1c6b82-d60e-4014-8134-972fb369c2e4.png)

Excchange data in the form of binary payload between devices. I kept codec classes as a generic , with that any data class extending MessageCodec(below diaram) can either send or receive the payload along with headers.


![interface](https://user-images.githubusercontent.com/38101471/230643822-7a65a1d5-4a58-43dc-8453-7e3fac052779.png)


    
Data class must implments MessageCodec inorder to send a messages, right now message will accept maximum of 63 headers and payload of data class. And also we can convert the message to its data class by using message.parseMessage(payload, Creater), If you want to parse the payload Data class must implments Creater interface. 
    
    
Wrote a samples for message encoding and decoding inside src/test folder , Please take a look. 
