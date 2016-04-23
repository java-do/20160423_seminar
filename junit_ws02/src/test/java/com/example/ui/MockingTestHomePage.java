package com.example.ui;

import com.example.WicketApplication;
import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MockingTestHomePage {

    private WicketTester tester;

    @Before
    public void setUp() throws Exception {
        ICalculationService mock = mock(ICalculationService.class);
        when(mock.square(anyDouble())).thenReturn(2.0);
        doThrow(new Exception()).when(mock).insertLog(any());

        tester = new WicketTester(new WicketApplication() {
            @Override
            protected Module getGuiceModule() {
                return binder -> {
                    binder.bind(ICalculationService.class).toInstance(mock);
                };
            }
        });
    }

    @Test
    public void ボタンを押してExceptionが発生するとfeedbackする() {
        tester.startPage(HomePage.class);

        FormTester sut = tester.newFormTester("postToCalculationPage");
        sut.setValue("value", "5.0");
        sut.submit();

        tester.assertErrorMessages("エラーが発生しました");
    }

}
