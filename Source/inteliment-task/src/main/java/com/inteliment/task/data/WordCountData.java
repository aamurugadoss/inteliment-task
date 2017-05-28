package com.inteliment.task.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Murugadoss
 * This class is responsible for holding list of word count objects
 */
public class WordCountData {

	/**
	 * counts - List
	 */
	private List<Map<String, Long>> counts = new ArrayList<>();

	/**
	 * @return
	 */
	public List<Map<String, Long>> getCounts() {
		return counts;
	}

	/**
	 * @param counts
	 */
	public void setCounts(List<Map<String, Long>> counts) {
		this.counts = counts;
	}
}
