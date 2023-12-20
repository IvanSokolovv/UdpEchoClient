import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

import static java.net.InetAddress.getLocalHost;
import static java.nio.charset.Charset.defaultCharset;
import static java.util.logging.Logger.getAnonymousLogger;

public class UdpEchoClient {

    public static void main(String[] args) throws IOException {
        Logger logger = getAnonymousLogger();

        InetAddress localhost = getLocalHost();
        int port = 6666;
        DatagramSocket socket = new DatagramSocket();

        String message = "Hello world!";
        byte[] messageBytes = message.getBytes(defaultCharset());
        DatagramPacket messagePacket = new DatagramPacket(
                messageBytes,
                messageBytes.length,
                localhost,
                port
        );

        logger.info("Отправляем: " + message);
        socket.send(messagePacket);

        byte[] responseBytes = new byte[1000];
        DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length);

        socket.receive(responsePacket);
        logger.info("Получили: " + new String(responsePacket.getData()));

        socket.close();
        logger.info("Клиент закончил свою работу");
    }
}