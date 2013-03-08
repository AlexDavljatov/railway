package com.tsystems.client.others;

import com.tsystems.common.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/28/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyAsynchClient {
    final int SERVER_PORT = 9090;
    final String SERVER_IP = "localhost";

    ByteBuffer in;   //receive
    ByteBuffer out;  //send

    boolean completed = false;

    public void execute() {
        try {
            in = ByteBuffer.allocate(1024);
            out = ByteBuffer.wrap("Hi".getBytes());
            //out = ByteBuffer.wrap(new Serializer().serialize(new UserSer("name", "surname", "email", "password")));
            //out = ByteBuffer.wrap(new Serializer().serialize("Hi"));

            AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();

            if (channel.isOpen()) {

                //connect this channel's socket
                Void connect = channel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT)).get();

                if (connect == null) {

                    System.out.println("Local address: " + channel.getLocalAddress());

                    //sending data
                    channel.write(out).get();
                    channel.read(in, in, new CompletionHandler<Integer, ByteBuffer>() {
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            String msgReceived = Charset.defaultCharset().decode(buffer).toString();
                            System.out.println("Msg received from server : " + msgReceived);
                            completed = true;
                        }

                        public void failed(Throwable exc, ByteBuffer buffer) {
                            completed = false;
                            throw new UnsupportedOperationException("read failed!");
                        }
                    });

                    while (!completed) {

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }

                        System.out.println("Waiting for response from the server");
                    }

                } else {
                    System.out.println("The connection cannot be established!");
                }
            } else {
                System.out.println("The asynchronous socket channel cannot be opened!");
            }
        }
        //catch (IOException | InterruptedException | ExecutionException ex)
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public boolean doRegister(User user) throws IOException, ClassNotFoundException {
        System.err.println("doRegister");
        //out.writeObject(new DataTransferObject(CommandType.REGISTER, user));
        //DataTransferObject input = (DataTransferObject) in.readObject();
        //System.err.println(input.getCmd());
        /**     System.err.println("Try to register: " + user);
         out.writeObject(new DataTransferObject(MyProtocol.Command.REGISTER, user));
         DataTransferObject input = (DataTransferObject) in.readObject();
         System.err.println(input.getCmd());
         processCommand(input.getCmd());
         */
        //out.close();
        //in.close();
        //return input.getCmd() == CommandType.OK;
        return false;
    }


    public static void main(String[] args) {

        new MyAsynchClient().execute();
    }

}
