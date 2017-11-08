package com.taufic.vr_fantasy.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taufic.vr_fantasy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taufic on 11/8/2017.
 */

public class ProgressView extends LinearLayout {

    @BindView(R.id.loading_indicator)
    ViewGroup mLoadingContainer;
    @BindView(R.id.error_text)
    TextView mErrorLoadingMsg;
    @BindView(R.id.retry_button)
    Button mRetryButton;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_progressbar, null);
        ButterKnife.bind(this, view);

        addView(view);
    }

    public void startProgressBar() {
        setVisibility(VISIBLE);
        mLoadingContainer.setVisibility(VISIBLE);
        mErrorLoadingMsg.setVisibility(GONE);
        mRetryButton.setVisibility(GONE);
    }

    public void stopProgressBar() {
        setVisibility(GONE);
    }

    public void stopShowError(String errorMessage, boolean isRetry) {
        mLoadingContainer.clearAnimation();
        mLoadingContainer.setVisibility(GONE);
        mRetryButton.setVisibility(isRetry? VISIBLE : GONE);
        mErrorLoadingMsg.setVisibility(VISIBLE);
        mErrorLoadingMsg.setText(errorMessage);
    }

    public void setRefresh(OnClickListener onClickListener) {
        mRetryButton.setOnClickListener(onClickListener);
    }

}
