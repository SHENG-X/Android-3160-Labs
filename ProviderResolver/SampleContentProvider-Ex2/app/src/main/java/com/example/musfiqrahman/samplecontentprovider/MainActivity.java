package com.example.musfiqrahman.samplecontentprovider;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musfiqrahman.samplecontentprovider.data.DictionaryContract;
import com.example.musfiqrahman.samplecontentprovider.provider.SampleDictionaryProvider;

public class MainActivity extends AppCompatActivity {

    private static final int LOADER_DICTIONARY = 1;

    private DictionaryAdapter mDictionaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        mDictionaryAdapter = new DictionaryAdapter();
        list.setAdapter(mDictionaryAdapter);

        getSupportLoaderManager().initLoader(LOADER_DICTIONARY, null, mLoaderCallbacks);

        //getSupportLoaderManager().initLoader(LOADER_DICTIONARY, null, mLoaderCallbacks);
    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_DICTIONARY:
                            return new CursorLoader(getApplicationContext(),
                                    SampleDictionaryProvider.CONTENT_URI,
                                    new String[]{DictionaryContract.DictionaryEntity.COLUMN_WORD, DictionaryContract.DictionaryEntity.COLUMN_MEANING},
                                    null, null, null);
                        default:
                            throw new IllegalArgumentException();
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    switch (loader.getId()) {
                        case LOADER_DICTIONARY:
                            mDictionaryAdapter.setDictionary(data);
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_DICTIONARY:
                            mDictionaryAdapter.setDictionary(null);
                            break;
                    }
                }

            };

    private static class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder>{

        private Cursor mCursor;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (mCursor.moveToPosition(position)) {
                holder.mText1.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(DictionaryContract.DictionaryEntity.COLUMN_WORD)));
                holder.mText2.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow(DictionaryContract.DictionaryEntity.COLUMN_MEANING)));
            }
        }

        @Override
        public int getItemCount() {
            return mCursor == null ? 0 : mCursor.getCount();
        }

        void setDictionary(Cursor cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }


        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView mText1, mText2;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_2, parent, false));
                mText1 = itemView.findViewById(android.R.id.text1);
                mText2 = itemView.findViewById(android.R.id.text2);
            }

        }

    }
}
