package com.kalashnyk.denys.rxjava2lessons_1.utils.rx_utils;

import android.widget.EditText;

import com.kalashnyk.denys.rxjava2lessons_1.model.User;
import com.kalashnyk.denys.rxjava2lessons_1.model.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

interface IRxUtils {

    Observable<String> introductionJustOperator(String string);

    Observable<String> introductionErrorOperator(Throwable t);

    Observable<Boolean> introductionMapOperator(String stringForMap);

    Observable<List<UserEntity>> introductionFlatMapOperator(List<User> list);

    Observable<List<UserEntity>> introductionSwitchMapOperator(EditText editText);

    Observable<List<User>> introductionConcatMapOperator(List<User> list);

    void introductionSubscribeOperator();

    void introductionZipOperator(Observable<User> dataFromNetwork, Observable<UserEntity> dataFromDatabase);

    void introductionMergeOperator(Observable<User> dataFromNetwork, Observable<User> dataFromDatabase);

    void introductionCombineLatestOperator(EditText editTextLogin, EditText editTextPassword);

    void introductionSubscribeOnWithObserveOnOperators();

    void introductionPublishSubject();

    void introductionReplaySubject();

    void introductionBehaviorSobject();

    void introductionAsyncSubject();

    Observer<Integer> getFirstObserver();

    Observer<Integer> getSecondObserver();
}
