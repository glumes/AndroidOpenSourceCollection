package com.glumes.opensource.ui.fragment;

import com.glumes.opensource.base.BaseModel;
import com.glumes.opensource.base.BasePresenter;

/**
 * Created by zhaoying on 16/11/10.
 */

public class PicturePresenter  extends BasePresenter<PictureContract.PictureView,BaseModel>{



    public PicturePresenter() {

    }

    @Override
    public void attachView(PictureContract.PictureView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initModel() {
        mModel = new BaseModel();
    }


}
