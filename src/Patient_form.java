
//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;

public class Patient_form extends JFrame {

	Connection conn = null;
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTextField Pname;
	private JTextField Mobile;
	private ButtonGroup bg = new ButtonGroup();
	private ButtonGroup bg2 = new ButtonGroup();
	private ButtonGroup bg3 = new ButtonGroup();

	private JLabel rno_1;
	private JLabel date;
	private JTextField DOB;

	private String Gender;
	private String Martial;
	private String Age;
	private JTextField Address_1;
	private JTextField age1;
	private JTextField Complaint_TextField;
	private JTextField Region_txtField;
	private String Status;

	// Get System date

	public void clock() {

		DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Date d2 = new Date();
		String d3 = d.format(d2);

		date.setText(d3);

	}

	public void counts() {
		conn = ConnectionMain.dbConnector();
		try {
			String sql = "Select count(reg) from patient";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				String add = rs.getString("count(reg)");

				rno_1.setText(add);
				rs.close();
				pst.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void correct() {

		if (rno_1.getText().equals("") || Pname.getText().equals("") || Address_1.getText().equals("")
				|| DOB.getText().equals("") || Mobile.getText().equals("") || Complaint_TextField.getText().equals("")
				|| Region_txtField.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Fill First All Field");

		} else {
			conn = ConnectionMain.dbConnector();

			try {

				String sql = "insert into patient(reg,pname,rdate,address,martial,gender,dob,age,phone) values (?,?,?,?,?,?,?,?,?)";
				String sql1 = "INSERT INTO jdbc.patienthistory(reg,region,complaint,adate,status,recoverypercent)\n"
						+ "VALUES (?,?,?,?,?,?);";

				pst = conn.prepareStatement(sql);
				pst1 = conn.prepareStatement(sql1);

				pst.setString(1, rno_1.getText());
				pst.setString(2, Pname.getText());
				pst.setString(3, date.getText());
				pst.setString(4, Address_1.getText());
				pst.setString(5, Martial);
				pst.setString(6, Gender);
				pst.setString(7, DOB.getText());
				pst.setString(8, Age);
				pst.setString(9, Mobile.getText());
				
				pst1.setString(1, rno_1.getText());
				pst1.setString(2, Region_txtField.getText());
				pst1.setString(3, Complaint_TextField.getText());
				pst1.setString(4, date.getText());
				pst1.setString(5, Status);
				if(Status == "Yes") {
					pst1.setString(6, "10");
				} else {
					pst1.setString(6, "0");
				}

				pst.execute();
				pst.close();
				pst1.execute();
				pst1.close();

				JOptionPane.showMessageDialog(null, "Done");

				counts();
				reset();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	private void reset() {

		Pname.setText("");
		Address_1.setText("");
		DOB.setText("");
		age1.setText("");
		Mobile.setText("");
	}

	public void close() {
		WindowEvent w = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(w);
	}

	private void age() {

		DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = new Date();
		String str2 = d.format(d1);

		String[] temp2;
		int[] a = new int[5];
		int b = 0;

		String str = DOB.getText();
		String[] temp;
		String delimeter = "/";
		temp = str.split(delimeter);

		String delimeter2 = "/";
		temp2 = str2.split(delimeter2);
		for (int i = 0; i < temp2.length; i++) {
			a[i] = Integer.parseInt(temp2[i]) - Integer.parseInt(temp[i]);

		}

		if (a[2] > 0) {
			b = a[2] - 1;
			if (a[1] > 0) {
				b = b + 1;
			} else if (a[1] == 0) {
				if (a[0] >= 0) {
					b = b + 1;
				}
			}
		}
		Age = String.valueOf(b);
		age1.setText(Age);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient_form frame = new Patient_form();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Patient_form() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblR = new JLabel("Registration Number");
		lblR.setBounds(54, 9, 200, 50);
		contentPane.add(lblR);

		JLabel lblNewLabel = new JLabel("Patient Name");
		lblNewLabel.setBounds(54, 47, 83, 37);
		contentPane.add(lblNewLabel);

		JLabel lblRigistrationDate = new JLabel("Registration Date");
		lblRigistrationDate.setBounds(54, 86, 114, 37);
		contentPane.add(lblRigistrationDate);

		JLabel lblPatientAddress = new JLabel("Patient Address");
		lblPatientAddress.setBounds(54, 134, 96, 37);
		contentPane.add(lblPatientAddress);

		JLabel lblMartialStatus = new JLabel("Martial Status");
		lblMartialStatus.setBounds(54, 218, 96, 50);
		contentPane.add(lblMartialStatus);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(54, 256, 83, 37);
		contentPane.add(lblGender);

		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(54, 286, 130, 39);
		contentPane.add(lblDateOfBirth);

		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setBounds(54, 392, 96, 29);
		contentPane.add(lblMobileNumber);

		Pname = new JTextField();
		Pname.setBounds(211, 55, 139, 20);
		contentPane.add(Pname);
		Pname.setColumns(10);

		Mobile = new JTextField();
		Mobile.setBounds(211, 396, 139, 20);
		contentPane.add(Mobile);
		Mobile.setColumns(10);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Unmarried");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Martial = "Unmarried";

			}
		});
		rdbtnNewRadioButton.setBounds(211, 231, 109, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnMarried = new JRadioButton("Married");
		rdbtnMarried.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Martial = "Married";
			}
		});
		rdbtnMarried.setBounds(322, 231, 109, 23);
		contentPane.add(rdbtnMarried);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Gender = "Male";
			}
		});
		rdbtnMale.setBounds(211, 256, 109, 23);
		contentPane.add(rdbtnMale);

		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Status = "Yes";

			}
		});
		rdbtnYes.setBounds(211, 433, 109, 23);
		contentPane.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Status = "No";

			}
		});
		rdbtnNo.setBounds(322, 433, 109, 23);
		contentPane.add(rdbtnNo);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Gender = "Female";
			}
		});
		rdbtnFemale.setBounds(322, 256, 109, 23);
		contentPane.add(rdbtnFemale);

		rno_1 = new JLabel("");

		rno_1.setBounds(211, 9, 200, 50);
		contentPane.add(rno_1);

		date = new JLabel("New label");
		date.setBounds(221, 86, 89, 37);
		contentPane.add(date);

		bg.add(rdbtnMarried);
		bg.add(rdbtnNewRadioButton);

		bg2.add(rdbtnFemale);
		bg2.add(rdbtnMale);
		
		bg3.add(rdbtnYes);
		bg3.add(rdbtnNo);

		clock();
		counts();

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				correct();

			}
		});
		btnNewButton.setBounds(91, 478, 89, 23);
		contentPane.add(btnNewButton);

		DOB = new JTextField();
		DOB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				age();
			}
		});

		DOB.setBounds(211, 293, 139, 24);
		contentPane.add(DOB);
		DOB.setColumns(10);

		Address_1 = new JTextField();
		Address_1.setBounds(211, 129, 139, 44);
		contentPane.add(Address_1);
		Address_1.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(54, 336, 46, 14);
		contentPane.add(lblAge);

		age1 = new JTextField();
		age1.setBounds(211, 333, 89, 20);
		contentPane.add(age1);
		age1.setColumns(10);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		btnReset.setBounds(211, 478, 89, 23);
		contentPane.add(btnReset);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				close();
			}
		});
		btnExit.setBounds(322, 478, 89, 23);
		contentPane.add(btnExit);

		JLabel lblNewLabel_1 = new JLabel("Complaint");
		lblNewLabel_1.setBounds(54, 367, 74, 14);
		contentPane.add(lblNewLabel_1);

		Complaint_TextField = new JTextField();
		Complaint_TextField.setColumns(10);
		Complaint_TextField.setBounds(211, 364, 139, 20);
		contentPane.add(Complaint_TextField);

		JLabel lblPatientRegion = new JLabel("Patient Region");
		lblPatientRegion.setBounds(54, 182, 96, 37);
		contentPane.add(lblPatientRegion);

		Region_txtField = new JTextField();
		Region_txtField.setColumns(10);
		Region_txtField.setBounds(211, 190, 139, 20);
		contentPane.add(Region_txtField);

		JLabel lblNewLabel_1_1 = new JLabel("Recovery Status");
		lblNewLabel_1_1.setBounds(54, 432, 114, 14);
		contentPane.add(lblNewLabel_1_1);

	}
}
