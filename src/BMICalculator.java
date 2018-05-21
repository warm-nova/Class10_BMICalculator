import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.regex.*;


public class BMICalculator extends JFrame {

    private JPanel contentPane;
    private JLabel titleLabel;
    private JPanel contentPanel;
    private JButton submitButton;
    private ButtonGroup bg;
    private JPanel sexPanel;
    private JPanel whPanel;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JTextField heightText;
    private JTextField weightText;
    private JPanel consolePanel;
    private JLabel consoleLabel;
    private JTextField consoleText;
    private JRadioButton ManRadio;
    private JRadioButton WomanRadio;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JTextField resultText;

    private double weight;
    private double height;
    private double BMI;


    public BMICalculator() {
        setTitle("BMI指数计算器");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        //布局文件，参考了https://blog.csdn.net/u010992313/article/details/72083337，做了改动

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        titleLabel = new JLabel("身高体重指数计算器");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font(Font.DIALOG,Font.BOLD,22));

        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPane.add(contentPanel,BorderLayout.CENTER);
        submitButton = new JButton("计算");
        contentPane.add(submitButton, BorderLayout.SOUTH);

        bg = new ButtonGroup();
        sexPanel = new JPanel();
        sexPanel.setLayout(new FlowLayout());
        contentPanel.add(sexPanel,BorderLayout.NORTH);
        ManRadio = new JRadioButton("男性");
        ManRadio.setSelected(true);
        WomanRadio=new JRadioButton("女性");
        bg.add(ManRadio);
        bg.add(WomanRadio);
        sexPanel.add(ManRadio);
        sexPanel.add(WomanRadio);

        //存放身高体重的panel，选项中
        whPanel = new JPanel();
        whPanel.setLayout(new FlowLayout());
        contentPanel.add(whPanel,BorderLayout.CENTER);
        heightLabel = new JLabel("身高（厘米/cm）：");
        weightLabel = new JLabel("体重（千克/kg）：");
        heightText = new JTextField(10);
        heightText.setToolTipText("请输入身高");
        weightText = new JTextField(10);
        weightText.setToolTipText("请输入体重");
        whPanel.add(heightLabel);
        whPanel.add(heightText);
        whPanel.add(weightLabel);
        whPanel.add(weightText);
        //结果
        consolePanel = new JPanel();

        consolePanel.setLayout(new FlowLayout());
        consoleLabel = new JLabel("你的BMI指数为：");
        consoleText = new JTextField(28);
        consoleText.setEditable(false);

        consolePanel.add(consoleLabel);
        consolePanel.add(consoleText);
        contentPanel.add(consolePanel,BorderLayout.SOUTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultLabel = new JLabel("结论：");
        resultText = new JTextField(28);
        resultText.setEditable(false);
        resultPanel.add(resultLabel);
        resultPanel.add(resultText);
        consolePanel.add(resultPanel,BorderLayout.CENTER);




        //contentPanel.add(resultPanel,BorderLayout.WEST);

        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String hstr = heightText.getText();
                String wstr = weightText.getText();
                Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
                Matcher hisNum = pattern.matcher(hstr);
                Matcher wisNum = pattern.matcher(wstr);
                boolean acc = true;
                if( !hisNum.matches()|| !wisNum.matches()){
                    acc = false;
                }
                if(acc)
                {
                    height = Double.parseDouble(hstr)/100.0;
                    weight = Double.parseDouble(wstr);
                    BMI = weight / (height*height);
                    DecimalFormat df = new DecimalFormat("#.0");
                    String out = "";
                    if(ManRadio.isSelected()) {
                        if (BMI < 18.5)
                            out = "偏瘦，需要加强营养，适量增肥";
                        else if (BMI < 23.9)
                            out = "您的体重正好，请继续保持";
                        else if (BMI < 27.9)
                            out = "偏胖，请注意运动减肥";
                        else if (BMI < 30)
                            out = "肥胖，请在专业人士指导下进行运动训练";
                        else if (BMI >= 30 && BMI<=45)
                            out = "重度肥胖，不是一般人，赶紧减肥吧！";
                        else
                            out = "请重新输入或者立即去医院进行检查";
                    }

                    if(WomanRadio.isSelected()) {
                        if (BMI < 17.5)
                            out = "偏瘦，需要加强营养，适量增肥";
                        else if (BMI < 25.8)
                            out = "您的体重正好，请继续保持";
                        else if (BMI < 27.3)
                            out = "偏胖，请注意运动减肥";
                        else if (BMI < 32.3)
                            out = "肥胖，请在专业人士指导下进行运动训练";
                        else if (BMI >= 32.3 && BMI<=40)
                            out = "重度肥胖，不是一般人，赶紧减肥吧！";
                        else
                            out = "请重新输入或者立即去医院进行检查";
                    }

                    consoleText.setText(df.format(BMI));
                    resultText.setText(out);
                }

            }
        });
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BMICalculator frame = new BMICalculator();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
