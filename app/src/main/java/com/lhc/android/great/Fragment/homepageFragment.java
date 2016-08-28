package com.lhc.android.great.Fragment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lhc.android.great.Activity.MainActivity;
import com.lhc.android.great.Adapter.GoodsShowAdapter;
import com.lhc.android.great.Adapter.ImageViewPagerAdapter;
import com.lhc.android.great.Bmod.Goods;
import com.lhc.android.great.R;
import com.lhc.android.great.Utils.NavigateUtil;
import com.lhc.android.great.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/8/7.
 */
public class homepageFragment extends Fragment {

    private ViewPager mVpAdverImages;
    private LinearLayout mLlHpPrint,mLlHpStore,mLlHpSecondBook;
    private ImageView mIvDotOne,mIvDotTwo,mIvDotThree,mIvDotFour,mIvDotFive;
    private ListView mLvNews;
    private int mImagesCounts;
    private int currentItem=1;

    private List<Goods> mGoodsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_page,null);
        mVpAdverImages = (ViewPager) view.findViewById(R.id.vp_advertise_image);
        mLlHpPrint=(LinearLayout)view.findViewById(R.id.ll_homepage_print);
        mLlHpStore=(LinearLayout)view.findViewById(R.id.ll_homepage_shopping_store);
        mLlHpSecondBook=(LinearLayout)view.findViewById(R.id.ll_homepage_sh_bstore);

        mLvNews=(ListView)view.findViewById(R.id.lv_homepage_news);


        new Thread(new Runnable(){
            @Override
            public void run() {
                BmobQuery<Goods> query=new BmobQuery<>();
                query.addWhereEqualTo("property","goods");
                query.findObjects(new FindListener<Goods>() {
                    @Override
                    public void done(List<Goods> list, BmobException e) {
                        mGoodsList=list;
                        if(mGoodsList!=null) {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    GoodsShowAdapter adapter = new GoodsShowAdapter(getActivity(), mGoodsList);
                                    mLvNews.setAdapter(adapter);
                                }
                            });

                        }

                        if(e!=null){
                            ToastUtil.showToast(getActivity(),"出错啦");
                        }
                    }
                });
            }
        }).start();




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

//        mIvDotFive=(ImageView)view.findViewById(R.id.iv_dot_five);
        mIvDotOne=(ImageView)view.findViewById(R.id.iv_dot_one);
        mIvDotTwo=(ImageView)view.findViewById(R.id.iv_dot_two);
        mIvDotThree=(ImageView)view.findViewById(R.id.iv_dot_three);
//        mIvDotFour=(ImageView)view.findViewById(R.id.iv_dot_four);


        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Context context=getActivity();
        List<ImageView> list=new ArrayList<>();
        ImageView ivOne=new ImageView(context);
        ivOne.setLayoutParams(params);
        ivOne.setImageResource(R.drawable.one);
        ImageView ivTwo=new ImageView(context);
        ivTwo.setImageResource(R.drawable.two);
        ivTwo.setLayoutParams(params);
        ImageView ivThree=new ImageView(context);
        ivThree.setLayoutParams(params);
        ivThree.setImageResource(R.drawable.three);
        list.add(ivThree);
        list.add(ivTwo);
        list.add(ivOne);
        mImagesCounts =list.size();

        /*
        ImageView ivFour=new ImageView(context);
        ivFour.setImageResource(R.drawable.avatar_boy);
        list.add(ivFour);
        ImageView ivFive=new ImageView(context);
        ivFive.setImageResource(R.drawable.four);
        list.add(ivFive);
//        list.add(ivOne);
//        list.add(0,ivFive);*/


        ImageViewPagerAdapter pageradapter=new ImageViewPagerAdapter(getActivity(),list);
        if(pageradapter!=null) {
            mVpAdverImages.setAdapter(pageradapter);
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
//                mIvDotFour.setImageResource(R.drawable.viewpage_dot_white);
//                mIvDotFive.setImageResource(R.drawable.viewpage_dot_white);
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
