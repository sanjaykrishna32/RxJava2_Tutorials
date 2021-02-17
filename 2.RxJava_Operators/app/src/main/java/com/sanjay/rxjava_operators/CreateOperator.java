package com.sanjay.rxjava_operators;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class CreateOperator extends AppCompatActivity {

    Observable<Student> observable;
    DisposableObserver<Student> disposableObserver;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String TAG="CREATE_OPERATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_operator);

        //createing an observable using create method
        observable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Student> emitter) throws Throwable {
                ArrayList<Student> studentArrayList = getStudents();
                for(Student student : studentArrayList){
                    emitter.onNext(student);
                }
                emitter.onComplete();
            }
        });
        //making the observable visible to the observer
        compositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );
    }
    private DisposableObserver getObserver()
    {
        disposableObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(@NonNull Student student) {
                Log.i(TAG, " onNext invoked with " + student.getEmail());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return  disposableObserver;
    }
    private ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student("Sanjay","s@.com",25,"2021");
        students.add(student1);
        Student student2 = new Student("krishna","s@.com",25,"2021");
        students.add(student2);
        Student student3 = new Student("abc","abc@.com",25,"2021");
        students.add(student3);
        Student student4 = new Student("cdf","cvb@.com",25,"2021");
        students.add(student4);
        Student student5 = new Student("qwer","qwer@.com",25,"2021");
        students.add(student5);
        return students;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}