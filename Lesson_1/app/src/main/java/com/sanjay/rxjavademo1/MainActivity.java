package com.sanjay.rxjavademo1;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String greetings = "Hello World, this is my first rxjava program";
    String TAG = getClass().getName();
    Observable<String> observable;
    DisposableObserver<String> observer;
    DisposableObserver<String> disposableObserver;
    TextView greeting;
//    Disposable disposable;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greeting = findViewById(R.id.greatingTextView);

        //use just method to get an observer for the string object
        observable = Observable.just(greetings);

        //constructor the observer
        observer = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                //method is called when new item is emitted from observable
                Log.i(TAG,"onNext Invoked");
                //data from the observable can be received by onNext method of the observer
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //method is called when the error occurs
                Log.i(TAG,"onError Invoked");
            }

            @Override
            public void onComplete() {
                //method is called when the observable successfully emittes the data
                Log.i(TAG,"onComplete Invoked");
            }
        };

        //add observer to composite disposable pool
//        compositeDisposable.add(observer);
        //subscribe observable to the observer
//        observable.subscribe(observer);
        /*best coding way*/
        //with schedulers and inside compositeDisposable.add method as the below code will return disposable object
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));

        //construct disposable observer
        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                //method is called when new item is emitted from observable
                Log.i(TAG,"onNext Invoked");
                //data from the observable can be received by onNext method of the observer
                greeting.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //method is called when the error occurs
                Log.i(TAG,"onError Invoked");
            }

            @Override
            public void onComplete() {
                //method is called when the observable successfully emittes the data
                Log.i(TAG,"onComplete Invoked");
            }
        };
        //add observer to composite disposable pool
        compositeDisposable.add(disposableObserver);
        //subscribe observable to the observer
        observable.subscribe(disposableObserver);
    }
    //Override the onDestroy method of your activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //This method will always be invoked when the activity destroyed.
        // (when user move to another view).
//        disposable.dispose();
//        disposableObserver.dispose();
        //tp dispose all observers at once call
        compositeDisposable.clear();
    }
}