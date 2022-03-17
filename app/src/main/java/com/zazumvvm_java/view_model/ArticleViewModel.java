package com.zazumvvm_java.view_model;

import static com.zazumvvm_java.constants.AppConstant.API_KEY;
import static com.zazumvvm_java.constants.AppConstant.ARTICLE_QUERY;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.zazumvvm_java.repository.ArticleRepository;
import com.zazumvvm_java.response.ArticleResponse;
import com.zazumvvm_java.room.entities.Note;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    //Room
    private LiveData<List<Note>> mAllNotes;
    //Retrofit
    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;
    public ArticleViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
        //Room
        mAllNotes = articleRepository.getAllNotes();
        //Retrofit
        this.articleResponseLiveData = articleRepository.getMovieArticles(ARTICLE_QUERY, API_KEY);
    }

    //Retrofit
    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }

    //Room
    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void addNote(Note note) {
        articleRepository.addNote(note);
    }

}
