package com.example.inventory.responsibleMan;


import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.inventory.MainActivity;
import com.example.inventory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

   private Toolbar mTopToolbar;
    ImageView change_name;
    TextView name;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_profile, container, false);

        mTopToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTopToolbar);

        setHasOptionsMenu(true);

//        change_name = (ImageView) view.findViewById(R.id.rm_edit_name);
//        name = (TextView) view.findViewById(R.id.rm_profile_name);
//
//        change_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity().getApplicationContext());
//                View mView1 = getLayoutInflater().inflate(R.layout.change_name_dialog,null);
//                final EditText change_name_input = mView1.findViewById(R.id.change_name_input);
//                Button change_name_cancel = mView1.findViewById(R.id.change_name_cancel);
//                Button change_name_submit = mView1.findViewById(R.id.change_name_submit);
//                change_name_input.setText(name.getText().toString());
//
//                builder1.setView(mView1);
//                final AlertDialog dialog1 = builder1.create();
//
//
//
//                dialog1.show();
//
//
//                Toast.makeText(getActivity().getApplicationContext(), "smjh gya kya", Toast.LENGTH_SHORT).show();
//                change_name_submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        name.setText(change_name_input.getText().toString());
//                        dialog1.dismiss();
//                    }
//                });
//
//                change_name_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog1.dismiss();
//                    }
//                });
//            }
//        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent= new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}





