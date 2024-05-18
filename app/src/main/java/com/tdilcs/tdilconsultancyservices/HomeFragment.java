package com.tdilcs.tdilconsultancyservices;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    ////////////////////////////////////////////////////////Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage=2;
    private Timer timer;
    final private long DELAY_TIME=2000;
    final private long PERIOD_TIME=2000;
    /////////////////////////////////////////////////////////Strip Ad Layout
    private ImageView stripAdImage;
    private LinearLayout stripAdContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView=view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        List<CategoryModel> categoryModelList=new ArrayList<>();
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Electric"));
        categoryModelList.add(new CategoryModel("link", "Website"));
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Electric"));
        categoryModelList.add(new CategoryModel("link", "Website"));
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryAdapter=new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
//////////////////////////////////////////////////////////////////////////////////////////////        Banner Slider
        bannerSliderViewPager=view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList=new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.tdilcs_office, "#077AE4"));

        SliderAdapter sliderAdapter=new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setCurrentItem(currentPage);
        ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });
        /////////////////////////////////////////////////Strip Ad Layout
        stripAdImage=view.findViewById(R.id.strip_ad_image);
        stripAdContainer=view.findViewById(R.id.strip_ad_container);
        stripAdImage.setImageResource(R.drawable.trolley);
        stripAdContainer.setBackgroundColor(Color.parseColor("#077AE4"));
        return view;
    }
    //////// Banner Slider Infinitely Scroller is made with page looper
    private void pageLooper(){
        if(currentPage ==sliderModelList.size()-2){
            currentPage=2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);

        }
        if(currentPage==1){
            currentPage=sliderModelList.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);

        }
    }
    private void startBannerSlideShow(){
        Handler handler=new Handler();
        Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentPage>=sliderModelList.size()){
                    currentPage=1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME, PERIOD_TIME);
    }
    private void stopBannerSlideShow(){
        timer.cancel();
    }
}