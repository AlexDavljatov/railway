import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
 
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
 
public class NonBlockingTcpServer
{    
    public static void main(String[] args) throws Exception
    {
        final int DEFAULT_PORT = 9001;
        ByteBuffer incomingBuffer = ByteBuffer.allocateDirect(1024);
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer outgoingBuffer = ByteBuffer.wrap("World".getBytes());
     
        //Open Selector and ServerSocketChannel
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open())
        {
            //Check if both of them were opened
            if ((serverSocketChannel.isOpen()) && (selector.isOpen()))
            {
                //Configure non-blocking mode
                serverSocketChannel.configureBlocking(false);
                //Bind to the specific port number
                serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT));
                //Register the current channel with the given selector
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
             
                System.out.println("Waiting for incoming connections ...");
                while (true)
                {
                    //wait for incomming events
                    selector.select();
                    //there is something to process on selected keys
                    Iterator keys = selector.selectedKeys().iterator();
                    while (keys.hasNext())
                    {
                        SelectionKey key = (SelectionKey) keys.next();
                        //prevent the same key from coming up again
                        keys.remove();
                        if (!key.isValid())
                        {
                            continue;
                        }
                        //Accept incoming connection
                        if(key.isAcceptable())
                        {
                            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = serverChannel.accept();
                            socketChannel.configureBlocking(false); 
                            System.out.println("Accept incomming connection from: " + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                        if (key.isReadable())
                        {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            incomingBuffer.clear();
                            int numRead = -1;
                            try
                            {
                                numRead = socketChannel.read(incomingBuffer);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                key.cancel();
                                continue;
                            }
                            incomingBuffer.flip();
                            String requestMsg = decoder.decode(incomingBuffer).toString();
                            System.out.println("Request from " + socketChannel.getRemoteAddress() + " : " + requestMsg);
                            Thread.sleep(1000);
                            socketChannel.write(outgoingBuffer);
                            outgoingBuffer.flip();
                            socketChannel.shutdownOutput();
                            key.cancel();
                        }
                    }
                }
            }
            else
            {
                System.out.println("The server socket channel or selector cannot be opened!");
            }
        }
        catch (IOException ex)
        {
            System.err.println(ex);
        }
    }
}
