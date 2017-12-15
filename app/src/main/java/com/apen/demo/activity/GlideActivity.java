package com.apen.demo.activity;

import android.view.View;
import android.widget.ImageView;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-07.
 * GitHub：https://github.com/cxydxpx
 */

public class GlideActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView mImageView;

    private String IMG_URL = "http://www.maiwangtong.com/uploads/cert/20171107140154238.jpg";
    private String IMG_URL2 = "http://p1.pstatp.com/large/166200019850062839d3";

    @Override
    protected int layoutResId() {
        return R.layout.activity_glide;
    }

    public void loadImage(View v) {

        Glide
                /**
                 * 返回 RequestManager
                 * 此处的this 代表Glide的生命周期 返回一个RequestManager对象
                 *
                 * 多态
                 * with(activity)
                 * with(fragment)
                 * with(fragmentActivity)
                 * with(context)
                 *      如果参数为application 会调用get(getApplication) 生成一个RequestManager
                 *      如果参数不为application UI线程会生成一个隐藏的fragment 将activity的生命周期与fragment绑定 且同步
                 *                              如果不为UI线程 强制走get(getApplication)
                 */
                .with(this) // -- > RequestManager
                /**
                 *  RequestManager  返回 DrawableTypeRequest
                 *
                 * URL  load(url) --> fromString()
                 *                              .load(string)
                 *
                 *         DrawableTypeRequest <--  fromString() 会走loadGeneric()
                 *              `                                     loadGeneric() 调用 Glide.buildStreamModelLoader()生成 ModelLoader用于加载图片
                 *                                                                       Glide.buildFileDescriptorModelLoader()  生成 ModelLoader用于加载图片
                 *                                                      生成streamModelLoader
                 *
                 *         并且创建 DrawableTypeRequest(streamModelLoader)  进行返回
                 *                              DrawableTypeRequest 两个方法 asBitmap() 强制指定位静态图片  生成BitmapTypeRequest 对象
                 *                                                            asGif() 强制指定为动态图片 生成GifTypeRequest对象
                 *                               DrawableTypeRequest{
                 *                                     if (transcoder == null) {
                                                             transcoder = glide.buildTranscoder(resourceClass, transcodedClass);//用于对图片进行转码的
                                                      }

                                                     DataLoadProvider<ImageVideoWrapper, Z> dataLoadProvider = glide.buildDataProvider(ImageVideoWrapper.class,
                                                     resourceClass);  // 用于对图片进行编解码的

                                                     ImageVideoModelLoader<A> modelLoader = new ImageVideoModelLoader<A>(streamModelLoader,
                                                     fileDescriptorModelLoader);
                 *
                 *                               }
                 *
                 * DrawableTypeRequest  返回  DrawableRequestBuilder
                 *
                 *          DrawableTypeRequest.load(String)为DrawableTypeRequest的父类 DrawableRequestBuilder 的方法
                 *                        placeholder  return DrawableRequestBuilder
                 *                        error   return DrawableRequestBuilder
                 *                        load   return DrawableRequestBuilder
                 *                        centerCrop   return DrawableRequestBuilder
                 *                        diskCacheStrategy   return DrawableRequestBuilder
                 *                        into return Target<GlideDrawable>
                 */
                .load(IMG_URL) // -- > DrawableTypeRequest
                /**
                 *  定义只能静态显示
                 *  .asBitmap()
                 *  定义只能动态图片
                 *  .asGif()
                 *  此处为占位图
                 */
//                .placeholder(R.mipmap.loading)
//                错误时显示
//                .error(R.mipmap.baidumap_ico_poi_on)
//                取消占位图
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                制定图片大小
//                .override(200,200)
                /**
                 * into
                 *  DrawableRequestBuilder.into()
                 *         会走super.into() 父类 -- > GenericRequestBuilder的方法
                 *  一直走到glide 方法
                 *     Target buildImageViewTarget(){
                 *          ImageViewTargetFactory.buildTarget(imageview,class)
                 *                                                        class
                 *                                                          asBimap BitmapImageViewTarget
                 *                                                          其他  GlideDrawableImageViewTarget
                 *      }
                 *  返回一个GlideDrawableImageViewTarget(Target) Target对象则是用来最终展示图片用的
                 *
                 *  GenericRequestBuilder
                 *      into()
                 *      reture into(Target)
                 *
                 *  into(target)
                 *       Request request = buildRequest(target);  //Request是用来发出加载图片请求
                 *
                 *          生成Request 并且赋值
                 *
                 *       接着会走 GenericRequest.obtain(target...)好多好多参数 生成GenericRequest对象
                 *         GenericRequest.init() 做赋值
                 *
                 *        requestTracker.runRequest(request);
                 *
                 *          GenericRequest.begin()
                 *                 if (model == null) {//如果传进去URL为null
                 *                      onException(null); // 如果为null  会先使用占位符占位
                 *                      return;
                 *                  }
                 *
                 *                  这里会指定是不是调用override  都会去调用 onSizeReady
                 *                   if (Util.isValidDimensions(overrideWidth, overrideHeight)) {
                 *                           onSizeReady(overrideWidth, overrideHeight);
                 *                      } else {
                 *                         target.getSize(this);
                 *                    }
                 *          onSizeReady 真正复杂的地方
                 *
                 *            ModelLoader<A, T> modelLoader = loadProvider.getModelLoader();
                 *
                 *             engine 主要用来开启线程
                 *             loadStatus = engine.load(signature, width, height, dataFetcher, loadProvider, transformation, transcoder,
                 *                                      priority, isMemoryCacheable, diskCacheStrategy, this);
                 *
                 *              EngineRunnable
                 *                               resource = decode() -->  decodeFromSource() --> decodeJob.decodeFromSource()
                     *               DecodeJob        --> decodeFromSource

                                    Resource<T(GifBitmapWrapper)> decoded = decodeSource()

                                            --> fetcher(ImageVideoFetcher).loadData(priority)
                                                                    return new ImageVideoWrapper(inputStream, fileDescriptor);
                                            --> fetcher(HttpUrlFetcher).loadData()// 进行联网了
                                                                    retrurn inputStream
                                            decodeFromSourceData(imageVideoWrapper)
                                                      decoded = loadProvider.getSourceDecoder().decode(data, width, height);
                                                                loadProvider onSizeReady()方法中得到的FixedLoadProvider，
                                                                getSourceDecoder()得到的则是一个GifBitmapWrapperResourceDecoder对象，
                                                                也就是要调用这个对象的decode()方法来对图片进行解码
                                                                 // 从服务器获取流 读取
                                                            --> GifBitmapWrapperResourceDecoder.decodeStream()

                                                                // 静态图 走decodeBitmapWrapper()
                                                                         Resource<Bitmap> bitmapResource = bitmapDecoder.decode(toDecode, width, height);
                                                                         if (bitmapResource != null) {
                                                                                result = new GifBitmapWrapper(bitmapResource, null);
                                                                         }
                                                                         return result;

                                                                <-- --> decodeBitmapWrapper()

                                                                    bitmapDecoder(ImageVideoBitmapDecoder ).decode(toDecode, width, height);

                                                                        //对服务器返回的InputStream的读取，以及对图片的加载全都在这里了。
                                                                       <-- --> streamDecoder(StreamBitmapDecoder).decode(is, width, height);
                                                                            BitmapResource.obtain()
                                                                                return Resource</Bitmap>


                                return transformEncodeAndTranscode(decoded);
                                    -- > transcode(transformed);
                                    //GifBitmapWrapperDrawableTranscoder 核心代码 转码
                                    --> GifBitmapWrapperDrawableTranscoder.transcode(Resource<GifBitmapWrapper> toTranscode)
                                            //GlideBitmapDrawableTranscoder
                                                -->  public Resource<GlideBitmapDrawable> transcode(Resource<Bitmap> toTranscode) {
                                                            GlideBitmapDrawable drawable = new GlideBitmapDrawable(resources, toTranscode.get());
                                                              return new GlideBitmapDrawableResource(drawable, bitmapPool);
                                                     }

                        private void onLoadComplete(Resource resource) {
                            manager(EngineJob).onResourceReady(resource);
                        }

                 *
                 */
                .skipMemoryCache(true)
                .into(mImageView);

    }

}
