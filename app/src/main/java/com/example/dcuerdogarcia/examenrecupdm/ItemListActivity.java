package com.example.dcuerdogarcia.examenrecupdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class ItemListActivity extends AppCompatActivity
        implements ItemListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }

    }
//El sexto lo puse dos veces porque le di a enter sin querer
    //Sería el séptimo con los cambios aquí y en los config.xml
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

            //mando con el forResult el intent y el numero que recibirá el requestCode
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivityForResult(detailIntent, 1);
        }
        //Si la variable dualpanel es true saco la toast
        if (getResources().getBoolean(R.bool.dual_panel)==true) {
            // text.setText("Land");
            Toast.makeText(getBaseContext(), "Tumbado", Toast.LENGTH_SHORT).show();

        }
        //si es false esta en portrait y sacomos la toast
        else if (getResources().getBoolean(R.bool.dual_panel)==false){
            // text.setText("Portrait");
            Toast.makeText(getBaseContext(), "Portrait", Toast.LENGTH_SHORT).show();
        }
    }
    //Creo el metodo onActivityResult para poder recibir desde el intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //si recibe un requestCode del intent y un activity.resultOk tambien del intent muestra una toast Cerrada
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getBaseContext(), "Activity Cerrada", Toast.LENGTH_SHORT).show();

            }
        }
    }
}