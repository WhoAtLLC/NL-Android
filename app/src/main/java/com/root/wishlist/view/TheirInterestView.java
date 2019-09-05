package com.root.wishlist.view;


import com.root.wishlist.pojo.wantto.Comment;

import java.util.List;

public interface TheirInterestView {

    void message(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional);

    void businessInfo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments);

    void networkMessage(String connection);
}
