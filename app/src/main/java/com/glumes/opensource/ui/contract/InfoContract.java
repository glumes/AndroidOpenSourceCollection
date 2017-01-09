package com.glumes.opensource.ui.contract;

import com.glumes.opensource.base.LoadView;
import com.glumes.opensource.mvp.IModel;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

/**
 * Created by zhaoying on 16/11/2.
 */

public interface InfoContract {



    interface PictureView extends LoadView<List<BaseResult>> {

    }

    interface Model extends IModel {

    }
}
