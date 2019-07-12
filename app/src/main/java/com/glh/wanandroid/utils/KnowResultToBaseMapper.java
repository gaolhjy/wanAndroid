// (c)2016 Flipboard Inc, All Rights Reserved.

package com.glh.wanandroid.utils;

import com.doyo.sdk.mvp.ResBaseBean;
import com.doyo.sdk.mvp.ResBaseListBean;
import com.glh.wanandroid.bean.KnoelwData2;
import com.glh.wanandroid.bean.KnowleDataList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class KnowResultToBaseMapper implements Function<ResBaseBean<List<KnowleDataList>>,
        ResBaseBean<ResBaseListBean<KnoelwData2>>> {

    private static KnowResultToBaseMapper INSTANCE = new KnowResultToBaseMapper();

    private KnowResultToBaseMapper() {
    }

    public static KnowResultToBaseMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ResBaseBean<ResBaseListBean<KnoelwData2>> apply(ResBaseBean<List<KnowleDataList>> data) throws Exception {

        List<ResBaseBean<ResBaseListBean<KnoelwData2>>> list = new ArrayList<>();

        ResBaseBean<ResBaseListBean<KnoelwData2>> newData = new ResBaseBean<>();
        ResBaseListBean<KnoelwData2> newData2 = new ResBaseListBean<>();

        List<KnowleDataList> data1 = data.getData();


        for (int i = 0; i < data1.size(); i++) {
            List<KnoelwData2> children = data1.get(i).children;
            newData2.datas = children;
            newData.setData(newData2);
            list.add(newData);
        }

        return newData;
    }
}
