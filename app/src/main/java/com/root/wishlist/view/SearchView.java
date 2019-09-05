package com.root.wishlist.view;


import com.root.wishlist.pojo.search.SearchResult;

import java.util.List;

public interface SearchView {
    void getSearchData(List<SearchResult> searchResults);
    void getmessage(String message);
}
