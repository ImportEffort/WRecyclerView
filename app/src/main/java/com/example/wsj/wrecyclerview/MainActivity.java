package com.example.wsj.wrecyclerview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.wsj.wrecyclerview.entites.MultilItemOne;
import com.example.wsj.wrecyclerview.entites.MultilItemThree;
import com.example.wsj.wrecyclerview.entites.MultilItemTitle;
import com.example.wsj.wrecyclerview.entites.MultilItemTwo;
import com.example.wsj.wrecyclerview.recyclerView.adapter.BaseQuickAdapter;
import com.example.wsj.wrecyclerview.recyclerView.base.BaseViewHolder;
import com.example.wsj.wrecyclerview.recyclerView.itemDecoration.NoHeadFooterLineDeoration;
import com.example.wsj.wrecyclerview.recyclerView.listener.LoadMoreListener;
import com.example.wsj.wrecyclerview.recyclerView.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.wsj.wrecyclerview.R.id.recyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BaseQuickAdapter<String> mAdapter;
    private RecyclerView mRecyclerView;
    private View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        Button addHeader = (Button) findViewById(R.id.add_header);
        Button addFooter = (Button) findViewById(R.id.add_footer);
        addHeader.setOnClickListener(this);
        addFooter.setOnClickListener(this);
        Button removeHeader = (Button) findViewById(R.id.re_header_header);
        Button removeFooter = (Button) findViewById(R.id.remove_footer);
        removeHeader.setOnClickListener(this);
        removeFooter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_header:
                View headerView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
                headerViews.add(headerView);
                addPostion++;
                mAdapter.addHeadView(headerView);
                break;
            case R.id.re_header_header:
                if (removePostion < addPostion) {
                    mAdapter.removeHeadView(headerViews.get(removePostion));
                    removePostion++;
                }
                mAdapter.addItem("添加");
                break;

            case R.id.add_footer:
                View footer = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, null);
                footerViews.add(footer);
                addPostion1++;
                mAdapter.addFooterView(footer);
                break;
            case R.id.remove_footer:
                if (removePostion1 < addPostion1) {
                    mAdapter.removeFooterView(footerViews.get(removePostion1));
                    removePostion1++;
                }
                break;
        }
    }

    private ArrayList<View> headerViews = new ArrayList<>();
    private ArrayList<View> footerViews = new ArrayList<>();
    private int addPostion = 0;
    private int addPostion1 = 0;
    private int removePostion = 0;
    private int removePostion1 = 0;
    private boolean firstTry = true;

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new NoHeadFooterLineDeoration(this, ContextCompat.getColor(this, R.color.bg_gray), 14, 0, 0));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
        final ArrayList<String> items = new ArrayList<>();

//        items.add("条目1");
//        items.add("条目2");
//        items.add("条目3");
//        items.add("条目4");
//
//        items.add("条目5");
//        items.add("条目6");
//        items.add("条目7");
//        items.add("条目8");
//
//        items.add("条目9");
//        items.add("条目10");

        mAdapter = new BaseQuickAdapter<String>(this, R.layout.simlpe_layout_item, items) {
            @Override
            public void convert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.textView, item);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        TestBRVAHAdapter brvahAdapter = new TestBRVAHAdapter<>(R.layout.simlpe_layout_item, items);
        brvahAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
        brvahAdapter.setHeaderAndEmpty(true);
        brvahAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.layout_empty_view, mRecyclerView, false));

        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override

            public void OnLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (firstTry) {
                            mAdapter.setLoadEnd();
                            firstTry = false;
                        } else {
                            Toast.makeText(MainActivity.this, "tianjia ", Toast.LENGTH_SHORT).show();
                            mAdapter.addItem("上拉加载出来的");
                            mAdapter.setLoadComplete();
                        }

                    }
                }, 2000);
            }
        }, mRecyclerView);
//        mRecyclerView.setItemAnimator(null);

//        mAdapter.setHeaderAndFooterWithEmpty(false, false);
        mAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
        mAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.layout_empty_view, (ViewGroup) mRecyclerView.getParent(), false));
//        mRecyclerView.setAdapter(brvahAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "pos   " + position, Toast.LENGTH_SHORT).show();
            }
        });
//        mRecyclerView.setAdapter(mAdapter);

        testMutliAdapter();
        testSectionAdapter();
        testMyMutilAdapter();
        testMySectionAdapter();
    }

    private void testMySectionAdapter() {
        List<SectionEntity> sectionEntities = new ArrayList<>();
        sectionEntities.add(new Type1Section(true, "类型1"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(true, "类型2"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));

        final TestMySectionAdapter testMySectionAdapter = new TestMySectionAdapter(sectionEntities);
        testMySectionAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void OnLoadMore() {

                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testMySectionAdapter.addItem(new Type1Section(false, "上拉出来的"));
                        testMySectionAdapter.setLoadEnd();
                    }
                }, 2000);
            }
        }, mRecyclerView);
        testMySectionAdapter.setEnableNotFullScreenLoadMore(true);
        testMySectionAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_footer_view, mRecyclerView, false));
        testMySectionAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
        mRecyclerView.setAdapter(testMySectionAdapter);
    }

    List<MultiItemEntity> itemEntities;

    private void testMyMutilAdapter() {
        itemEntities = new ArrayList<>();

        itemEntities.add(new MultilItemTitle());
        List<MultiItemEntity> one = new ArrayList<>();
        one.add(new MultilItemOne());
        one.add(new MultilItemOne());
        one.add(new MultilItemOne());
        itemEntities.addAll(one);

        itemEntities.add(new MultilItemTitle());
        List<MultiItemEntity> two = new ArrayList<>();
        two.add(new MultilItemTwo());
        two.add(new MultilItemTwo());
        two.add(new MultilItemTwo());
        itemEntities.addAll(two);

        itemEntities.add(new MultilItemTitle());
        List<MultiItemEntity> three = new ArrayList<>();
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        three.add(new MultilItemThree());
        itemEntities.addAll(three);


        final TestMyMultiAdapter testMyMultiAdapter = new TestMyMultiAdapter(itemEntities);
        testMyMultiAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void OnLoadMore() {

                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testMyMultiAdapter.addItem(new MultiItemEntity() {
                            @Override
                            public int getItemType() {
                                return 1;
                            }
                        });
                        testMyMultiAdapter.setLoadEnd();
                    }
                }, 2000);
            }
        }, mRecyclerView);
        testMyMultiAdapter.setEnableNotFullScreenLoadMore(true);
        testMyMultiAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_footer_view, mRecyclerView, false));
        testMyMultiAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_head_view, mRecyclerView, false));
//        mRecyclerView.setAdapter(testMyMultiAdapter);
    }


    private void testSectionAdapter() {
        List<SectionEntity> sectionEntities = new ArrayList<>();
        sectionEntities.add(new Type1Section(true, "类型1"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(true, "类型2"));
        sectionEntities.add(new Type1Section(false, "类型1item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));
        sectionEntities.add(new Type2Section(false, "类型2item"));

        TestBRVAHSection testBRVAHSection = new TestBRVAHSection(R.layout.simlpe_layout_item, R.layout.section_layout, sectionEntities);
//        mRecyclerView.setAdapter(testBRVAHSection);
    }

    private void testMutliAdapter() {
        List<MultiItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 1;
            }
        });
        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 2;
            }
        });

        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 1;
            }
        });

        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 3;
            }
        });

        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 3;
            }
        });

        itemEntities.add(new MultiItemEntity() {
            @Override
            public int getItemType() {
                return 30;
            }
        });


        TestBRVAHMutilType testBRVAHMutilType = new TestBRVAHMutilType(itemEntities);
//        mRecyclerView.setAdapter(testBRVAHMutilType);
    }
}
