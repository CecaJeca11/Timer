import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame {
    private JLabel timeLabel;
    private JButton colorButton;
    private JButton stopButton;
    private JButton restartButton;
    private Timer timer;
    private boolean colorChange = false;

    public Main() {
        setTitle("Clock App");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        colorButton = new JButton("Change Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose Color", timeLabel.getForeground());
                if (color != null && !color.equals(Color.RED)) {
                    timeLabel.setForeground(color);
                }
            }
        });
        buttonPanel.add(colorButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        buttonPanel.add(stopButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        buttonPanel.add(restartButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime time = LocalTime.now();
                timeLabel.setText(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (colorChange) {
                    timeLabel.setForeground(timeLabel.getForeground().equals(Color.RED) ? colorButton.getForeground() : Color.RED);
                }
                colorChange = !colorChange;
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        Main clockGUI = new Main();
        clockGUI.setVisible(true);
    }
}
