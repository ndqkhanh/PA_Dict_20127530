package dictionary;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 11:08 PM
 * Description: ...
 */
public class History {
    // Searched text
    private String historySearchText;
    // 0: search by slang, 1: search by definition
    private boolean searchType;

    public History() {
        historySearchText = "";
        searchType = false;
    }

    public History(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
    }

    public String getHistorySearchText() {
        return historySearchText;
    }

    public boolean getSearchType() {
        return searchType;
    }

    public void setHistory(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
    }
}
