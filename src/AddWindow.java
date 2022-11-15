import javax.swing.*;
import java.awt.*;

public class AddWindow extends JFrame
{
    Font textFont;
    Watcher watcher;
    WindowUI wui;
    DateChooser dateChooser;
    JTextField dateSet;

    JLabel dateLabel;

    JLabel timeLabel;
    JTextField timeSet;

    JLabel sitLabel;
    JComboBox sitSet;

    JLabel errorLabel;

    JButton yesBtn;
    JButton noBtn;
    public void init()
    {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        this.setResizable(false);
        setTitle("添加");
        setBounds(450,220,400,200);
        setLocationRelativeTo(wui);

        dateLabel = new JLabel("日期：");
        dateLabel.setFont(textFont);
        dateLabel.setBounds(40,20,100,30);
        add(dateLabel);

        dateSet = new JTextField("单击以设置日期");
        dateSet.setEditable(false);
        dateChooser = DateChooser.getInstance("yyyy-MM-dd");
        dateChooser.register(dateSet);
        dateSet.setBounds(140,20,200,30);
        dateSet.setFont(textFont);
        dateSet.setBorder(null);
        add(dateSet);

        timeLabel = new JLabel("时间：");
        timeLabel.setBounds(40,50,100,30);
        timeLabel.setFont(textFont);
        add(timeLabel);

        timeSet = new JTextField();
        timeSet.setBounds(140,55,200,20);
        timeSet.setFont(textFont);
        add(timeSet);
        timeSet.setToolTipText("输入时间，如：14:07");

        sitLabel = new JLabel("猫砂情况：");
        sitLabel.setBounds(40,80,100,30);
        sitLabel.setFont(textFont);
        add(sitLabel);

        sitSet = new JComboBox();
        sitSet.setBounds(140,85,200,20);
        add(sitSet);
        sitSet.addItem("拉粑粑");
        sitSet.addItem("尿尿");
        sitSet.addItem("两者都有");
        sitSet.addItem("两者都没有");
        sitSet.setSelectedIndex(0);

        yesBtn = new JButton("确定");
        yesBtn.setBounds(200,120,60,20);
        add(yesBtn);
        noBtn = new JButton("取消");
        noBtn.setBounds(280,120,60,20);
        add(noBtn);
        yesBtn.addActionListener(watcher);
        noBtn.addActionListener(watcher);

        errorLabel = new JLabel("错误");
        errorLabel.setVisible(false);
        errorLabel.setBounds(40,120,200,20);
        add(errorLabel);
        errorLabel.setForeground(Color.red);

        repaint();
    }
}
