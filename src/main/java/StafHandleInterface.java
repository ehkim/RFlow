import com.ibm.staf.STAFResult;

/**
 * Created by ekim on 2015-11-04.
 */
public interface StafHandleInterface {
    void unRegister();

    STAFResult submit2(String location, String service, String request);
}
