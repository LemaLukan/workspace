package assign5Package;
import java.util.* ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	JLabel aLabel;
    JTextArea displayArea;
	private JTextField myInputArea;

    private BookingSystem bookingSystem;
    private ArrayList<Booking> selectedBookings;

	public MyFrame(){
		super("Day and month selector");

//		bookingSystem = BookingSystem.getInstance();
        bookingSystem = new BookingSystem();
		setLayout(new FlowLayout());
        aLabel = new JLabel("Specify a date in the format DD/MM/YYYY");
        add(aLabel);
        myInputArea  = new JTextField( 10 );

        myInputArea.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					MyDate d;
					d = new MyDate(myInputArea.getText());
					// Create an object of class MyDate based on supplied info
					bookingSystem.display(d); // find bookings on that date
					selectedBookings = bookingSystem.getBookings();// rettrieve the bookings
					if (selectedBookings.size() == 0) displayArea.append("No bookings on " + d.toString() + ".\n");
					else {
						displayArea.append("Bookings on " + d.toString() + ":\n");
						for (Booking aBooking:selectedBookings){
							displayArea.append(aBooking.toString() + "\n");
						}
					}
                }
			}
		);

		add(myInputArea);

		displayArea = new JTextArea(20, 20);
		add(displayArea);
	}

	public static void main(String a[]){
		MyFrame d = new MyFrame();
		d.setSize(300, 450);
		d.setVisible(true);
	}

}
