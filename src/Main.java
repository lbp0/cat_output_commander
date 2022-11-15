import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{
	public static void main(String a[])
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		WindowUI wui = new WindowUI();
		Watcher watcher = new Watcher();
		MySql mysql = new MySql();
		MWrite mwrite = new MWrite();
		ConnectAgent ca = new ConnectAgent();
		AddWindow addWindow = new AddWindow();
		
		mysql.wui = wui;
		wui.watcher = watcher;
		watcher.wui = wui;
		watcher.mysql = mysql;
		watcher.mwrite = mwrite;
		mwrite.mysql = mysql;
		ca.wui = wui;
		wui.ca = ca;
		addWindow.watcher = watcher;
		watcher.addWindow = addWindow;
		addWindow.wui = wui;
		
		wui.init();
		addWindow.textFont = wui.textFont;//同步字体
		addWindow.init();
		mysql.showData();
		ca.start();

	}
}
