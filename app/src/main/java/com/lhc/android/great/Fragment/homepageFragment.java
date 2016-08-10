package com.lhc.android.great.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lhc.android.great.Adapter.ImageViewPagerAdapter;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/7.
 */
public class homepageFragment extends Fragment {

    private ViewPager mVpAdverImages;
    private LinearLayout mLlHpPrint,mLlHpStore,mLlHpSecondBook;
    private ImageView mIvDotOne,mIvDotTwo,mIvDotThree,mIvDotFour,mIvDotFive;
    private int mImagesCounts;
    private int currentItem=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_page,null);
        mVpAdverImages = (ViewPager) view.findViewById(R.id.vp_advertise_image);
        mLlHpPrint=(LinearLayout)view.findViewById(R.id.ll_homepage_print);
        mLlHpStore=(LinearLayout)view.findViewById(R.id.ll_homepage_shopping_store);
        mLlHpSecondBook=(LinearLayout)view.findViewById(R.id.ll_homepage_sh_bstore);

        mLlHpPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToPrintPage(getActivity());
            }
        });

        mLlHpStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToShopStorePage(getActivity());
            }
        });

        mLlHpSecondBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateUtil.navigateToShopSecondBookStorePage(getActivity());
            }
        });

        mIvDotFive=(ImageView)view.findViewById(R.id.iv_dot_five);
        mIvDotOne=(ImageView)view.findViewById(R.id.iv_dot_one);
        mIvDotTwo=(ImageView)view.findViewById(R.id.iv_dot_two);
        mIvDotFour=(ImageView)view.findViewById(R.id.iv_dot_four);
        mIvDotThree=(ImageView)view.findViewById(R.id.iv_dot_three);


        Context context=getActivity();
        List<ImageView> list=new ArrayList<>();
        ImageView ivOne=new ImageView(context);
        ivOne.setImageResource(R.drawable.adver_one);
        list.add(ivOne);
        ImageView ivTwo=new ImageView(context);
        ivTwo.setImageResource(R.drawable.adver_two);
        list.add(ivTwo);
        ImageView ivThree=new ImageView(context);
        ivThree.setImageResource(R.drawable.adver_three);
        list.add(ivThree);
        ImageView ivFour=new ImageView(context);
        ivFour.setImageResource(R.drawable.adver_four);
        list.add(ivFour);
        ImageView ivFive=new ImageView(context);
        ivFive.setImageResource(R.drawable.adver_five);
        list.add(ivFive);
        mImagesCounts =list.size();
        ImageViewPagerAdapter adapter=new ImageViewPagerAdapter(getActivity(),list);
        if(adapter!=null) {
            mVpAdverImages.setAdapter(adapter);
            mVpAdverImages.setCurrentItem(0);
        }
        mIvDotOne.setImageResource(R.drawable.viewpage_dot_red);
        mVpAdverImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIvDotOne.setImageResource(R.drawable.viewpage_dot_white);
                mIvDotTwo.setImageResource(R.drawable.viewpage_dot_white);
                mIvDotThree.setImageResource(R.drawable.viewpage_dot_white);
                mIvDotFour.setImageResource(R.drawable.viewpage_dot_white);
                mIvDotFive.setImageResource(R.drawable.viewpage_dot_white);
                switch (position){
                    case 0:
                        mIvDotOne.setImageResource(R.drawable.viewpage_dot_red);
                        currentItem=0;
                        break;
                    case 1:
                        mIvDotTwo.setImageResource(R.drawable.viewpage_dot_red);
                        currentItem=1;
                        break;
                    case 2:
                        mIvDotThree.setImageResource(R.drawable.viewpage_dot_red);
                        currentItem=2;
                        break;
                    case 3:
                        mIvDotFour.setImageResource(R.drawable.viewpage_dot_red);
                        currentItem=3;
                        break;
                    case 4:
                        mIvDotFive.setImageResource(R.drawable.viewpage_dot_red);
                        currentItem=4;
                    default:break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 10, 10, TimeUnit.SECONDS);
    }

    public class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem=(currentItem+1)% mImagesCounts;
            handler.obtainMessage().sendToTarget();
        }
    }

    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            mVpAdverImages.setCurrentItem(currentItem);
        }
    };
}
