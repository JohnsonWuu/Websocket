package com.example.webscoket;

import com.controller.ExceptionRecordNumberGenerator;
import com.controller.MyEnum;
import com.controller.MyList;
import com.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@SpringBootTest
//@PrepareForTest({ExceptionRecordNumberGenerator.class})
@PrepareForTest(
		value = ExceptionRecordNumberGenerator.class,
		fullyQualifiedNames = {
				"com.controller.MyEnum$1",
				"com.controller.MyEnum$2"
})
class WebscoketApplicationTests {


	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@Test
	public void testMockFindListFromDB() {

		List<String> newListData = new ArrayList<>();
		newListData.add("TestNo0001");
		newListData.add("TestNo0002");

		MyList pumpkin = mock(MyList.class);
		when(pumpkin.getName()).thenReturn("www.pkslow.com");
//		final MyEnum one = mock(MyEnum.ONE.getClass());
//		when(one.myMethod()).thenReturn(10);
//
//		Whitebox.setInternalState(MyEnum.class, "ONE", one);


//		final ExceptionRecordNumberGenerator one = mock(ExceptionRecordNumberGenerator.RETURN_TO_TOTE_RECORD.getClass());
//		Mockito.when(one.getRecordNumbers(12))
//				.thenReturn(newListData);

//		ExceptionRecordNumberGenerator typeMock = PowerMockito.mock(ExceptionRecordNumberGenerator.class);
//		Whitebox.setInternalState(ExceptionRecordNumberGenerator.class, "RETURN_TO_TOTE_RECORD", typeMock);

	//	List<String> mockResult = userService.getNo(newListData.size());


	//	System.out.println("mock result: " + mockResult);

		// List<String> returnToteRecordNo = ExceptionRecordNumberGenerator.RETURN_TO_TOTE_RECORD.getRecordNumbers(3);
		// System.out.println("111[RETURN_TO_TOTE_RECORD]return tote record number:" + returnToteRecordNo);

//        List<String> lostRecordNo = ExceptionRecordNumberGenerator.LOST_RECORD.getRecordNumbers(8);
//        System.out.println("[LOST_RECORD],lost record number:" + lostRecordNo);
//
//        String foundRecordNo = ExceptionRecordNumberGenerator.FOUND_RECORD.getRecordNumber();
//        System.out.println("[FOUND_RECORD],found record number:" + foundRecordNo);
	}
}
