package com.apen.simple.net;

import com.apen.simple.bean.SimpleBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-11-15.
 * GitHub：https://github.com/cxydxpx
 */

public interface Api {

    @GET
    Call<SimpleBean> getSimle();

}
