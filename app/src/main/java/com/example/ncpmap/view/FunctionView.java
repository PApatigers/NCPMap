package com.example.ncpmap.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ncpmap.R;

public class FunctionView extends FrameLayout {

    private Context mContext;
    private TextView mFunctionName,mFunctionExra;
    private ImageView mFunctionIcon;

    public FunctionView(Context context) {
        this(context,null);
    }

    public FunctionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FunctionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
        typeFunctionView(attrs);
    }

    void init(){
        View.inflate(mContext,R.layout.view_function,this);
        mFunctionName = findViewById(R.id.function_name);
        mFunctionExra = findViewById(R.id.function_exra);
        mFunctionIcon = findViewById(R.id.function_icon);
    }

    /**
     * 获得自定义属性
     */
    void typeFunctionView(AttributeSet attrs){
        if (attrs == null)
            return;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.functionView);
        mFunctionName.setText(typedArray.getString(R.styleable.functionView_functionName));
        mFunctionExra.setText(typedArray.getString(R.styleable.functionView_functionExra));
        mFunctionIcon.setImageResource(typedArray.getResourceId(R.styleable.functionView_functionIcon,R.drawable.ic_broken_img));
    }
}
