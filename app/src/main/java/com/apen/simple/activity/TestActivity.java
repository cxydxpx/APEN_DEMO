package com.apen.simple.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.MotionEvent;
import android.view.View;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.bean.SimpleBean;
import com.apen.simple.net.Api;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-06.
 * GitHub：https://github.com/cxydxpx
 */

public class TestActivity extends BaseActivity {

    public int add(int a, int b) {
        return a + b;
    }

    public int reduce(int a, int b) {
        return a - b;
    }

//    LayoutInflater mInflater;

    @Override
    protected int layoutResId() {
        return R.layout.activity_test;
    }

    Handler mHandler = new Handler();

    Thread ml;

    HandlerThread mThread = new HandlerThread("HandlerThread");

    View mView;

    protected void retroftTest() {

        String url = "http://www.baidu.com";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api ma = retrofit.create(Api.class);
        Call<SimpleBean> simle = ma.getSimle();
        simle.enqueue(new Callback<SimpleBean>() {
            @Override
            public void onResponse(Call<SimpleBean> call, Response<SimpleBean> response) {

            }

            @Override
            public void onFailure(Call<SimpleBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void init() {
        super.init();


//        retroftTest();

//        getHttpUrl();

//        mInflater.inflate(R.layout.activity_constraint,null);

//        mThread.start();

//        Looper.prepare();

//        Looper.loop();

//        int[] arr = {1, 2, 3, 4, 5};
//        int[] arr2;
//        arr2 = arr;
//
//        List lm = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
//        // 使用iterator()方法要求容器返回一个Iterator
//
//        ListIterator<Integer> iterator1 = lm.listIterator();

//        while (iterator1.hasNext()) {
//            int next = iterator1.next();
//            int i = iterator1.nextIndex();
//
//            Logger.v(next + "  --  " + i);
//
//        }

//        Stack<String> ms = new Stack<>();
//        for (String str: "My dog has fleas".split(" ")) {
//            //把项压入堆栈顶部.
//            ms.push(str);
//            // 移除堆栈顶部的对象，并作为此函数的值返回该对象。
//            String pop = ms.pop();
//            // 查看堆栈顶部的对象，但不从堆栈中移除它。
//            String peek = ms.peek();
//            // 测试堆栈是否为空。
//            boolean empty = ms.empty();
//        }

//        Queue<Integer> mu = new LinkedList<Integer>();
//        for (int i = 0; i < 200; i++) {
//            // 将指定的元素插入此队列（如果立即可行且不会违反容量限制）
//            boolean add = mu.add(i);
//            //获取，但是不移除此队列的头。
//            Integer element = mu.element();
//            //将指定的元素插入此队列（如果立即可行且不会违反容量限制） 此方法通常要优于 add(E)，
//            boolean offer = mu.offer(i);
//            // 获取但不移除此队列的头；
//            Integer peek = mu.peek();
//            //获取并移除此队列的头
//            Integer poll = mu.poll();
//            // 获取并移除此队列的头
//            Integer remove = mu.remove();
//        }
//
//
//        View view;
//
//        IBinder mBin;
//
//        Map<String, Integer> mp = new HashMap<String, Integer>();
//
//        int[] ints = new int[5];
//        Arrays.fill(ints, 666);

//        List<String>[] ms = new ArrayList[2];
//        List<String[]> mm = new ArrayList<>();

//        Random random = new Random(47);
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < 1000; i++) {
//            int r = random.nextInt(20);
//            Integer fre = map.get(r);
//            map.put(r, fre == null ? 1 : fre + 1);
//        }
//        Logger.v(map + "");

        // 检测是否还有上一个元素
//        while (iterator1.hasPrevious()) {
//            // 获取上一个元素
//            int b = iterator1.previous();
//
////            Logger.v(b + "  --  ");
//        }
//
//        Iterator<Integer> iterator = lm.iterator();

        // 检测序列中是否还有元素
//        while (iterator.hasNext()) {
//            // 获得序列中下一个元素
//            int next = iterator.next();
//            Logger.v(next + "");
//        }
//

//        for (int i = 0; i < arr2.length; i++) {
//            arr2[i] = arr2[i] + 1;
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            Logger.v("arr[" + i + "] = " + arr[i]);
//            // 2,3,4,5,6
//        }

//        Random rand = new Random(47);
//        int[] arr3 = new int[rand.nextInt(20)];




    }

    private void getHttpUrl() {

        RequestBody fromBady = new FormBody.Builder()
                .add("id", "59")
                .build();


        Request requestBuilder = new Request.Builder()
                .url("http://www.baidu.com")
//                .method("GET", null)
                .post(fromBady)
                .build();

        OkHttpClient mOkHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient build = builder.build();

//        Call call = mOkHttpClient.newCall(requestBuilder);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String s = response.body().toString();
//                Logger.v(s);
//            }
//        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
