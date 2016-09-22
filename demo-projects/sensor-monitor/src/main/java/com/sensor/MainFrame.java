package com.sensor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

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
	private int completedTaskCount = 0;
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
			Collections.sort(tasks);
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
				new Thread(new Runnable() {
					@Override
					public void run() {
						btnRestartActionPerformed(e);
					}
				}).start();
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (consoleText) {
						console.setText(consoleText.toString());
						DefaultCaret caret = (DefaultCaret) console.getCaret();
						caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
						progressBar.setValue(completedTaskCount);
						try {
							consoleText.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
	
	private void btnRestartActionPerformed(ActionEvent e) {
		try {
			while (taskIndex < tasks.size()) {
				Task task = tasks.get(taskIndex++);
				consoleText.append(task.getCommand()+"\r\n");
				final Process process;
				String command = null;				
				if (task.getCommand().startsWith("file:")) {
					String path = task.getCommand().split("file:///")[1];
					command = "cmd.exe /c call " + path;
					process = Runtime.getRuntime().exec(command);
				} else {
					command = "cmd.exe /c "+task.getCommand();
					process = Runtime.getRuntime().exec(command);
				}
				
				Thread temp = new Thread(new Runnable() {
					@Override
					public void run() {
						byte[] buff = new byte[1024];
						int readLen = -1;
						try {
							while((readLen = process.getInputStream().read(buff)) != -1) {
								synchronized (consoleText) {
									consoleText.append(new String(buff, 0, readLen));
									consoleText.notify();
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				temp.start();
				while(process.isAlive()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				completedTaskCount++;
			}
			synchronized (consoleText) {
				consoleText.notify();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
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
