import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WindowUI extends JFrame
{
	Font textFont;

	Watcher watcher;
	ConnectAgent ca;

	JLabel connection;
	JLabel version;
	
	JTable table;

	JTextArea jtf;
	JScrollPane outPut;
	
	JButton addOne;//添加
	JButton delOne;//删除
	JButton change;//修改
	JButton refresh;//刷新
	void init()//窗口初始化函数
	{
		//全局字体设置
		textFont = new Font("宋体",Font.PLAIN,12);
		setLocationRelativeTo(null);

		//窗口
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.setResizable(false);
		setTitle("猫猫数据库管理器");
		setBounds(450,220,900,600);
		setFont(textFont);
		setVisible(true);
		String[] columns = {"数据ID","日期","时间","尿尿","拉粑粑"};//字段
		
		Object[][] data = {{"Loading...","Loading...","Loading...","Loading...","Loading..."}};
		DefaultTableModel model=new DefaultTableModel(data, columns);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		//scrollPane.setBounds(0,0,650,566);
		scrollPane.setBounds(30,30,600,500);
		add(scrollPane); 

		connection = new JLabel("网络状态：");
		connection.setBounds(30,5,500,30);
		connection.setFont(textFont);
		add(connection);
		ca.connection = connection;

		version = new JLabel("版本：v1.4");
		version.setFont(textFont);
		version.setBounds(30,530,500,30);
		add(version);

		addOne = new JButton("添加");
		addOne.setBounds(670,350,200,30);
		addOne.setFont(textFont);
		addOne.addActionListener(watcher);
		add(addOne);
		
		delOne = new JButton("删除");
		delOne.setBounds(670,400,200,30);
		delOne.setFont(textFont);
		delOne.addActionListener(watcher);
		add(delOne);
		
		change = new JButton("修改");
		change.setBounds(670,450,200,30);
		change.setFont(textFont);
		change.addActionListener(watcher);
		add(change);
		
		refresh = new JButton("刷新");
		refresh.setBounds(670,500,200,30);
		refresh.setFont(textFont);
		refresh.addActionListener(watcher);
		add(refresh);

		jtf = new JTextArea();
		jtf.setFont(textFont);
		outPut = new JScrollPane(jtf);
		outPut.setBounds(670,30,200,270);
		outPut.setFont(textFont);
		add(outPut);
		jtf.setText("操作记录：\n");
		jtf.setEditable(false);
		
		repaint();//重新绘制窗口解决按钮不显示的问题
	}
}
