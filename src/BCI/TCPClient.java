package BCI;

import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TCPClient {
    Socket tcp_clientSocket;
    DataOutputStream tcp_outputStream;

    boolean open(String host, Integer port) throws Exception { //Opens the connection to the Acquisition server TCP Tagging.
        tcp_clientSocket = new Socket(host, port);
        tcp_outputStream = new DataOutputStream(tcp_clientSocket.getOutputStream());
        System.out.println("Connection established.");

        return true;
    }

    boolean close() throws Exception { //Closes connection.
        tcp_clientSocket.close();
        System.out.println("Connection closed.");

        return true;
    }

    boolean send(Long stimulation, Long timestamp) throws Exception { //Stimulation is sent with a timestamp included.
        ByteBuffer b = ByteBuffer.allocate(24);
        b.order(ByteOrder.LITTLE_ENDIAN);
        b.putLong(0);
        b.putLong(stimulation);
        b.putLong(timestamp);

        tcp_outputStream.write(b.array());
        return true;
    }

    public static void main(String argv[]) throws Exception {
        TCPClient client = new TCPClient();
        System.out.println("Attempting to connect to BCI...");
        client.open("localhost", 15361);
        //client.send(123L, 0L); //Testing to see if the stimulation is being received.
        //client.send(666L, 0L);
        //client.close(); //Client is set to close once the TCP Client has connected, just delete it to keep the TCP Client connected to OpenVibe.
    }

}
