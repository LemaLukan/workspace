package assign5Package;
import java.util.* ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyObserverFrame  extends JFrame implements Observer{
	JLabel aLabel;
    JTextArea displayArea;
	private JTextField myInputArea;
    private NewBookingSystem bookingSystem;
    private ArrayList<Booking> selectedBookings;
    MyDate d;
	public MyObserverFrame(){
		super("Day and month selector");

		bookingSystem = NewBookingSystem. getInstance();
		bookingSystem.getObservable().addObserver(this);

		setLayout(new FlowLayout());
        aLabel = new JLabel("Specify a date in the format DD/MM/YYYY");
        add(aLabel);
        myInputArea  = new JTextField( 10 );

        myInputArea.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){

					d = new MyDate(myInputArea.getText());
					// Create an object of class MyDate based on supplied info
					bookingSystem.display(d); // find bookings on that date
		//		    bookingSystem.getBookings();
                }
			}
		);

		add(myInputArea);

		displayArea = new JTextArea(20, 20);
		add(displayArea);
	}

	public void update(Observable obj, Object arg) {
			ArrayList<Booking> selectedBookings;
			selectedBookings = (ArrayList<Booking>) arg;
		    if (selectedBookings.size() == 0) displayArea.append("No bookings on " + d.toString() + ".\n");
		  						else {
		  								displayArea.append("Bookings on " + d.toString() + ":\n");
		  								for (Booking aBooking:selectedBookings){
		  									displayArea.append(aBooking.toString() + "\n");
		  						        }
								}
    }

	public static void main(String a[]){
		MyObserverFrame d = new MyObserverFrame();
		d.setSize(300, 450);
		d.setVisible(true);
	}

}
