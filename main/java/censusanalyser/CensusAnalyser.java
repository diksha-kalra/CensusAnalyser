package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;

public class CensusAnalyser {

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		try (CSVParser parser = new CSVParser(new FileReader(csvFilePath), format);
				Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			int numOfEnteries = 0;
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
			numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEnteries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		try (CSVParser parser = new CSVParser(new FileReader(csvFilePath), format);
				Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaStateCodeCSV> censusStateCodeCSVIterator = csvToBean.iterator();
			int numOfEnteries = 0;
			Iterable<IndiaStateCodeCSV> csvIterable = () -> censusStateCodeCSVIterator;
			numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEnteries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
}
