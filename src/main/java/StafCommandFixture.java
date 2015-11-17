import com.ibm.staf.STAFException;
import com.ibm.staf.STAFMarshallingContext;
import com.ibm.staf.STAFResult;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ekim on 2015-11-04.
 */
public class StafCommandFixture {
    protected String service;
    protected String location;
    protected String request;
    protected String pattern;
    protected String responseMsg;
    protected int returnCode = 0;
    protected StafHandleInterface handle = null;

    public StafCommandFixture() {
        try {
            handle = new StafHandleWrapper("StafHandle");
        } catch (STAFException e) {
            e.printStackTrace();
        }
    }

   protected void finalize() throws Throwable {
       super.finalize();

       if (null != handle) {
           handle.unRegister();
       }
   }

    public void setService(String service) {
        this.service = service;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int submitRequest() {
        if (null == handle) {
            return -1;
        }

        STAFResult stafResult = handle.submit2(location, service, request);
        responseMsg = convertEOL(unmarshall(stafResult.result));
        returnCode = stafResult.rc;
        return returnCode;
    }

    public int submitMarshall() {
        if (null == handle) {
            return -1;
        }

        STAFResult stafResult = handle.submit2(location, service, request);
        returnCode = stafResult.rc;

        if (stafResult.resultObj instanceof String) {
            responseMsg = stafResult.result;

            return returnCode;
        }

        Map resultMap = (Map)stafResult.resultObj;
        List returnedFileList = (List)resultMap.get("fileList");

        if (false == returnedFileList.isEmpty()) {
            Map stdoutMap = (Map)returnedFileList.get(0);
            responseMsg = (String)stdoutMap.get("data");
        }

        return returnCode;
    }

    public String responseMessage() {
        return responseMsg;
    }

    public String responsePattern() {
        String result = "";
        Pattern localPattern = Pattern.compile(pattern);
        Matcher localMatcher = localPattern.matcher(responseMsg);

        if (localMatcher.find()) {
            result += localMatcher.group();
        }

        return result;
    }

    public String getErrorMessage() {
        String errorMsg = "";

        if (0 != returnCode) {
            if (null != handle) {
                STAFResult resultErr = handle.submit2(location, "HELP", "ERROR " + returnCode);
                errorMsg = unmarshall(resultErr.result);
                errorMsg = convertEOL(errorMsg);
            } else {
                return "Couldn't get a STAF hanlder.";
            }
        }

        return errorMsg;
    }

    public String convertEOL(String str) {
        String resultStr = str;
        resultStr = resultStr.replaceAll("\r\n", "<br>");
        resultStr = resultStr.replaceAll("\r", "<br>");
        resultStr = resultStr.replaceAll("\n", "<br>");
        return resultStr;
    }

    public String unmarshall(String str) {
        STAFMarshallingContext marshallContext = STAFMarshallingContext.unmarshall(str);
        return marshallContext.toString();
    }

    // The following functions are optional.  If they aren't declared they'll be ignored.
    public void execute() {
    }

    public void reset() {
    }

    public void table(List<List<String>> table) {
    }

    public void beginTable() {
    }

    public void endTable() {
    }

}
