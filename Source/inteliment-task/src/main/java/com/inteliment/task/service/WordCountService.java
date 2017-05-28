package com.inteliment.task.service;

import java.util.List;

import com.inteliment.task.data.CSVFileData;
import com.inteliment.task.data.WordCountData;

/**
 * @author Murugadoss
 *
 */

public interface WordCountService {
	
	public WordCountData wordCount(List<String> searchText);

	public List<CSVFileData> prepareCsvFileDataList(int topCount);
}
