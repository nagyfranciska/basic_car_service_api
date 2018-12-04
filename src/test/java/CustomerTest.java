//import dao.CustomerDAO;
//import model.Customer;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.*;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Mockito.*;
//import service.CustomerService;
//
//
////TODO: add hibernate mock
//
//public class CustomerTest {
//
//    @Mock
//    private CustomerDAO dao;
//
//    @InjectMocks
//    private CustomerService service;
//
//    public CustomerTest() {
//    }
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void successfulSave() {
////        when(dao.save(any(Customer.class))).thenReturn(new Customer());
////        Customer dummy = new Customer("Jane Doe", "Under the Bridge", "A Better Place", "free");
////        when(dao.save(dummy)).thenReturn(dummy);
////        Customer result = service.saveCustomer(dummy);
////        Assert.assertThat(result.getName(), is("Jane Doe"));
//    }
//}
