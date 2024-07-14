package BookingSystem;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestBooking.class,
    TestBookingFile.class,
    TestBookingIntegration.class
})
public class TestBookingSuites {
   
}
