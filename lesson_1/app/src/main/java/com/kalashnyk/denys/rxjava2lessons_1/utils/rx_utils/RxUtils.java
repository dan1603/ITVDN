package com.kalashnyk.denys.rxjava2lessons_1.utils.rx_utils;

import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.kalashnyk.denys.rxjava2lessons_1.model.User;
import com.kalashnyk.denys.rxjava2lessons_1.model.UserEntity;
import com.kalashnyk.denys.rxjava2lessons_1.utils.validation_utils.IValidator;
import com.kalashnyk.denys.rxjava2lessons_1.utils.validation_utils.ValidatorImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import kotlin.Pair;

public class RxUtils implements IRxUtils {

    private IValidator mValidator;

    private Observable<String> mObservableJustOperator;
    private Observable<String> mObservableErrorOperator;
    private Observable<Boolean> mObservableMapOperator;
    private Observable<List<UserEntity>> mObservableFlatMapOperator;
    private Observable<List<UserEntity>> mObservableSwitchMapOperator;
    private Observable<List<User>> mObservableConcatMapOperator;
    private Observable<Boolean> mObservableCombineLatestOperator;

    public RxUtils() {
        mValidator = new ValidatorImpl();
    }

    @Override
    public Observable<String> introductionJustOperator(String string) {
        mObservableJustOperator = Observable.just(string);
        return mObservableJustOperator;
    }

    @Override
    public Observable<String> introductionErrorOperator(Throwable t) {
        mObservableErrorOperator = Observable.error(new Exception(t.getMessage()));
        return mObservableErrorOperator;
    }

    @Override
    public Observable<Boolean> introductionMapOperator(String stringForMap) {
        mObservableMapOperator = Observable.just(stringForMap)
                .map(string -> string.length() > 0);
        return mObservableMapOperator;
    }

    @Override
    public Observable<List<UserEntity>> introductionFlatMapOperator(List<User> list) {
        mObservableFlatMapOperator = Observable.just(list)
                .flatMap(items -> Observable.just(getSaveListIntoDatabase(items)));
        return mObservableFlatMapOperator;
    }

    @Override
    public Observable<List<UserEntity>> introductionSwitchMapOperator(EditText editText) {
        mObservableSwitchMapOperator = RxTextView.textChanges(editText)
                .switchMap(query -> Observable.just(getSortedListUser(query)));
        return mObservableSwitchMapOperator;
    }

    @Override
    public Observable<List<User>> introductionConcatMapOperator(List<User> list) {
        mObservableConcatMapOperator = Observable.fromIterable(list)
                .concatMap(item -> Observable.just(updateUserName("Person - ", item)))
                .toList()
                .toObservable();
        return mObservableConcatMapOperator;
    }


    @Override
    public void introductionSubscribeOperator() {

        mObservableJustOperator.subscribe(string -> {
            Log.d("CheckObservableInput", "string - " + string);
        });

        mObservableErrorOperator.subscribe(error -> {
            // onNext don't called
        }, throwable -> {
            Log.d("CheckObservableInput", "throwable - " + throwable.getMessage());
        });

        mObservableMapOperator.subscribe(is -> {
            Log.d("CheckObservableInput", "is - " + is);

        }, throwable -> {

        }, () -> {
            Log.d("CheckObservableInput", "complete");

        });

        mObservableFlatMapOperator.subscribe(items -> {

        }, throwable -> {

        }, () -> {

        });

        mObservableSwitchMapOperator.subscribe(items -> {

        }, throwable -> {

        }, () -> {

        });

        mObservableConcatMapOperator.subscribe(items -> {

        }, throwable -> {

        }, () -> {

        });
    }

    @Override
    public void introductionZipOperator(Observable<User> dataFromNetwork, Observable<UserEntity> dataFromDatabase) {
        Observable.zip(dataFromNetwork,
                dataFromDatabase,
                Pair::new)
                .subscribe(pair -> {

                }, throwable -> {

                }, () -> {

                });
    }

    @Override
    public void introductionMergeOperator(Observable<User> dataFromNetwork, Observable<User> dataFromDatabase) {
        Observable.merge(dataFromNetwork,
                dataFromDatabase)
                .subscribe(items -> {

                }, throwable -> {

                }, () -> {

                });
    }

    @Override
    public void introductionCombineLatestOperator(EditText editTextLogin, EditText editTextPassword) {
        Observable<Boolean> isLoginValid = RxTextView.textChanges(editTextLogin)
                .map(charSequence -> mValidator.isLoginValid(charSequence));

        Observable<Boolean> isPasswordValid = RxTextView.textChanges(editTextPassword)
                .map(charSequence -> mValidator.isPasswordValid(charSequence));

        mObservableCombineLatestOperator = Observable.combineLatest(isLoginValid, isPasswordValid, this::isDataValid);
        mObservableCombineLatestOperator.subscribe(is -> {

        });
    }

    @Override
    public void introductionSubscribeOnWithObserveOnOperators() {
        mObservableCombineLatestOperator
                .subscribeOn(Schedulers.io()) // create new thread inside Observable
                .observeOn(AndroidSchedulers.mainThread()) // result handling data of Observable will be returned into main thread
                .subscribe(is -> {

                });
    }

    private List<UserEntity> getSaveListIntoDatabase(List<User> list) {
        List<UserEntity> convertedList = new ArrayList<>();
        return convertedList;
    }

    private List<UserEntity> getSortedListUser(CharSequence query) {
        List<UserEntity> convertedList = new ArrayList<>();
        return convertedList;
    }

    private User updateUserName(String prefix, User item) {
        item.setFirstName(prefix + item.getFirstName());
        return item;
    }

    private boolean isDataValid(boolean o1, boolean o2) {
        return o1 && o2;
    }

    @Override
    public void introductionPublishSubject() {
        PublishSubject<Integer> source = PublishSubject.create();

        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(getSecondObserver());
        source.onNext(4);
        source.onComplete();

    }

    @Override
    public void introductionReplaySubject() {
        ReplaySubject<Integer> source = ReplaySubject.create();

        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        source.subscribe(getSecondObserver());
    }

    @Override
    public void introductionBehaviorSobject() {
        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(getSecondObserver());
        source.onNext(4);
        source.onComplete();
    }

    @Override
    public void introductionAsyncSubject() {
        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(getSecondObserver());
        source.onNext(4);
        source.onComplete();

    }

    @Override
    public Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Observer", " First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.d("Observer", " First onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Observer", " First onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Observer", " First onComplete");
            }
        };
    }

    @Override
    public Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Observer", " Second onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.d("Observer", " Second onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Observer", " Second onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Observer", " Second onComplete");
            }
        };
    }
}
