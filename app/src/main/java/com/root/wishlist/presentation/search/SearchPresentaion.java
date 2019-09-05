package com.root.wishlist.presentation.search;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.SearchModel;
import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.view.SearchView;

import java.util.List;

public class SearchPresentaion implements SearchInterface, SearchModel.FindSearchData {
    SearchView searchView;
    SearchModel searchModel;
    SharedDatabase sharedDatabase;
    Context context;

    public SearchPresentaion(Context context, SearchView searchView) {
        this.context = context;
        this.searchView = searchView;
        sharedDatabase = new SharedDatabase(context);
        searchModel = new SearchModel();

    }

    @Override
    public void findData(String search) {
        //searchModel.sendSearchQuery(context, "token " + sharedDatabase.getToken(), search, this);
        searchModel.sendSearchQuery(context, "token 94ed2f838d622f8c3e98c376fa447e6fcb10c55d", search, this);
    }

    @Override
    public void getSearchResult(List<SearchResult> searchResults) {
        if (searchView != null) {
            searchView.getSearchData(searchResults);
        }

    }

    @Override
    public void getmessage(String message) {

        if (searchView != null) {
            searchView.getmessage(message);
        }
    }
}
