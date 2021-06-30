package cn.edu.bnuz.yhy.server;

import cn.edu.bnuz.yhy.system.Call;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static Objectpool objectpool;
    private ServerSocket ss;

    public SocketServer() {
        //监听指定窗口
        objectpool = new Objectpool();
        try {
            ss = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("正在监听8888端口");
        while (true) {
            try {
                Socket socket = ss.accept();
                new CreateServerThread(socket);
                System.out.println("已发送给客户端");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class CreateServerThread extends Thread {
        private Socket socket;

        public CreateServerThread(Socket socket) {
            this.socket = socket;
            start();
        }

        public void run() {
            try {
                //读取客户端发来的call对象
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Call call = (Call) ois.readObject();
                System.out.println("服务端：" + call.getClazzName());
                //从对象池中找出对应的对象
                Object object = objectpool.getObject(call.getClazzName());
                //从该对象上找出相应的方法对象
                //getSalary(String startTime,String endTime,double dou)
                Method method = object.getClass().getMethod(
                        call.getMethodName(),
                        call.getParamTypes());
                //方法对象执行
                Object rtOJ = method.invoke(object, call.getParamValues());
                call.setReturnObj(rtOJ);
                //返回方法执行结果
                ObjectOutputStream oos = new ObjectOutputStream(
                        socket.getOutputStream()
                );
                //将call对象返回客户端
                oos.writeObject(call);
                oos.flush();
                ois.close();
                oos.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}


