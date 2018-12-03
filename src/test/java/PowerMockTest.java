import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Static.class})
public class PowerMockTest {
    
    @Test
    public void testMethodThatCallsStaticMethod() {
        // mock all the static methods in a class called "Static"
        PowerMockito.mockStatic(Static.class);
        // use Mockito to set up your expectation
        Mockito.when(Static.firstStaticMethod("abc")).thenReturn("def");
        Mockito.when(Static.secondStaticMethod()).thenReturn(123);

        // execute your test
        new ClassCallStaticMethodObj().execute();

        // Different from Mockito, always use PowerMockito.verifyStatic(Class) first
        // to start verifying behavior
        PowerMockito.verifyStatic(Static.class, Mockito.times(2));
        // IMPORTANT:  Call the static method you want to verify
        Static.firstStaticMethod("abc");


        // IMPORTANT: You need to call verifyStatic(Class) per method verification, 
        // so call verifyStatic(Class) again
        PowerMockito.verifyStatic(Static.class); // default times is once
        // Again call the static method which is being verified 
        Static.secondStaticMethod();

        // Again, remember to call verifyStatic(Class)
        PowerMockito.verifyStatic(Static.class, Mockito.never());
        // And again call the static method. 
        Static.thirdStaticMethod();
    }
}