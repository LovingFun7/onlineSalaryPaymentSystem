package cn.edu.bnuz.yhy.client;

import cn.edu.bnuz.yhy.system.Call;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private static SocketClient socketClient;

    private SocketClient(){
        try {
            //链接服务器
            socket = new Socket("127.0.0.1", 8888);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*每次启动一个客服端
    只连接一个服务端，
    单例模式*/
    public static SocketClient getSocketClient(){
        if (socketClient == null){
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public Call callServer(Call call){
        try {
            //使用socket的输出流（对象输出）
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //使用输出流将call对象发送到服务端
            oos.writeObject(call);
            oos.flush();
            System.out.println("客户端发送："+call.getClazzName());
            //等等服务端返回call，读取返回值对象
            ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream()
            );
            //读取服务端返回的call对象
            call = (Call)ois.readObject();
            oos.close();
            return call;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
