import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PatientHistory extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTextField Pname;

	private JLabel reg_1;
	private JLabel date;
	private JLabel Date1;
	private JLabel RegNum;
	private JLabel PatientName;
	private JLabel lblRigistrationDate;
	private JLabel lblGender;
	private JLabel lblDateOfBirth;
	private JLabel ComplainLbl;
	private JLabel PatientRegion;
	private JLabel Datelbl;
	private JScrollPane scrollPane;
	private JButton btnViewHistory;
	private JTextField DOB;

	private JLabel lblAge;
	private JLabel lblSearch;
	private JButton Updatebtn;
	private JButton exitbtn;

	private JTextField age1;

	private JTextField Gender;
	private JTextField SearchRegNo;
	private JTextField Complaint_TextField;
	private JTextField Region_txtField;
	private String Status;

	private ButtonGroup bg2 = new ButtonGroup();
	private ButtonGroup bg3 = new ButtonGroup();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientHistory frame = new PatientHistory();
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
	public PatientHistory() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		RegNum = new JLabel("Rigistration Number");
		RegNum.setBounds(42, 60, 130, 44);
		contentPane.add(RegNum);

		PatientName = new JLabel("Patient Name");
		PatientName.setBounds(42, 102, 139, 38);
		contentPane.add(PatientName);

		lblRigistrationDate = new JLabel("Rigistration Date");
		lblRigistrationDate.setBounds(42, 143, 109, 38);
		contentPane.add(lblRigistrationDate);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(44, 180, 99, 38);
		contentPane.add(lblGender);

		lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(42, 217, 114, 38);
		contentPane.add(lblDateOfBirth);

		ComplainLbl = new JLabel("Complaint");
		ComplainLbl.setBounds(42, 323, 74, 14);
		contentPane.add(ComplainLbl);

		Complaint_TextField = new JTextField();
		Complaint_TextField.setColumns(10);
		Complaint_TextField.setBounds(291, 323, 139, 20);
		contentPane.add(Complaint_TextField);

		Region_txtField = new JTextField();
		Region_txtField.setColumns(10);
		Region_txtField.setBounds(291, 294, 139, 20);
		contentPane.add(Region_txtField);

		JLabel RSlbl = new JLabel("Recovery Status");
		RSlbl.setBounds(42, 368, 114, 19);
		contentPane.add(RSlbl);

		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Status = "Yes";

			}
		});
		rdbtnYes.setBounds(291, 364, 109, 23);
		contentPane.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Status = "No";

			}
		});
		rdbtnNo.setBounds(402, 364, 109, 23);
		contentPane.add(rdbtnNo);

		bg3.add(rdbtnYes);
		bg3.add(rdbtnNo);

		Pname = new JTextField();
		Pname.setEditable(false);
		Pname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				conn = ConnectionMain.dbConnector();
				try {

					String sql = "select * from patient where pname=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, Pname.getText());
					rs = pst.executeQuery();
					if (rs.next()) {
						String add1 = rs.getString("reg");
						reg_1.setText(add1);
						String add2 = rs.getString("rdate");
						date.setText(add2);

						String add5 = rs.getString("gender");
						Gender.setText(add5);
						String add6 = rs.getString("dob");
						DOB.setText(add6);
						String add7 = rs.getString("age");
						age1.setText(add7);
						// String add8=rs.getString("pname");
						// Pname.setText(add8);

					}
					rs.close();
					pst.close();
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		Pname.setBounds(291, 111, 139, 20);
		contentPane.add(Pname);
		Pname.setColumns(10);

		date = new JLabel("");
		date.setBounds(301, 143, 124, 30);
		contentPane.add(date);

		DOB = new JTextField();
		DOB.setEditable(false);
		DOB.setBounds(291, 226, 139, 20);
		contentPane.add(DOB);
		DOB.setColumns(10);

		lblAge = new JLabel("Age");
		lblAge.setBounds(42, 260, 46, 14);
		contentPane.add(lblAge);

		age1 = new JTextField();
		age1.setBounds(291, 257, 89, 20);
		age1.setEditable(false);
		contentPane.add(age1);
		age1.setColumns(10);

		Gender = new JTextField();
		Gender.setBounds(291, 189, 139, 19);
		Gender.setEditable(false);
		contentPane.add(Gender);
		Gender.setColumns(10);

		reg_1 = new JLabel("");
		reg_1.setBounds(301, 73, 124, 20);
		contentPane.add(reg_1);

		lblSearch = new JLabel("Search ");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBackground(Color.BLACK);
		lblSearch.setBounds(42, 9, 63, 14);
		contentPane.add(lblSearch);

		SearchRegNo = new JTextField();
		SearchRegNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				conn = ConnectionMain.dbConnector();
				try {

					String sql = "select * from patient where reg=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, SearchRegNo.getText());
					rs = pst.executeQuery();
					if (rs.next()) {
						String add1 = rs.getString("reg");
						reg_1.setText(add1);
						String add2 = rs.getString("rdate");
						date.setText(add2);

						String add5 = rs.getString("gender");
						Gender.setText(add5);
						String add6 = rs.getString("dob");
						DOB.setText(add6);
						String add7 = rs.getString("age");
						age1.setText(add7);
						String add8 = rs.getString("pname");
						Pname.setText(add8);

					}
					rs.close();
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

				try {

					String sql = "select * from patient where pname=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, SearchRegNo.getText());
					rs = pst.executeQuery();
					if (rs.next()) {
						String add1 = rs.getString("reg");
						reg_1.setText(add1);
						String add2 = rs.getString("rdate");
						date.setText(add2);

						String add5 = rs.getString("gender");
						Gender.setText(add5);
						String add6 = rs.getString("dob");
						DOB.setText(add6);
						String add7 = rs.getString("age");
						age1.setText(add7);
						String add8 = rs.getString("pname");
						Pname.setText(add8);

					}
					rs.close();
					pst.close();
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

				try {

					String sql = "select * from patient where phone=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, SearchRegNo.getText());
					rs = pst.executeQuery();
					if (rs.next()) {
						String add1 = rs.getString("reg");
						reg_1.setText(add1);
						String add2 = rs.getString("rdate");
						date.setText(add2);

						String add5 = rs.getString("gender");
						Gender.setText(add5);
						String add6 = rs.getString("dob");
						DOB.setText(add6);
						String add7 = rs.getString("age");
						age1.setText(add7);
						String add8 = rs.getString("pname");
						Pname.setText(add8);

					}
					rs.close();
					pst.close();
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

			}

		});
		SearchRegNo.setBounds(42, 38, 152, 30);
		contentPane.add(SearchRegNo);
		SearchRegNo.setColumns(10);

		Updatebtn = new JButton("Update");
		Updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				correct();
//				conn = ConnectionMain.dbConnector();
//				String value3 = reg_1.getText();
//				try {
////					String sql = "";
////					pst = conn.prepareStatement(sql);
////					pst.execute();
////					JOptionPane.showMessageDialog(null, "Updated");
//					rs.close();
//					pst.close();
//
//				} catch (Exception e) {
//					JOptionPane.showMessageDialog(null, e);
//				}

			}
		});
		Updatebtn.setBounds(52, 407, 89, 23);
		contentPane.add(Updatebtn);

		JButton exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		exitbtn.setBounds(402, 407, 89, 23);
		contentPane.add(exitbtn);

		PatientRegion = new JLabel("Patient Region");
		PatientRegion.setBounds(42, 286, 96, 37);
		contentPane.add(PatientRegion);

		btnViewHistory = new JButton("View History");
		btnViewHistory.setBounds(230, 407, 109, 23);
		contentPane.add(btnViewHistory);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 696, 744, -229);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Datelbl = new JLabel("Date");
		Datelbl.setBounds(253, 11, 46, 14);
		contentPane.add(Datelbl);

		DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Date d2 = new Date();
		String d3 = d.format(d2);

		Date1 = new JLabel("");
		Date1.setText(d3);
		Date1.setBounds(291, 46, 68, 14);
		contentPane.add(Date1);

	}

	private void reset() {

		SearchRegNo.setText("");
		Complaint_TextField.setText("");
		Region_txtField.setText("");

	}

	private void correct() {

		if (Complaint_TextField.getText().equals("") || Region_txtField.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Fill First All Field");

		} else {
			conn = ConnectionMain.dbConnector();

			try {

				String sql1 = "INSERT INTO jdbc.patienthistory(reg,region,complaint,adate,status,recoverypercent)\n"
						+ "VALUES (?,?,?,?,?,?);";

				pst1 = conn.prepareStatement(sql1);

				pst1.setString(1, reg_1.getText());
				pst1.setString(2, Region_txtField.getText());
				pst1.setString(3, Complaint_TextField.getText());
				pst1.setString(4, Date1.getText());
				pst1.setString(5, Status);
				if (Status == "Yes") {
					pst1.setString(6, "10");
				} else {
					pst1.setString(6, "0");
				}

				pst1.execute();
				pst1.close();

				JOptionPane.showMessageDialog(null, "Done");

//				counts();
				reset();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

}
