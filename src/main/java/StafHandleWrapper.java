import com.ibm.staf.STAFException;
import com.ibm.staf.STAFHandle;
import com.ibm.staf.STAFResult;

/**
 * Created by ekim on 2015-11-04.
 */
public class StafHandleWrapper implements StafHandleInterface {
    private STAFHandle handle;

    public StafHandleWrapper(String arg) throws STAFException {
        handle = new STAFHandle(arg);
    }

    public void unRegister() {
        try {
            handle.unRegister();
        } catch (STAFException e) {
            e.printStackTrace();
        }
    }

    public STAFResult submit2(String location, String service, String request) {
        return handle.submit2(location, service, request);
    }
}
