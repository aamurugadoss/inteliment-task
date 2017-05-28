
package com.inteliment.task.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.inteliment.task.data.CSVFileData;
import com.inteliment.task.data.SearchText;
import com.inteliment.task.data.WordCountData;
import com.inteliment.task.service.WordCountService;

/**
 * @author Murugadoss
 *
 */
@RestController
public class WordCountController {

	@Autowired
	private WordCountService wordCountService;

	/**
	 * @param searchText
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/counter-api/search", headers = "content-type=application/json")
	public WordCountData getCount(@RequestBody SearchText searchText) {
		WordCountData countData = wordCountService.wordCount(searchText.getSearchText());
		return countData;
	}

	/**
	 * @param response
	 * @param topResult
	 * @throws IOException
	 */
	@RequestMapping(value = "/counter-api/top/{input}", headers="Accept=text/csv")
	public void downloadCSV(HttpServletResponse response, @PathVariable("input") int topResult) throws IOException {

		String csvFileName = "topresult.csv";
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);
		// uses the Super CSV API to generate CSV data from the model data
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String header = "Result";
		// Call Word Count Service to fetch the top list based on user input.
		List<CSVFileData> csvFileDataList = wordCountService.prepareCsvFileDataList(topResult);

		for (CSVFileData data : csvFileDataList) {
			csvWriter.write(data, header);
		}
		csvWriter.close();
	}
}
