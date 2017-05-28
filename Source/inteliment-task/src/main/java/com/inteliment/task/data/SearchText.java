package com.inteliment.task.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murugadoss
 *
 */
public class SearchText {

	/**
	 * searchText - List
	 */
	private List<String> searchText = new ArrayList<>();

	/**
	 * @return
	 */
	public List<String> getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText
	 */
	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
}
