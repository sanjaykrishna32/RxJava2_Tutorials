package com.sanjay.rxjava_operators;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MapOperator extends AppCompatActivity {

    Observable<Student> observable;
    DisposableObserver<Student> disposableObserver;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String TAG="MAP_OPERATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_operator);

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
        //making the observable visible to the observer using map operator(returns object)
//        compositeDisposable.add(
//                observable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<Student, Student>() {
//
//                            @Override
//                            public Student apply(Student student) throws Throwable {
//                                student.setName(student.getName().toUpperCase());
//                                return student;
//                            }
//                        })
//                        .subscribeWith(getObserver())
//        );
        //making the observable visible to the observer using flatmap operator(returns observable)
//        compositeDisposable.add(
//                observable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .flatMap(new Function<Student, Observable<Student>>() {
//                            @Override
//                            public Observable<Student> apply(Student student) throws Throwable {
//                                Student student1 = new Student("sa","sa@sa.com",23,"2021");
//                                Student student2 = new Student("san","san@sa.com",24,"2021");
//
//                                student.setName(student.getName().toUpperCase());
//                                return Observable.just(student,student1,student2);
//                            }
//                        })
//                        .subscribeWith(getObserver())
//        );

        //making the observable visible to the observer using concat operator(returns observable)
        compositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(new Function<Student, Observable<Student>>() {
                            @Override
                            public Observable<Student> apply(Student student) throws Throwable {
                                Student student1 = new Student("sa","sa@sa.com",23,"2021");
                                Student student2 = new Student("san","san@sa.com",24,"2021");

                                student.setName(student.getName().toUpperCase());
                                return Observable.just(student,student1,student2);
                            }
                        })
                        .subscribeWith(getObserver())
        );

    }
    private DisposableObserver getObserver()
    {
        disposableObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(@NonNull Student student) {
                Log.i(TAG, " onNext invoked with " + student.getName());
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