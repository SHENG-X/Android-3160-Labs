package com.example.musfiqrahman.waitlist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int LOADER_GUEST = 1;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    GuestListAdapter mAdapter;
    Button btn;
    EditText guestNameInput, guestNumInput;
    GuestDB guestDB;
    GuestViewModel guestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.all_guest_list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        btn = (Button) findViewById(R.id.add_to_waitlist_button);
        guestViewModel = ViewModelProviders.of(this).get(GuestViewModel.class);
        mAdapter = new GuestListAdapter();
        recyclerView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(LOADER_GUEST, null, mLoaderCallbacks);
        Log.d("erro","cp1");
        //guestDB=GuestDB.getDB(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int guestNo = 0;
                guestNameInput = (EditText) findViewById(R.id.guest_name);
                guestNumInput = (EditText) findViewById(R.id.num_of_guest);
                String name = String.valueOf(guestNameInput.getText());
                String guestNum = String.valueOf(guestNumInput.getText());
                guestNameInput.setText("");
                guestNumInput.setText("");
                if (name.length() <= 0 || guestNum.length() <= 0) {
                    Toast.makeText(view.getContext(), "Please Enter Guest Name and Guest Number.", Toast.LENGTH_LONG).show();
                } else {
                    guestNo = Integer.parseInt(guestNum);
                    if (guestNo > 0) {
                        GuestInfo guestInfo = new GuestInfo(name, guestNo);
                        guestViewModel.setGuestInfoMutableLiveData(guestInfo);
                        getSupportLoaderManager().restartLoader(LOADER_GUEST, null, mLoaderCallbacks);
                        Toast.makeText(view.getContext(), "Add success!", Toast.LENGTH_LONG).show();
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    } else {
                        Toast.makeText(view.getContext(), "Guest Number Cannot Be Zero.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        Log.d("erro","cp2");


    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.d("onc","231");
            switch (id) {
                case LOADER_GUEST:
                    return new CursorLoader(getApplicationContext(),
                            GuestContract.GuestEntity.CONTENT_URI,
                            new String[]{GuestContract.GuestEntity.COLUMN_GUEST_NAME, GuestContract.GuestEntity.COLUMN_PARTY_SIZE},
                            null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.d("onc","222");
            switch (loader.getId()) {
                case LOADER_GUEST:
                    mAdapter.setGuest(data);
                    break;
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_GUEST:
                    mAdapter.setGuest(null);
                    break;
            }
        }
    };


}
