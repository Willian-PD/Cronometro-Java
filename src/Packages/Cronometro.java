package Packages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {

    private JLabel countTime;
    private Timer tm;
    private int count = 0;
    private boolean rodando = false;

    public void init(){

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame window = new JFrame("Cronometro");
        window.setSize(300, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        window.setVisible(true);

        countTime = new JLabel("00:00:00");
        countTime.setFont(new Font(countTime.getName(), Font.PLAIN,80));
        window.add(countTime, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton btiniciar = new JButton("Iniciar");
        JButton btParar = new JButton("Parar");
        JButton btZerar = new JButton("Zerar");

        btiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!rodando) {
                    tm = new Timer();
                    rodando = (true);
                    tm.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            count++;
                            int seg = count % 60;
                            int min = count / 60;
                            int hora = min / 60;
                            min %= 60;
                            countTime.setText(String.format("%02d:%02d:%02d", hora, min, seg));
                            if(min % 2 == 0)
                                window.getContentPane().setBackground(Color.cyan);
                            else 
                                window.getContentPane().setBackground(Color.green);
                        }
                    }, 1000, 1000);
                }
            }
        });

        btParar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rodando) {
                    tm.cancel();
                    rodando = false;
                }
            }
        });

        btZerar.addActionListener(e -> {
            if (!rodando) {
                count = 0;
                int seg = count % 60;
                int min = count / 60;
                int hora = min / 60;
                min %= 60;
                countTime.setText(String.format("%02d:%02d:%02d", hora, min, seg));
                tm.cancel();
            } else
                count = -1;
        });

        panel.add(btiniciar);
        panel.add(btParar);
        panel.add(btZerar);
        panel.add(new JLabel(new ImageIcon("./Logo_UniLaSalle.png")));

        window.add(panel, BorderLayout.EAST);
        window.pack();
        window.setVisible(true);
    }
}
