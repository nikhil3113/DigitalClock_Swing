import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.Duration;
import java.time.LocalTime;


class MainFrame extends JFrame{
    Container c;
    JLabel labClock,labImg,labImgUs,labImgJp, labJpTime, labImgUae, labUaeTime, labUkTime, labImgUk,labImgCan, labCanTime, labstopWatch,labstpWatchTitle,labAlarm, labUsTime,labCount,labWorldClock;
    JButton btnStart, btnStop, btnReset, btnAlarmSet, btnAlarmCancel, btnCountStart, btnCountReset;
    JTextField txtAlarm,txtCount;
    long lastTickTime;
    Timer alarmTimer, countTimer;
    int timeLeft ;
    boolean isCountRunning = false;

    MainFrame(){
        c= getContentPane();
        c.setLayout(null);
        
        DateFormat dt = new SimpleDateFormat("HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        //!America/New_York
        TimeZone timeZone = TimeZone.getTimeZone("America/New_York"); 
        DateFormat dtUS = new SimpleDateFormat("hh:mm:ss");
        dtUS.setTimeZone(timeZone);
        //!Japan/Tokyo
        TimeZone timeJp = TimeZone.getTimeZone("Japan/Tokyo"); 
        DateFormat dtJp = new SimpleDateFormat("hh:mm:ss");
        dtJp.setTimeZone(timeJp);
        //!Asia/Dubai
        TimeZone timeUae = TimeZone.getTimeZone("Asia/Dubai"); 
        DateFormat dtUae = new SimpleDateFormat("hh:mm:ss");
        dtUae.setTimeZone(timeUae);
        //!Europe/London
        TimeZone timeUk = TimeZone.getTimeZone("Europe/London"); 
        DateFormat dtUk = new SimpleDateFormat("hh:mm:ss");
        dtUk.setTimeZone(timeUk);
        //!America/Toronto
        TimeZone timeCan = TimeZone.getTimeZone("America/Toronto"); 
        DateFormat dtCan = new SimpleDateFormat("hh:mm:ss");
        dtCan.setTimeZone(timeCan);

        ImageIcon image = new ImageIcon("images/india.png");
        ImageIcon imageUs = new ImageIcon("images/US.png");
        ImageIcon imageJp = new ImageIcon("images/jp.png");
        ImageIcon imageUae = new ImageIcon("images/uae.png");
        ImageIcon imageUk = new ImageIcon("images/uk.png");
        ImageIcon imagecan = new ImageIcon("images/canada.png");
        
        
        labImg = new JLabel(image);
        labImgUs = new JLabel(imageUs);
        labImgJp = new JLabel(imageJp);
        labImgUae = new JLabel(imageUae);
        labImgUk = new JLabel(imageUk);
        labImgCan = new JLabel(imagecan);
        labClock = new JLabel(dt.format(cal.getTime()));
        labUsTime = new JLabel(dtUS.format(cal.getTime()));
        labJpTime = new JLabel(dtJp.format(cal.getTime()));
        labUaeTime = new JLabel(dtUae.format(cal.getTime()));
        labUkTime = new JLabel(dtUk.format(cal.getTime()));
        labCanTime = new JLabel(dtCan.format(cal.getTime()));
        labWorldClock = new JLabel("<html><u>WorldClock</u></html>");

        labstopWatch = new JLabel(String.format("%02d:%02d:%02d.%03d", 0, 0, 0, 0));
        labstpWatchTitle = new JLabel("StopWatch");
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnReset = new JButton("Reset");

        labAlarm= new JLabel("Alarm");
        txtAlarm =new JTextField(8);
        btnAlarmSet = new JButton("Set");
        btnAlarmCancel = new JButton("Cancel");

        labCount = new JLabel("CountDown");
        txtCount = new JTextField(8);
        btnCountStart = new JButton("Start");
        btnCountReset = new JButton("Reset");        

        Font f = new Font("Arial", Font.BOLD, 50);
        labClock.setFont(f);
        labUsTime.setFont(f);
        labJpTime.setFont(f);
        labUaeTime.setFont(f);
        labUkTime.setFont(f);
        labCanTime.setFont(f);
        labWorldClock.setFont(f);

        Font f2 = new Font("Arial", Font.BOLD, 20);
        labstopWatch.setFont(f);
        labstpWatchTitle.setFont(f);
        btnStart.setFont(f2);
        btnStop.setFont(f2);
        btnReset.setFont(f2);

        labAlarm.setFont(f);
        txtAlarm.setFont(f);
        btnAlarmSet.setFont(f2);
        btnAlarmCancel.setFont(f2);

        labCount.setFont(f);
        txtCount.setFont(f);
        btnCountStart.setFont(f2);
        btnCountReset.setFont(f2);


        labClock.setBounds(350, 100, 300, 40);
        labImg.setBounds(240, 30, 300, 50);
        labImgUs.setBounds(240, 150, 300,40);
        labUsTime.setBounds(350,210,300,40);
        labImgJp.setBounds(480,30,300,50);
        labJpTime.setBounds(600,100,300,40);
        labImgUae.setBounds(480, 150, 300 ,40);
        labUaeTime.setBounds(600,210,300,40);
        labImgUk.setBounds(740,30,300,40);
        labUkTime.setBounds(850,100,300,40);
        labImgCan.setBounds(740,150,300,40);
        labCanTime.setBounds(850,210,300,40);

        labWorldClock.setBounds(550,310,300,40);

        labstpWatchTitle.setBounds(10, 300, 350,50);
        labstopWatch.setBounds(50, 380,350,50);
        btnStart.setBounds(40,460,100,50);
        btnStop.setBounds(140,460,100,50);
        btnReset.setBounds(230,460,100,50);

        labAlarm.setBounds(1100,300,350,50);
        txtAlarm.setBounds(1150, 380, 350,50);
        btnAlarmSet.setBounds(1150,460,100,50);
        btnAlarmCancel.setBounds(1250, 460, 100, 50);

        labCount.setBounds(600, 550, 350, 50);
        txtCount.setBounds(600, 630, 350, 50);
        btnCountStart.setBounds(600, 700, 100, 50);
        btnCountReset.setBounds(750, 700, 100, 50);

        labstpWatchTitle.setForeground(Color.RED);
        labAlarm.setForeground(Color.RED);
        labCount.setForeground(Color.RED);
        labWorldClock.setForeground(Color.MAGENTA);

        //!Clock
        ActionListener IndTime = (ae) ->{
             Calendar now = Calendar.getInstance();
            labClock.setText(dt.format(now.getTime()));
        };

        new Timer(1000, IndTime).start();
        
        ActionListener UsTime = (ae) -> {
            Calendar now = Calendar.getInstance();
            labUsTime.setText(dtUS.format(now.getTime()));
        };
         new Timer(1000, UsTime).start();

        ActionListener JpTime = (ae) -> {
            Calendar now = Calendar.getInstance();
            labJpTime.setText(dtJp.format(now.getTime()));
        };
         new Timer(1000, JpTime).start();

        ActionListener UaeTime = (ae) -> {
            Calendar now = Calendar.getInstance();
            labUaeTime.setText(dtUae.format(now.getTime()));
        };
         new Timer(1000, UaeTime).start();
         
        ActionListener UkTime = (ae) -> {
            Calendar now = Calendar.getInstance();
            labUkTime.setText(dtUk.format(now.getTime()));
        };
         new Timer(1000, UkTime).start();

         ActionListener CanTime = (ae) -> {
            Calendar now = Calendar.getInstance();
            labCanTime.setText(dtCan.format(now.getTime()));
        };
         new Timer(1000, CanTime).start();

        //!Stopwatch
        ActionListener a1 = (ae) ->{
            long runningTime = System.currentTimeMillis() - lastTickTime;
            Duration duration = Duration.ofMillis(runningTime);
            long hours = duration.toHours();
            duration = duration.minusHours(hours);
            long minutes = duration.toMinutes();
            duration = duration.minusMinutes(minutes);
            long millis = duration.toMillis();
            long seconds = millis / 1000;
            millis -= (seconds * 1000);
            labstopWatch.setText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis));
        };
        Timer timer= new Timer(100, a1);

        ActionListener start = (ae) ->{
            if(!timer.isRunning()){
                lastTickTime = System.currentTimeMillis();
                timer.start();
            }
        };
        btnStart.addActionListener(start);

        ActionListener stop = (ae) ->{
            timer.stop();
        };
        btnStop.addActionListener(stop);

        ActionListener reset = (ae) ->{
             labstopWatch.setText(String.format("%02d:%02d:%02d.%03d", 0, 0, 0, 0));
        };
        btnReset.addActionListener(reset);

        //!Alarm
        ActionListener triggerAlarm = (ae) ->{
            JOptionPane.showMessageDialog(c, "Alarm!");
            alarmTimer.stop();
        };

        ActionListener alarmSet = (ae) -> {
        String alarmTime = txtAlarm.getText();
        if (!alarmTime.isEmpty()) {
            if (alarmTime.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"))  {
                try {
                    LocalTime now = LocalTime.now();
                    LocalTime alarm = LocalTime.parse(alarmTime);

                    Duration duration = Duration.between(now, alarm);
                    long delay = duration.toMillis();
                System.out.println("Alarm time:" + alarmTime);
                System.out.println(delay);
                    if (delay > 0) {
                        alarmTimer = new Timer((int) delay, triggerAlarm);
                        alarmTimer.start();
                        JOptionPane.showMessageDialog(c, "Alarm is set for " + alarmTime);
                     
                    } else {
                        JOptionPane.showMessageDialog(c, "Invalid alarm time", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(c, "parse Error", "Error", JOptionPane.ERROR_MESSAGE); 
                }
            } else {
                JOptionPane.showMessageDialog(c, "Invalid alarm time format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
        btnAlarmSet.addActionListener(alarmSet);


        ActionListener alarmCancel= (ae) ->{
            txtAlarm.setText("");
            alarmTimer.stop();
        };
        btnAlarmCancel.addActionListener(alarmCancel);


        txtCount.setText(String.format("%02d:%02d:%02d", 0, 1, 0));
        
        //!CountDown
         ActionListener countStart = (ae) -> {
            if (timeLeft <= 0) {
                countTimer.stop();
                JOptionPane.showMessageDialog(c, "Countdown complete!");
                btnCountStart.setEnabled(true);
                btnCountStart.setText("Start"); 
                isCountRunning = false;
            } else {
                int hours = timeLeft / 3600;
                int minutes = (timeLeft % 3600) / 60;
                int seconds = (timeLeft % 3600) % 60;

                String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                txtCount.setText(formattedTime);

                timeLeft--;
            }
        };

        btnCountStart.addActionListener((ae) -> {
            if (!isCountRunning) {
                if (countTimer != null) {
                    countTimer.stop();
                }

                String inputTime = txtCount.getText();
                String[] timeParts = inputTime.split(":");

                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);
                int seconds = Integer.parseInt(timeParts[2]);

                timeLeft = hours * 3600 + minutes * 60 + seconds;
                countTimer = new Timer(1000, countStart);
                countTimer.start();

                btnCountStart.setEnabled(true);
                btnCountStart.setText("Stop"); 
                isCountRunning = true;
            } else {
                countTimer.stop();
                btnCountStart.setText("Start"); 
                isCountRunning = false;
            }
        });
        ActionListener countReset = (ae) ->{
             txtCount.setText(String.format("%02d:%02d:%02d", 0, 1, 0));
        };
        btnCountReset.addActionListener(countReset);

        c.add(labClock);
        c.add(labImg);
        c.add(labUsTime);
        c.add(labImgUs);
        c.add(labImgJp);
        c.add(labJpTime);
        c.add(labUaeTime);
        c.add(labImgUae);
        c.add(labImgUk);
        c.add(labUkTime);
        c.add(labCanTime);
        c.add(labImgCan);
        c.add(labWorldClock);
        c.add(labstpWatchTitle);
        c.add(labstopWatch);
        c.add(btnStart);
        c.add(btnStop);
        c.add(btnReset);
        c.add(labAlarm);
        c.add(txtAlarm);
        c.add(btnAlarmSet);
        c.add(btnAlarmCancel);
        c.add(labCount);
        c.add(txtCount);
        c.add(btnCountStart);
        c.add(btnCountReset);
        c.setBackground(Color.lightGray);

        setTitle("Digital Clock");
        Image icon = Toolkit.getDefaultToolkit().getImage("images/clock.png");  
        setIconImage(icon);  
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
