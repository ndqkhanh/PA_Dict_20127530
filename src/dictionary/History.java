package dictionary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 11:08 PM
 * Description: ...
 */
public class History {
    // Searched text
    private String historySearchText;
    // false: search by slang, true: search by definition
    private boolean searchType;
    // Date & time search
    private LocalDateTime ldt;

    public History() {
        ldt = LocalDateTime.now();
        historySearchText = "";
        searchType = false;
    }

    public History(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
        ldt = LocalDateTime.now();
    }

    public History(String historySearchText, boolean searchType, LocalDateTime ldt) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
        this.ldt = ldt;
    }

    public String getHistorySearchText() {
        return historySearchText;
    }

    public boolean getSearchType() {
        return searchType;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public String getFormatLdt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(formatter);
    }

    public void setHistory(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
        ldt = LocalDateTime.now();
    }

    public boolean checkEqual(String historySearchText, boolean searchType) {
        return this.searchType == searchType && this.historySearchText.equals(historySearchText);
    }
}
