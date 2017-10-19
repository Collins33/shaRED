package com.example.root.shared;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements  View.OnClickListener{
    private ArrayList<Category> categories;
    private RecyclerView recyclerView;
    private DashboardItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_activity);


        recyclerView = (RecyclerView) findViewById(R.id.dashboardRecycler);
        mCardView=(CardView) findViewById(R.id.card_view);

        categories = new ArrayList<>();
        adapter = new DashboardItemAdapter(this, categories);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mCardView.setOnClickListener(this);


        categories = new ArrayList<Category>();
        for(int i=0;i<= 3;i++) {
           categories.add(new Category(MyData.categorys[i],MyData.images[i]));
        }

        adapter = new DashboardItemAdapter(getApplicationContext(),categories);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View view){
        if(view == mCardView){
            getNewIntent();
        }
    }
    public void getNewIntent(){
        categories = new ArrayList<Category>();
        for(int i=0;i<= 3;i++) {
            categories.add(new Category(MyData.categorys[i],MyData.images[i]));

        }

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
