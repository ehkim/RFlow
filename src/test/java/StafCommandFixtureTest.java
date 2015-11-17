import com.ibm.staf.STAFException;
import com.ibm.staf.STAFResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by ekim on 2015-11-16.
 */
public class StafCommandFixtureTest {
    private StafCommandFixture stafCmdFixture;

    @Before
    public void setUp() throws Exception {
        stafCmdFixture = new StafCommandFixture();
        initStafCmdFixtureVariable();
    }

    private void initStafCmdFixtureVariable() {
        stafCmdFixture.location = "local";
        stafCmdFixture.service = "ping";
        stafCmdFixture.request = "ping";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Ignore
    @Test
    public void testSubmit2NomalCase() {
        assertEquals(0, stafCmdFixture.submitMarshall());
    }

    @Ignore
    @Test
    public void testSubmit2NullDataCase() {
        stafCmdFixture.location = "local";
        stafCmdFixture.service = "process";
        stafCmdFixture.request = "START SHELL COMMAND javac aaa.java bbb.java WAIT";
        stafCmdFixture.submitMarshall();
        assertNull(stafCmdFixture.responseMsg);
    }

    @Ignore
    @Test
    public void testResponseValue() {
        testSubmit2NomalCase();
        assertEquals("PONG", stafCmdFixture.responseMsg);
    }

    private int submitToMock(String location, String service, String request, STAFResult actualResult) {
        //StafHandleInterface handleInterface = mock(StafHandleInterface.class);
        //when((ObservableBooleanValue) handleInterface.submit2(location, service, request)).then(actualResult);

        //stafCmdFixture.handle = handleInterface;
        stafCmdFixture.location = location;
        stafCmdFixture.service = service;
        stafCmdFixture.request = request;

        return stafCmdFixture.submitRequest();
    }

    @Ignore
    @Test
    public void testPingSuccessCase() throws STAFException {
        int returnCode = submitToMock("local", "PING", "PING", new STAFResult(0, "PONG"));
        assertEquals(0, returnCode);
    }

    @Ignore
    @Test
    public void testPingFailCaseErrorCode() throws STAFException {
        int returnCode = submitToMock("1.1.1.1", "PING", "PING", new STAFResult(16, "PONG"));
        assertEquals(16, returnCode);
    }

    @Ignore
    @Test
    public void testPingFailCaseErrorMessage() throws STAFException {
        int returnCode = submitToMock("gggg", "PING", "PING", new STAFResult(16, "PONG"));
        assertEquals(16, returnCode);
    }

    @Ignore
    @Test
    public void testEcho() throws STAFException {
        submitToMock("local", "ECHO", "ECHO Hello World", new STAFResult(0, "Hello World"));
        assertEquals("Hello World", stafCmdFixture.responsePattern());
    }

    @Ignore
    @Test
    public void testListDirectory() throws STAFException {
        STAFResult mockResult = new STAFResult(0, "@SDT/[3:64:@SDT/$S:12:autoexec.bat@SDT/$S:13:Program Files@SDT/$S:7:Windows");
        submitToMock("local", "FS", "LIST DIRECTORY C:\\", mockResult);
        assertEquals("[<br>  autoexec.bat<br>  Program Files<br>  Windows<br>]",  stafCmdFixture.responsePattern());
    }

    @Ignore
    @Test
    public void testResponseHtml() throws STAFException {
        stafCmdFixture.responseMsg = "Test OutPut";
        //assertEquals("<pre>Test OutPut</pre>", stafCmdFixture.responseHtml());
    }

    @Ignore
    @Test
    public void testResponsePattern() throws STAFException {
        stafCmdFixture.pattern = "[0-9]*Test OutPut[0-9]*";
        stafCmdFixture.responseMsg = "123Test OutPut456Test Else OutPut";
        assertEquals("123Test OutPut456", stafCmdFixture.responsePattern());
    }

    @Ignore
    @Test
    public void testGetErrorMessage() throws STAFException {
        stafCmdFixture.returnCode = 17; //Description: File open error
        assertEquals("{<br>  Description: File open error<br>  Details    : " +
                "This indicates that there was an error opening the requested file.  " +
                "Some possible explanations are that the file/path does not exist, " +
                "contains invalid characters, or is locked.<br><br>}", stafCmdFixture.getErrorMessage());
    }
}