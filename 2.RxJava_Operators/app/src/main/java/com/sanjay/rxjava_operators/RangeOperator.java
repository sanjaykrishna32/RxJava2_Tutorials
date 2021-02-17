package com.sanjay.rxjava_operators;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

public class RangeOperator extends AppCompatActivity {

    Observable<Integer> observable;
    DisposableObserver<Integer> disposableObserver;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String TAG="RANGE_OPERATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_operator);

        //observable for range operator
        observable = Observable.range(1,5);

        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );

    }
    private DisposableObserver getObserver()
    {
        disposableObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(@NonNull Integer s) {
                Log.i(TAG,"onNext Invoked "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG,"onError Invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete Invoked");
            }
        };
        return  disposableObserver;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}