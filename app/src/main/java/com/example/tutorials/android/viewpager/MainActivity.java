package com.example.tutorials.android.viewpager;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ViewPager viewPager;

    private LinearLayout page1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MainPageAdapter());
        //set the default page
        viewPager.setCurrentItem(3);
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "page " + (position + 1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MainPageAdapter extends PagerAdapter {

        @Override public float getPageWidth(int position) {
            // Sets the width of the pages -> 1.0f = full width, 0.5f = half width
            return(0.6f);
        }

        @Override
        public int getCount() {
            // returns number of pages which can be shown
            return 8;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page = null;

            // use the debugger to see recycled views being recreated
            Log.d("recycle", position + " created");

            // possible to add different pages or 'views'
                page1 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.page, null);
                TextView page1tv = (TextView) page1.findViewById(R.id.tv1);
                page1tv.setText("page: " + position);
                page = page1;

            container.addView(page, 0);

            return page;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // used by the recycler to remove pages that are no longer visible
        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            // use the debugger to see recycled views being deleted
            Log.d("recycle", position + " deleted");
            container.removeView((View) view);
        }

    }

}