package com.inteliment.task.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inteliment.task.data.CSVFileData;
import com.inteliment.task.data.WordCountData;
import com.inteliment.task.util.Util;
@Service("wordCountService")
public class WordCountServiceImpl implements WordCountService {
	/**
	 * sample paragraph
	 */
	@Value("${sample.paragraph}")
	private String SAMPLE_PARAGRAPH;

	/**
	 * This API prepare number of occurrences of words which are sent by user.
	 * 
	 * @param searchText
	 * @return
	 * @throws IOException
	 */
	public WordCountData wordCount(List<String> searchText) {
		List<Map<String, Long>> countList = new ArrayList<>();

		for (String st : searchText) {
			Map<String, Long> wordMap = new HashMap<>();
			Long count = readTextFile().get(st);
			if (count == null) {
				count = 0L;
			}
			wordMap.put(st, count);
			countList.add(wordMap);
		}
		WordCountData count = new WordCountData();
		count.setCounts(countList);
		return count;
	}

	/**
	 * This API prepare top list of word with occurrences based on user request.
	 * 
	 * @param topCount
	 * @return
	 * @throws IOException
	 */
	public List<CSVFileData> prepareCsvFileDataList(int topCount) {
		//Sorting the map
		Map<String, Long> sortedMap = readTextFile().entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		List<CSVFileData> csvFileDataList = new ArrayList<>();
		if (topCount > sortedMap.size()) {
			topCount = sortedMap.size();
		}
		for (int i = 0; i < topCount; i++) {
			Long count = (Long) sortedMap.values().toArray()[i];
			String word = (String) sortedMap.keySet().toArray()[i];
			CSVFileData csvFileData = new CSVFileData(word + "|" + count);
			csvFileDataList.add(csvFileData);
		}
		return csvFileDataList;
	}

	/**
	 * This API read the text file and create map with number of occurrences of
	 * word.
	 * 
	 * @return Map
	 */
	private Map<String, Long> readTextFile() {
		Map<String, Long> countedMap;
		//This is used for unit testing.
		//String paragraph = String.join("", Util.SAMPLE_PARAGRAPH);
		String paragraph = String.join("", SAMPLE_PARAGRAPH);
		List<String> wordList = Arrays.asList(paragraph.split(("\\W+")));

		countedMap = wordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return countedMap;
	}
}
