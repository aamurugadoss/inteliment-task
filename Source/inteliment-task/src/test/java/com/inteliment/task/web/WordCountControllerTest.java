package com.inteliment.task.web;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inteliment.task.data.CSVFileData;
import com.inteliment.task.data.WordCountData;
import com.inteliment.task.service.WordCountServiceImpl;

/**
 * @author Murugadoss
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCountControllerTest {

	@InjectMocks
	private WordCountServiceImpl WordCountService;
	
	/**
	 * testWordCount
	 */
	@Test
	public void testWordCount(){
		List<String> list = new ArrayList<String>();
		list.add("sit");
		list.add("feugiat");
		list.add("abc");
		WordCountData count = WordCountService.wordCount(list);
		assertEquals(3, count.getCounts().size());
	}
	
	/**
	 * testPrepareCsvFileDataList
	 */
	@Test
	public void testPrepareCsvFileDataList(){
		List<CSVFileData> list = WordCountService.prepareCsvFileDataList(10);
		assertEquals(1, list.size());
	}
}
