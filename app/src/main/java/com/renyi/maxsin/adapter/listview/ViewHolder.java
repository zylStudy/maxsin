package com.renyi.maxsin.adapter.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.utils.DensityUtil;
import com.renyi.maxsin.utils.ShadowDrawable;

public class ViewHolder
{
    private SparseArray<View> mViews;
    protected int mPosition;
    private View mConvertView;
    private Context mContext;
    protected int mLayoutId;

    public ViewHolder(Context context, View itemView, ViewGroup parent, int position)
    {
        mContext = context;
        mConvertView = itemView;
        mPosition = position;
        mViews = new SparseArray<View>();
        mConvertView.setTag(this);
    }


    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null)
        {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            ViewHolder holder = new ViewHolder(context, itemView, parent, position);
            holder.mLayoutId = layoutId;
            return holder;
        } else
        {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    public int getLayoutId()
    {
        return mLayoutId;
    }

    public void updatePosition(int position)
    {
        mPosition = position;
    }

    public int getItemPosition()
    {
        return mPosition;
    }


    /****以下为辅助方法*****/

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    //添加阴影
    public  ViewHolder setShadowDrawable(int viewId) {
        View vw= getView(viewId);

        ShadowDrawable shadowDrawable = new ShadowDrawable();
        shadowDrawable.setColor(ContextCompat.getColor(mContext, R.color.colora))    //shadowcolor
                .setOffsetY(DensityUtil.dip2px(mContext, 5))    //阴影下偏移--offset of the shadow
                .setRadius(DensityUtil.dip2px(mContext, 8))     //四角半径--concern of the rectangle
                .setEdgeShadowWidth(DensityUtil.dip2px(mContext, 8))   //四周阴影半径-- the shadow of each edge of the rectangle
                .setFilterColor(0x56ffffff)                 //中间值，越大阴影越接近设置的值-- the slot to said how close to the shadowcolor
                .setTopMargin(DensityUtil.dip2px(mContext, 3))  //上间距--top margin
                .setParentHeight(DensityUtil.dip2px(mContext, 90))  //设置要依附的View的高度 -- the height of parent view
                .attach(vw)                                 //要在哪个View上面加阴影-- the shadow parent.※
                .build();
        return this;
    }
    //设置网络图片
    public ViewHolder setImageViewNetUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        //view.setImageResource(resId);
        Glide.with(mContext).load(imageUrl).asBitmap()
                .into(view);

        return this;
    }
    public  ViewHolder setImageCircular(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
        return this;
    }

    //设置图片圆角
    public ViewHolder setCornerRadiusImageViewNetUrl(int viewId, String imageUrl, final float a) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(a);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
        return this;
    }
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolder setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId)
    {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress)
    {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating)
    {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating, int max)
    {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId,
                                         View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


}
