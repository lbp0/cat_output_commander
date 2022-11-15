import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectAgent extends Thread
{
    WindowUI wui;
    JLabel connection;
    public void run()
    {
        boolean ifConnected = false;
        while(true)
        {
            try
            {
                ifConnected = InetAddress.getByName("192.168.3.236").isReachable(300);//测试连接
            }
            catch(UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            if(ifConnected == true) //如果连接正常
            {
                connection.setText("网络状态：已连接至服务器");//设置连接正常
            }
            else
            {
                connection.setText("网络状态：离线");//设置连接异常
            }


            try
            {
                sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
