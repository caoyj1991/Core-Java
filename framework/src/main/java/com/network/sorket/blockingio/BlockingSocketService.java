package com.network.sorket.blockingio;

import com.exception.ServerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author Jun
 * Email
 * Date   6/23/17
 * Time   10:07 PM
 */
public class BlockingSocketService {

    private final static Integer DEFAULT_PORT = 9999;

    private Integer port = null;

    private ServerSocket serverSocket;

    private SocketConnectionService socketConnectionService;

    public BlockingSocketService(Integer port) throws ServerException {
        if (port<1024){
            throw new UnsupportedOperationException("Can not use this port:"+port);
        }
        this.port = port;
        try {
            serverSocket = new ServerSocket(this.port);
            socketConnectionService = new SocketConnectionService();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException(e.getMessage());
        }
    }

    public void start() throws ServerException {
        System.out.println("Waiting accept");
        while (true){
            try {
                Socket socketConnection = serverSocket.accept();
                System.out.println("new request=======");
                socketConnectionService.resolve(socketConnection);
            }catch (Exception e){
                throw new ServerException(e.getMessage());
            }
        }

    }


    public static void main(String[] args) throws ServerException {
        new BlockingSocketService(9999).start();
    }
}
