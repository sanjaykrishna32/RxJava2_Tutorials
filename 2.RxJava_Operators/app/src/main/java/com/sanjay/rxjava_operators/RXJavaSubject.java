package com.sanjay.rxjava_operators;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import android.os.Bundle;
import android.util.Log;

public class RXJavaSubject extends AppCompatActivity {
    String TAG = "RX_SUBJECT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_x_java_subject);
//        asyncDemo1();
//        asyncDemo2();
//        behaviorDemo1();
//        behaviorDemo2();
//        publishDemo1();
//        publishDemo2();
//        replayDemo1();
        replayDemo2();
    }

    void asyncDemo1(){
        //create a async subject
        Observable<String> observable = Observable.just("Android","JAVA","JSON","DART");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        //using async subject as observer
        observable.subscribe(asyncSubject);
        //using async subject as observable
        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());
    }


    void asyncDemo2(){
        //create a async subject
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        //using async subject as observable
        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOTLIN");
        asyncSubject.onNext("ANDROID");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("FLUTTER");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());
    }

    void behaviorDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());


    }

    void behaviorDemo2(){
        //create a behavior subject
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        //using behavior subject as observable
        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");
        behaviorSubject.onNext("ANDROID");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("FLUTTER");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());
    }

    void publishDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject = PublishSubject.create();

        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());


    }

    void publishDemo2(){
        //create a behavior subject
        PublishSubject<String> publishSubject = PublishSubject.create();
        //using behavior subject as observable
        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("JAVA");
        publishSubject.onNext("KOTLIN");
        publishSubject.onNext("ANDROID");
        publishSubject.onNext("XML");

        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("FLUTTER");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());
    }

    void replayDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());


    }

    void replayDemo2(){
        //create a behavior subject
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        //using behavior subject as observable
        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext("JAVA");
        replaySubject.onNext("KOTLIN");
        replaySubject.onNext("ANDROID");
        replaySubject.onNext("XML");

        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("FLUTTER");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());
    }

    /**
     * Function to return oberver
     * @return Observer<String>
     */
    private Observer<String> getFirstObserver(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"First observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"First observer onNext "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"First observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"First observer onComplete");
            }
        };
        return observer;
    }

    /**
     * Function to return oberver
     * @return Observer<String>
     */
    private Observer<String> getSecondObserver(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"Second observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"Second observer onNext "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"Second observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"Second observer onComplete");
            }
        };
        return observer;
    }

    /**
     * Function to return oberver
     * @return Observer<String>
     */
    private Observer<String> getThirdObserver(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG,"Third observer onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG,"Third observer onNext "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"Third observer onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"Third observer onComplete");
            }
        };
        return observer;
    }
}