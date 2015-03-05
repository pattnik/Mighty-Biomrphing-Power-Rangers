package MBPR;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class MainGUI extends JFrame 
{

//	private JLabel celsiusLabel;
//	private JButton generateButton;
//	private JLabel fahrenheitLabel;
//	private JTextField tempTextField;
		
	private JLabel currentImageLabel;
	private JLabel currentImage;
	private JLabel editLabel;
	private JLabel historyLabel;
	private GUI gui;
	private JButton generateButton;
	private JLabel historyImage1;
	private JLabel historyImage2;
	private JLabel historyImage3;
	private JLabel setupTime;
	private JLabel redrawTime;
	
	private Container pane;
	
	private JPanel leftPane;
	private JPanel centerPane;
	private JPanel rightPane;

	public MainGUI() 
	{
		initComponents();
	}

	private void initComponents() 
	{
		
		currentImageLabel = new JLabel();
		currentImage = new JLabel();
		editLabel = new JLabel();
		historyLabel = new JLabel();
		gui = new GUI();
		generateButton = new JButton();
		historyImage1 = new JLabel();
		historyImage2 = new JLabel();
		historyImage3 = new JLabel();
		setupTime = new JLabel();
		redrawTime = new JLabel();
		
		pane = getContentPane();
		
		leftPane = new JPanel();
		centerPane = new JPanel();
		rightPane = new JPanel();
		
//		tempTextField = new JTextField();
//		celsiusLabel = new JLabel();
//		generateButton = new JButton();
//		fahrenheitLabel = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Biomorph Generator");
		
		currentImageLabel.setText("Current Image");
		editLabel.setText("");
		historyLabel.setText("History");
		historyImage1.setIcon(null);
		historyImage2.setIcon(null);
		historyImage3.setIcon(null);
		currentImage.setIcon(new ImageIcon(gui.getCanvas().getImg()));
		redrawTime.setText("Time:");
		
		
		generateButton.setText("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});
		
//		celsiusLabel.setText("Celsius");
//
//		generateButton.setText("Generate");
//		generateButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				generateButtonActionPerformed(evt);
//			}
//		});
//
//		fahrenheitLabel.setText("Fahrenheit");
		
		BorderLayout layout2 = new BorderLayout(10,10);
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
		pane.add(leftPane, BorderLayout.LINE_START);
		
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
		pane.add(centerPane, BorderLayout.CENTER);
		
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
		pane.add(rightPane, BorderLayout.LINE_END);
		
		currentImageLabel.setPreferredSize(new Dimension(100, 20));
		editLabel.setPreferredSize(new Dimension(10, 20));
		historyLabel.setPreferredSize(new Dimension(100, 20));
		currentImage.setPreferredSize(new Dimension(500, 500));
		historyImage1.setPreferredSize(new Dimension(200, 200));
		historyImage2.setPreferredSize(new Dimension(200, 200));
		historyImage3.setPreferredSize(new Dimension(200, 200));
		redrawTime.setPreferredSize(new Dimension(170, 20));
		
		leftPane.add(currentImageLabel);
		leftPane.add(currentImage);
		leftPane.add(generateButton);
		leftPane.add(redrawTime);
		
		centerPane.add(editLabel);
		
		rightPane.add(historyLabel);
		rightPane.add(historyImage1);
		rightPane.add(historyImage2);
		rightPane.add(historyImage3);

//		GroupLayout layout = new GroupLayout(getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setHorizontalGroup(
//				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//								.addGroup(layout.createSequentialGroup()
//										.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//										.addComponent(celsiusLabel))
//										.addGroup(layout.createSequentialGroup()
//												.addComponent(generateButton)
//												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//												.addComponent(fahrenheitLabel)))
//												.addContainerGap(27, Short.MAX_VALUE))
//				);
//
//		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {generateButton, tempTextField});
//
//		layout.setVerticalGroup(
//				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//								.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//								.addComponent(celsiusLabel))
//								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//										.addComponent(generateButton)
//										.addComponent(fahrenheitLabel))
//										.addContainerGap(21, Short.MAX_VALUE))
//				);
		pack();
	}

	private void generateButtonActionPerformed(ActionEvent evt) 
	{
		long startTime = (new Date()).getTime();
		historyImage3.setIcon(historyImage2.getIcon());
		historyImage2.setIcon(historyImage1.getIcon());
		historyImage1.setIcon(new ImageIcon(gui.getCanvas().getScaledImage(200, 200)));
		gui.generate();
		currentImage.setIcon(new ImageIcon(gui.getCanvas().getImg()));
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		redrawTime.setText("Generate Time: (" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
		
	}

	public static void main(String args[]) 
	{
		long startTime = (new Date()).getTime();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainGUI().setVisible(true);
			}
		});

		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
}