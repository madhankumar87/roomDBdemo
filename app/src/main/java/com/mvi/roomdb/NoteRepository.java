package com.mvi.roomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        Executors.newSingleThreadExecutor().execute(()-> noteDao.insert(note));
    }

    public void update(Note note) {
        //new UpdateNoteAsyncTask(noteDao).execute(note);
        Executors.newSingleThreadExecutor().execute(()-> noteDao.update(note));
    }

    public void delete(Note note) {
        //new DeleteNoteAsyncTask(noteDao).execute(note);
        Executors.newSingleThreadExecutor().execute(()-> noteDao.delete(note));
    }

    public void deleteAllNotes() {
        //new DeleteAllNotesAsyncTask(noteDao).execute();
        Executors.newSingleThreadExecutor().execute(()-> noteDao.deleteAllNotes());
    }

    //This method (LiveData) automatically executes the asynchronous thread
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /*private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }*/
}