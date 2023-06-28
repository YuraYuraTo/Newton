import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;
public class ElectricityCalculator {
    private JFrame frame;
    private JTextField current1Field, current2Field, current3Field, current4Field, current5Field;
    private JLabel resultLabel, result1Label, wellltLabel;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ElectricityCalculator window = new ElectricityCalculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ElectricityCalculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(475, 275, 450, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel current0Label = new JLabel("Первый коэффициент:");
        current0Label.setBounds(10, 11, 414, 14);
        frame.getContentPane().add(current0Label);

        JLabel current2Label = new JLabel("Второй коэффициент:");
        current2Label.setBounds(10, 36, 414, 14);
        frame.getContentPane().add(current2Label);

        JLabel current3Label = new JLabel("Третий коэффициент:");
        current3Label.setBounds(10, 61, 414, 14);
        frame.getContentPane().add(current3Label);

        JLabel current4Label = new JLabel("Четвёртый коэффициент:");
        current4Label.setBounds(10, 86, 414, 14);
        frame.getContentPane().add(current4Label);

        JLabel current5Label = new JLabel("Начальное значение x0");
        current5Label.setBounds(10, 111, 414, 14);
        frame.getContentPane().add(current5Label);

        current1Field = new JTextField();
        current1Field.setBounds(166, 8, 150, 20);
        frame.getContentPane().add(current1Field);
        current1Field.setColumns(10);

        current2Field = new JTextField();
        current2Field.setBounds(166, 33, 150, 20);
        frame.getContentPane().add(current2Field);
        current2Field.setColumns(10);

        current3Field = new JTextField();
        current3Field.setBounds(166, 58, 150, 20);
        frame.getContentPane().add(current3Field);
        current3Field.setColumns(10);

        current4Field = new JTextField();
        current4Field.setBounds(166, 83, 150, 20);
        frame.getContentPane().add(current4Field);
        current4Field.setColumns(10);

        current5Field = new JTextField();
        current5Field.setBounds(166, 108, 150, 20);
        frame.getContentPane().add(current5Field);
        current5Field.setColumns(10);

        ButtonGroup citiesGroup = new ButtonGroup();
        JButton calculateButton = new JButton("Рассчитать");
        calculateButton.setBounds(150, 150, 150, 25);
        frame.getContentPane().add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double a = Integer.parseInt(current1Field.getText());
                double b = Integer.parseInt(current2Field.getText());
                double c = Integer.parseInt(current3Field.getText());
                double d = Integer.parseInt(current4Field.getText());
                double xz = Integer.parseInt(current5Field.getText());

                for (int i = 0; i < 100; i++) {
                    double f = getCubicEquationValue(a, b, c, d, xz);
                    double df = getCubicEquationDerivativeValue(a, b, c, d, xz);

                    double x1 = xz - f / df; // новое приближение корня

                    if (Math.abs(x1 - xz) < 1e-6) {



                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setMaximumFractionDigits(10);
                        String formattedResult = nf.format(x1);
                        resultLabel.setText(formattedResult);

                        String formatted1Result = "Кубическое уравнение имеет корень: ";
                        result1Label.setText(formatted1Result);


                        return;
                    }

                    xz = x1;
                }

                String formatted1Result = "Кубическое уравнение не имеет корней.";
                result1Label.setText(formatted1Result);

            }

            public static double getCubicEquationValue(double a, double b, double c, double d, double x) {
                return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
            }

            public static double getCubicEquationDerivativeValue(double a, double b, double c, double d, double x) {
                return 3 * a * Math.pow(x, 2) + 2 * b * x + c;
            }

        });

        result1Label = new JLabel("");
        result1Label.setBounds(10, 200, 414, 14);
        frame.getContentPane().add(result1Label);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 215, 414, 14);
        frame.getContentPane().add(resultLabel);

    }
}