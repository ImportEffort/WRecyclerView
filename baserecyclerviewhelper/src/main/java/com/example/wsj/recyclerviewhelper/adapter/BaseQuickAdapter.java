package com.example.wsj.recyclerviewhelper.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.wsj.recyclerviewhelper.base.BaseViewHolder;
import com.example.wsj.recyclerviewhelper.listener.ItemViewDelegate;
import com.example.wsj.recyclerviewhelper.listener.LoadMoreListener;
import com.example.wsj.recyclerviewhelper.listener.OnItemClickListener;
import com.example.wsj.recyclerviewhelper.load.CustomLoadMoreView;
import com.example.wsj.recyclerviewhelper.load.LoadMoreDelegate;
import com.example.wsj.recyclerviewhelper.load.LoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshijia on 2017/6/23 下午3:26.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
        implements ItemViewDelegate<T> {

    private final static int HEADER = 0x100001;
    private final static int FOOTER = 0x100004;
    private final static int LOAD_MORE_FOOTER = 0x100005;
    private final static int NORMAL = 0x100003;
    private static final int EMPTY = 0x100002;
    private static final int DEFAULT_TYPE = 0;//RecyclerView.getItemType所默认的返回值

    private int mLayoutId;

    private boolean mEnableHeaderClick = false;

    public void setHeaderItemClickEnable(boolean enableHeadClick) {
        this.mEnableHeaderClick = enableHeadClick;
    }

    private boolean mEnableFooterClick = false;

    public void setFooterItemClickEnable(boolean enableFooterClick) {
        this.mEnableFooterClick = enableFooterClick;
    }

    protected List<T> mDates;
    private LayoutInflater mInflater;
    protected Context mContext;

    private LoadMoreView mLoadMoreView = new CustomLoadMoreView();
    private LoadMoreDelegate mLoadMoreDelegate;

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        mLoadMoreView = loadMoreView;
    }

    public BaseQuickAdapter(@LayoutRes int layoutId, @Nullable List<T> dates) {
        if (layoutId != 0) {
            mLayoutId = layoutId;
        }
        mDates = dates == null ? new ArrayList<T>() : dates;
    }

    public BaseQuickAdapter(@Nullable List<T> dates) {
        this(0, dates);
    }

    public BaseQuickAdapter(@LayoutRes int layoutId) {
        this(layoutId, null);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mInflater = LayoutInflater.from(mContext);
        BaseViewHolder baseViewHolder;
        if (viewType == HEADER) {
            baseViewHolder = new BaseViewHolder(headerLayout);
            if (mEnableHeaderClick) bindViewClickListener(baseViewHolder);
        } else if (viewType == EMPTY) {
            baseViewHolder = new BaseViewHolder(emptyLayout);
        } else if (viewType == FOOTER) {
            baseViewHolder = new BaseViewHolder(footerLayout);
            if (mEnableFooterClick) bindViewClickListener(baseViewHolder);
        } else if (viewType == LOAD_MORE_FOOTER) {
            baseViewHolder = getLoadViewHolder(parent);
        } else {
            baseViewHolder = onCreateDefViewHolder(parent, viewType);
            bindViewClickListener(baseViewHolder);
        }
        return baseViewHolder;
    }

    private BaseViewHolder getLoadViewHolder(ViewGroup parent) {
        View loadMoreView = mInflater.inflate(mLoadMoreView.getLayoutId(), parent, false);
        return new BaseViewHolder(loadMoreView);
    }

    //用来生成正常条目的ViewHolder 子类复写这个方法来实现
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder.getItemViewType() == NORMAL || holder.getItemViewType() == DEFAULT_TYPE) {
            position = position - getHeaderLayoutCount();
            convert(holder, mDates.get(position), position);
        } else if (holder.getItemViewType() == LOAD_MORE_FOOTER) {
            if (mLoadMoreView == null) {
                mLoadMoreView = new CustomLoadMoreView();
            }
            mLoadMoreView.convert(holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getEmptyViewCount() == 1) {
            return getItemTypeWithEmpty(position);
        }
        if (afterEmptyViewEnableLoad) {
            setEnableNotFullScreenLoadMore(true);
        }
        int headerLayoutCount = getHeaderLayoutCount();//此方法返回 0，1
        if (position < headerLayoutCount) {
            return HEADER;
        } else {
            //计算条目开始位置偏差值 比如 有header position 从1开始的 adjPosition 为去除header后的位置
            int adjPosition = position - headerLayoutCount;
            int adapterCount = mDates.size();//计算条目个数
            if (adjPosition < adapterCount) {
                return getNormalItemType(adjPosition);
            } else {
                adjPosition = adjPosition - adapterCount;
                int numFooters = getFooterLayoutCount();
                if (adjPosition < numFooters) {
                    return FOOTER;
                } else {
                    return LOAD_MORE_FOOTER;
                }
            }
        }
    }

    //有空布局的时候应该禁止上拉更多
    private int getItemTypeWithEmpty(int position) {
        boolean header = mEmptyWithHeader && getHeaderLayoutCount() != 0;
        switch (position) {
            case 0:
                if (header) {
                    return HEADER;
                } else {
                    return EMPTY;
                }
            case 1:
                if (header) {
                    return EMPTY;
                } else {
                    return FOOTER;
                }
            case 2:
                return FOOTER;
            default:
                return EMPTY;
        }
    }

    //如果是多类型条目布局则返回对应条目的Type
    public int getNormalItemType(int position) {
        return super.getItemViewType(position);
    }


    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View itemView = baseViewHolder.itemView;
        if (itemView == null) {
            return;
        }
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //baseViewHolder.getLayoutPosition()) 包含头布局的位置position
                    int realPosition = baseViewHolder.getLayoutPosition() - getHeaderLayoutCount();
                    mOnItemClickListener.OnItemClick(BaseQuickAdapter.this, v, realPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if (getEmptyViewCount() == 1) {
            count = 1;
            if (mEmptyWithHeader && getHeaderLayoutCount() != 0) {
                count++;
            }
            if (mEmptyWithFooter && getFooterLayoutCount() != 0) {
                count++;
            }
        } else {
            count = getHeaderLayoutCount() + mDates.size() + getFooterLayoutCount() + getLoadMoreFooterCount();
        }
        return count;
    }

    public void addItem(T item) {
        mDates.add(item);
        notifyItemInserted(mDates.size() + getHeaderLayoutCount());
    }

    public void addItems(List<T> newDates) {
        mDates.addAll(newDates);
        notifyItemRangeChanged(mDates.size() - newDates.size() + getHeaderLayoutCount(), newDates.size());
    }


    private LinearLayout headerLayout;
    private LinearLayout footerLayout;
    private FrameLayout emptyLayout;

    /*** 添加空布局部分*/
    public void setEmptyView(View emptyView) {
        if (emptyLayout == null) {
            emptyLayout = new FrameLayout(emptyView.getContext());
            final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            final ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if (lp != null) {
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }
            emptyLayout.setLayoutParams(layoutParams);
        }
        emptyLayout.removeAllViews();
        emptyLayout.addView(emptyView);
        if (getEmptyViewCount() == 1) {
            int position = 0;
            if (mEmptyWithHeader && getHeaderLayoutCount() != 0) {
                position++;
            }
            notifyItemInserted(position);
        }
    }

    private boolean mEmptyWithHeader = true;
    private boolean mEmptyWithFooter = false;

    public void setEmptyWithHeader(boolean emptyWithHeader) {
        mEmptyWithHeader = emptyWithHeader;
    }

    public void setEmptyWithFooter(boolean emptyWithFooter) {
        mEmptyWithFooter = emptyWithFooter;
    }

    public void setHeaderAndFooterWithEmpty(boolean emptyWithHeader, boolean emptyWithFooter) {
        mEmptyWithHeader = emptyWithHeader;
        mEmptyWithHeader = emptyWithFooter;
    }

    private SpanSizeLookup mSpanSizeLookup;

    public interface SpanSizeLookup {
        /**
         * position 除去header以后的位置
         */
        int getSpanSize(GridLayoutManager gridLayoutManager, int position);
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    //span size表示一个item的跨度，跨度了多少个span
    // 如果使用的是GridVIew 则默认头布局和尾部布局为沾满屏幕宽度 内部设置了gridManager外部设置就无效了
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemType = getItemViewType(position);
                    if (mSpanSizeLookup == null) {
                        return isFixedViewType(itemType) ? gridLayoutManager.getSpanCount() : 1;
                    } else {
                        return (isFixedViewType(itemType)) ? gridLayoutManager.getSpanCount() :
                                mSpanSizeLookup.getSpanSize(gridLayoutManager, position - getHeaderLayoutCount());
                    }
                }
            });
        }
    }


    protected boolean isFixedViewType(int type) {
        return type == EMPTY || type == HEADER || type == FOOTER || type == LOAD_MORE_FOOTER;
    }

    public int getEmptyViewCount() {
        if (emptyLayout == null || emptyLayout.getChildCount() == 0) {
            return 0;
        }
        //这里保证了 如果有新数据添加进来的时候 emptyView 消失
        if (mDates.size() != 0) {
            setEnableLoadMore(true);
            return 0;
        }
        return 1;
    }

    private int getFooterLayoutCount() {
        if (footerLayout == null || footerLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    private int getLoadMoreFooterCount() {
        if (mLoadMoreView == null) {// !loadMoreEnable
            return 0;
        }
        return 1;
    }


    /*** 添加头布局逻辑*/

    protected int getHeaderLayoutCount() {
        if (headerLayout == null || headerLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getHeaderCount() {
        if (headerLayout == null) {
            return 0;
        } else {
            return headerLayout.getChildCount();
        }
    }

    public void addHeadView(View view) {
        this.addHeadView(view, -1, LinearLayout.VERTICAL);
    }

    public void removeHeadView(View view) {
        if (getHeaderLayoutCount() == 0) return;
        if (headerLayout != null) {
            headerLayout.removeView(view);
            if (headerLayout.getChildCount() == 0) {
                notifyItemRemoved(0);
            }
        }
    }

    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() == 0) return;
        headerLayout.removeAllViews();
        int position = getHeaderViewPosition();
        if (position != -1) {
            notifyItemRemoved(position);
        }
    }

    private int getHeaderViewPosition() {
        if (getEmptyViewCount() == 1) {
            if (mEmptyWithHeader) {
                return 0;
            }
        } else {
            return 0;
        }
        return -1;
    }


    private int getFooterViewPosition() {
        //Return to footer view notify position
        if (getEmptyViewCount() == 1) {
            int position = 1;
            if (mEmptyWithHeader && getHeaderLayoutCount() != 0) {
                position++;
            }
            if (mEmptyWithFooter) {
                return position;
            }
        } else {
            return getHeaderLayoutCount() + mDates.size();
        }
        return -1;
    }

    public void addHeadView(View view, int position, int orientation) {
        if (headerLayout == null) {
            headerLayout = new LinearLayout(view.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                headerLayout.setOrientation(LinearLayout.VERTICAL);
                headerLayout.setLayoutParams(new RecyclerView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            } else {
                headerLayout.setOrientation(LinearLayout.HORIZONTAL);
                headerLayout.setLayoutParams(new RecyclerView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }

        int childCount = headerLayout.getChildCount();
        if (position < 0 || position > childCount) {
            position = childCount;
        }
        headerLayout.addView(view, position);
        if (headerLayout.getChildCount() == 1) {
            notifyItemInserted(0);
        }
    }

    /*** 添加底部布局逻辑*/
    public void addFooterView(View view) {
        this.addFooterView(view, -1, LinearLayout.VERTICAL);
    }

    public void addFooterView(View view, int position, int orientation) {
        if (footerLayout == null) {
            footerLayout = new LinearLayout(view.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                footerLayout.setOrientation(LinearLayout.VERTICAL);
                footerLayout.setLayoutParams(new RecyclerView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            } else {
                footerLayout.setOrientation(LinearLayout.HORIZONTAL);
                footerLayout.setLayoutParams(new RecyclerView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }

        int childCount = footerLayout.getChildCount();
        if (position < 0 || position > childCount) {
            position = childCount;
        }
        footerLayout.addView(view, position);
        if (footerLayout.getChildCount() == 1) {
            notifyItemInserted(getFooterViewPosition());
        }
    }

    public void removeFooterView(View view) {
        if (getFooterLayoutCount() == 0) return;
        if (footerLayout != null) {
            footerLayout.removeView(view);
            if (footerLayout.getChildCount() == 0) {
                notifyItemRemoved(getFooterViewPosition());
            }
        }
    }

    public int getFooterCount() {
        if (footerLayout == null) {
            return 0;
        } else {
            return footerLayout.getChildCount();
        }
    }


    public void setLoadMoreListener(LoadMoreListener loadMoreListener, RecyclerView recyclerView) {
        mLoadMoreDelegate = new LoadMoreDelegate(recyclerView, loadMoreListener, this, mLoadMoreView);
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        if (mLoadMoreDelegate != null) {
            mLoadMoreDelegate.setLoadMoreEnable(enableLoadMore);
        }
    }

    private void setLoading() {
        mLoadMoreDelegate.startLoading();
    }

    public void setLoadComplete() {
        mLoadMoreDelegate.setLoadComplete();
    }

    private boolean afterEmptyViewEnableLoad;

    /**
     * 此方法请在 setLoadMoreListener 之后调用，否则无效
     *
     * @param enable 是否开启条目不满全屏的上拉加载事件
     */
    public void setEnableNotFullScreenLoadMore(boolean enable) {
        if (getEmptyViewCount() == 1) {
            afterEmptyViewEnableLoad = true;
            return;
        }
        if (mLoadMoreDelegate != null) {
            mLoadMoreDelegate.setEnableNoFullScreenLoadMore(enable);
        }
    }

    /**
     * 没有最新数据了
     */
    public void setLoadEnd() {
        mLoadMoreDelegate.setLoadEnd();
    }

    public void setLoadError() {
        mLoadMoreDelegate.setLoadError();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public void setNewDates(List<T> dates) {
        mDates.clear();
        mDates.addAll(dates);
        notifyDataSetChanged();
    }
}
