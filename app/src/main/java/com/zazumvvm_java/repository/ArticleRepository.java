package com.zazumvvm_java.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zazumvvm_java.MyApp;
import com.zazumvvm_java.response.ArticleResponse;
import com.zazumvvm_java.retrofit.ApiRequest;
import com.zazumvvm_java.retrofit.RetrofitRequest;
import com.zazumvvm_java.room.NoteDatabase;
import com.zazumvvm_java.room.dao.NoteDao;
import com.zazumvvm_java.room.entities.Note;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = ArticleRepository.class.getSimpleName();
    //ROOM
    private LiveData<List<Note>> mAllNotes;

    @Inject
    NoteDao mNoteDao;



    //Retrofit
    private ApiRequest apiRequest;
    public ArticleRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        //For ROOM
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        mNoteDao = noteDatabase.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }
    //Retrofit
    public LiveData<ArticleResponse> getMovieArticles(String query, String key) {
        Log.i("Zazu","Testing-Rep");
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest
                .getMovieArticles(query, key)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.i("Zazu","Testing-Rep1");
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.setValue(null);
                        Log.i("Zazu","Testing-Rep2"+t.getMessage());
                    }
                });
        return data;
    }

    //Room
    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }
    public void addNote(Note note) {
        new AddNote().execute(note);
    }
    public class AddNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insertNote(notes[0]);
            return null;
        }
    }
}
