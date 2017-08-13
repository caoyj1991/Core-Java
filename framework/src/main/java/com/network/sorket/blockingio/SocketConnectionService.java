package com.network.sorket.blockingio;

import com.framework.dispatcher.HttpDispatcher;
import com.network.protocol.http.HttpProtocolResolver;
import com.network.protocol.http.HttpRequest;
import com.network.protocol.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   3:43 PM
 */
public class SocketConnectionService {

    public void resolve(Socket socket){
        Runnable runnable = new ConnectionRunnable(socket);
        try {
            SocketThreadExecutor.getInstance().execute(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ConnectionRunnable implements Runnable{

        private Socket socketConnection;

        public ConnectionRunnable(Socket socketConnection){
            this.socketConnection = socketConnection;
        }

        @Override
        public void run() {
            System.out.println("accept with :"+socketConnection.getInetAddress().getHostAddress());
            try {
                InputStream inputStream = socketConnection.getInputStream();
                OutputStream outputStream = socketConnection.getOutputStream();
                HttpRequest httpRequest = HttpProtocolResolver.getHttpRequest(inputStream);
                HttpResponse httpResponse = HttpDispatcher.getInstance().doExecute(httpRequest);

                outputStream.write(httpResponse.getBody());
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                socketConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
