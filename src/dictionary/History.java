package dictionary;

import java.time.LocalDateTime;

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

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
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
