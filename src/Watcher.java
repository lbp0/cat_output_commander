import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Watcher implements ActionListener
{
	WindowUI wui;
	MySql mysql;
	MWrite mwrite;
	AddWindow addWindow;
	
	int rowNum;//选取的记录行数
	String tarId;//目标ID
	int backflag;
	String backStr;
	public void actionPerformed(ActionEvent e) 
	{
		//浏览按钮
		if(e.getSource() == wui.addOne)//添加
		{
			addWindow.dateSet.setText("单击以设置日期");
			addWindow.timeSet.setText(null);
			addWindow.setVisible(true);
			addWindow.errorLabel.setVisible(false);
		}
		else if(e.getSource() == wui.delOne)//删除
		{
			if(wui.table.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(wui,"请选择要删除的记录","未选择要删除的记录",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				rowNum = wui.table.getSelectedRow();
				tarId = (String) wui.table.getValueAt(rowNum,0);
				backflag = JOptionPane.showConfirmDialog(wui,"确定要删除选中的记录吗","确定",JOptionPane.YES_NO_OPTION);
				if(backflag == 0)
				{
					//数据库中删除
					System.out.println("删除" + tarId);
					mwrite.sqlDel(tarId);
					//从表格中删除
					((DefaultTableModel)wui.table.getModel()).removeRow(wui.table.getSelectedRow());
					wui.jtf.append("- 删除了一条记录\n");
				}
				else
				{
					wui.jtf.append("- 放弃删除\n");
				}
			}
		}
		else if(e.getSource() == wui.change)//修改
		{
			if(wui.table.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(wui,"请选择要修改的记录","未选择要修改的记录",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				rowNum = wui.table.getSelectedRow();
				tarId = (String) wui.table.getValueAt(rowNum,0);
				Object[] options = {"尿尿","拉粑粑","都有","都没有"};
				backStr = (String) JOptionPane.showInputDialog(wui,"请选择修改后的猫砂情况：","修改猫砂情况",JOptionPane.PLAIN_MESSAGE,null, options, options);
				mwrite.sqlChange(tarId, backStr);
				wui.jtf.append("- 修改完毕\n");
			}
		}
		else if(e.getSource() == wui.refresh)//刷新
		{
			mysql.showData();
			wui.jtf.append("- 刷新\n");
		}

		if(e.getSource() == addWindow.noBtn)
		{
			wui.jtf.append("- 放弃添加\n");
			addWindow.setVisible(false);
		}
		else if(e.getSource() == addWindow.yesBtn)
		{
			if(addWindow.dateSet.getText().equals("单击以设置日期"))
			{
				System.out.println("日期没有设置");
				addWindow.errorLabel.setText("请设置日期");
				addWindow.errorLabel.setVisible(true);
			}
			else if(addWindow.timeSet.getText().isEmpty())
			{
				System.out.println("时间没有设置");
				addWindow.errorLabel.setText("请设置时间");
				addWindow.errorLabel.setVisible(true);
			}
			else if(addWindow.timeSet.getText().length() != 5)
			{
				addWindow.errorLabel.setText("时间格式不正确，应为hh:mm");
				addWindow.errorLabel.setVisible(true);
			}
			else if(!addWindow.timeSet.getText().substring(2,3).equals(":"))
			{
				addWindow.errorLabel.setText("时间格式不正确，应为hh:mm");
				addWindow.errorLabel.setVisible(true);
			}
			else if(Integer.parseInt(addWindow.timeSet.getText().substring(0,2)) >= 24 || Integer.parseInt(addWindow.timeSet.getText().substring(0,2)) < 0)
			{
				addWindow.errorLabel.setText("小时输入错误，应为0到23");
				addWindow.errorLabel.setVisible(true);
			}
			else if(Integer.parseInt(addWindow.timeSet.getText().substring(3,5)) >= 60 || Integer.parseInt(addWindow.timeSet.getText().substring(0,2)) < 0)
			{
				addWindow.errorLabel.setText("分钟输入错误，应为0到59");
				addWindow.errorLabel.setVisible(true);
			}
			else
			{
				String year = addWindow.dateSet.getText().substring(0,4);
				String month = addWindow.dateSet.getText().substring(5,7);
				String day = addWindow.dateSet.getText().substring(8,10);
				String hour = addWindow.timeSet.getText().substring(0,2);
				String minute = addWindow.timeSet.getText().substring(3,5);
				String second = "00";
				String popo = null;
				String bubu = null;
				if(addWindow.sitSet.getSelectedIndex() == 0)//拉粑粑
				{
					popo = "0";
					bubu = "1";
				}
				else if(addWindow.sitSet.getSelectedIndex() == 1)//尿尿
				{
					popo = "1";
					bubu = "0";
				}
				else if(addWindow.sitSet.getSelectedIndex() == 2)//都有
				{
					popo = "1";
					bubu = "1";
				}
				else//都没有
				{
					popo = "0";
					bubu = "0";
				}

				backflag = JOptionPane.showConfirmDialog(wui, "确定要把如下信息填入数据库吗？\n" + year  + "年" + month + "月" + day + "日，" + hour + ":" + minute + "\n尿尿：" + popo + "            拉粑粑：" + bubu,"确认填入",JOptionPane.YES_NO_OPTION);
				addWindow.setVisible(false);
				if(backflag == 0)
				{
					mwrite.sqlWrite(year, month, day, hour, minute, second, popo, bubu);
					mysql.showData();//刷新
					wui.jtf.append("- 添加了一条记录\n");
				}
				else
				{
					wui.jtf.append("- 放弃添加\n");
				}
			}
		}
	}
}
