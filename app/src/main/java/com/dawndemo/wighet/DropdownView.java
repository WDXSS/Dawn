package com.dawndemo.wighet;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.dawndemo.Bean.TypeBean;
import com.dawndemo.R;

import java.util.List;


/**
 *  直播列表下的分类
 * Created by zc on 2017/4/1.
 */

public class DropdownView extends LinearLayout implements BossCustomButton.OnToggleListener {
    public static final int ID_DROPDOWN_ONE = 1;
    private Context con;
    private SparseArray<BossCustomButton> buttonList = new SparseArray<>();

    public DropdownView(Context context) {
        this(context,null);
    }

    public DropdownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DropdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.con = context;
        initView();
    }
    private void initView(){
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setTitle(String text, int id){
        if(buttonList.get(id) != null){
            buttonList.get(id).setText(text);
        }else{
            BossCustomButton button = new BossCustomButton(con);
            button.setId(id);
            button.setText(text);
            button.setOnToggleListener(this);
            buttonList.append(id,button);
            addView(button);
        }
    }
    public void setDate(List<TypeBean> list){
        this.mMenuList = list;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case ID_DROPDOWN_ONE:
                Toast.makeText(con,"VIEW_ID = " + v.getId(),Toast.LENGTH_SHORT).show();
                showPow(v);
                break;
        }
    }
    /**
     * 当前分类下typeId
     */
    private long mCurrentTypeId = 0;
    /**
     * 用于构建分类筛选列表的list，在修改分类后，复制了mTypes
     */
    public List<TypeBean> mMenuList;
    private PopupWindow mPopWindow;

    private LinearLayout layout;
    private PopMultistageMenu menu;
    private View v;

    private void showPow(View view){
        if (layout == null) {
            layout = new LinearLayout(con);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);
        }
        if (menu == null) {
            menu = new PopMultistageMenu(con);
            LinearLayout.LayoutParams menuParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            menuParams.weight = 1;
            menu.setLayoutParams(menuParams);
            menu.setMaxChecked(1);
            layout.addView(menu);
        }
        //
        //如果“其他”下没有直播，就删除掉，不显示其他   没有加
        //
        menu.setData(mMenuList, new PopMultistageMenu.OnMenuSelectListener() {
            @Override
            public void onMenuSelect(TypeBean model) {
                if (mCurrentTypeId != 0 && model.id == mCurrentTypeId) {
                    mPopWindow.dismiss();
                } else {
                    mPopWindow.dismiss();
                    mListener.onSelected(model);
                }
            }
        });
        if (v == null) {
            v = new View(con);
            v.setBackgroundColor(0x2f000000);
            LinearLayout.LayoutParams bgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            bgParams.weight = 1;
            v.setLayoutParams(bgParams);
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                }
            });
            layout.addView(v);
        }
        if (mPopWindow == null) {
            mPopWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mPopWindow.setFocusable(true);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopWindow.setOutsideTouchable(true);
            mPopWindow.setTouchable(true);
            mPopWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        }
        mPopWindow.setContentView(layout);
        mPopWindow.showAsDropDown(view);
    }





    private OnSelectItemListener mListener;
    public void setonSelectItemLinstener(OnSelectItemListener listener){
        this.mListener = listener;
    }
    public interface OnSelectItemListener{
//        void onSelected(int position , TypeBean bean);
        void onSelected(TypeBean bean);
    }
}
