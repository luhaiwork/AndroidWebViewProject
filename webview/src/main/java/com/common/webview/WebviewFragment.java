package com.common.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.common.webview.databinding.FragmentWebviewBinding;
import com.common.webview.utils.Constants;
import com.common.webview.webviewclient.CommonWebviewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.synjones.base.loadsir.ErrorCallback;
import com.synjones.base.loadsir.LoadingCallback;

public class WebviewFragment extends Fragment implements WebviewCallback, OnRefreshListener {
    private final String TAG=WebviewFragment.class.getSimpleName();
    private String mUrl;
    private boolean mCanNativeRefresh;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private boolean mIsError=false;

    public static WebviewFragment newInstance(String url,boolean canNativeRefresh) {
        WebviewFragment fragment = new WebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH,canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            mCanNativeRefresh=bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        //需要开启javascript
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(mUrl);
        mLoadService= LoadSir.getDefault().register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        mBinding.webview.setWebViewClient(new CommonWebviewClient(this));
        mBinding.smartrefreshlayout.setEnableLoadMore(false);
        mBinding.smartrefreshlayout.setOnRefreshListener(this);
        mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        return mLoadService.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if(mLoadService!=null){
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if(mIsError){
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        }else{
            mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        }
        Log.d(TAG,"pageFinished");
        mBinding.smartrefreshlayout.finishRefresh();
        if(mLoadService!=null){
            if(mIsError){
                mLoadService.showCallback(ErrorCallback.class);
            }else{
                mLoadService.showSuccess();
            }
        }
        mIsError=false;
    }

    @Override
    public void onError() {
        Log.e(TAG,"onError");
        mIsError=true;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}
