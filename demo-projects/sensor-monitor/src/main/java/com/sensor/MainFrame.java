package com.sensor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final int PARENT_WIDTH = 1000;
	private static final int PARENT_HEIGHT = 800;

	private JPanel mainPanel;
	private JButton btnRestart;
	private JScrollPane scrollPane;
	private JTextArea console;
	private JLabel lblProgress;
	private JProgressBar progressBar;
	
	private Properties labelConfig;
	private int taskIndex;
	private List<Task> tasks;
	private StringBuilder consoleText;
	
	public MainFrame() {
		taskIndex = 0;		
		consoleText = new StringBuilder();
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/config.ini"));
			tasks = new ArrayList<>(prop.entrySet().size());
			for (Entry<Object, Object> entry : prop.entrySet()) {
				Task task = new Task();
				task.setId(String.valueOf(entry.getKey()));
				task.setCommand(String.valueOf(entry.getValue()));
				tasks.add(task);
			}
			
			labelConfig = new Properties();
			labelConfig.load(new FileInputStream("src/main/resources/label-config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initComponents();
	}
	
	public void initComponents() {
		mainPanel = new JPanel();		
		btnRestart = new JButton();
		lblProgress = new JLabel(labelConfig.getProperty("progressLabelText"));
		progressBar = new JProgressBar(0, 100);		
		console = new JTextArea();
		scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(console);	
		scrollPane.setPreferredSize(new Dimension(PARENT_WIDTH - 20, PARENT_HEIGHT - (int) btnRestart.getPreferredSize().getHeight()-100));
		
		btnRestart.setText("Restart");
		
		progressBar.setMaximum(tasks.size());
		progressBar.setMinimum(0);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(PARENT_WIDTH - 100, 30));
		
		setTitle("Restart Sensor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(PARENT_WIDTH, PARENT_HEIGHT));
		
		
		mainPanel.add(btnRestart, BorderLayout.NORTH);
		mainPanel.add(lblProgress, BorderLayout.CENTER);
		mainPanel.add(progressBar, BorderLayout.CENTER);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		add(mainPanel);
		
		
		btnRestart.setPreferredSize(new Dimension(PARENT_WIDTH - 20, 30));
		
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRestartActionPerformed(e);
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					console.setText(consoleText.toString());
					progressBar.setValue(taskIndex);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void btnRestartActionPerformed(ActionEvent e) {
		while (taskIndex < tasks.size()) {
			consoleText.append(tasks.get(taskIndex++).getCommand()+"\r\n");
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new MainFrame().setVisible(true);
		    }
		});
	}
}
