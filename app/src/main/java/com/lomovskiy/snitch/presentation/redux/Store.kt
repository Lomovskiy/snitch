package com.lomovskiy.snitch.presentation.redux

typealias Subscription<S> = (S) -> Unit

class SubStateSubscription<State, SubState>(
    private val transformation: (State) -> SubState?,
    private val onStateChanged: (subState: SubState, isInitialState: Boolean) -> Unit
)

interface Store<S> {

    fun getState(): S

    fun dispatch(action: Action)

    fun subscribe(subscription: Subscription<S>)

    fun unsubscribe(subscription: Subscription<S>)

}

abstract class AbstractStore<S>(
    initialState: S,
    private val middlewares: List<Middleware<S>>,
    private val reducers: List<Reducer<S>>
) : Store<S> {

    internal val subscriptions = mutableListOf<Subscription<S>>()
    internal val pendingSubscriptions = mutableListOf<Subscription<S>>()
    internal val pendingUnsubscribed = mutableListOf<Subscription<S>>()

    private var currentState: S = initialState
    private var isDispatching = false

    override fun getState() = currentState

    override fun dispatch(action: Action) {
        val newAction = applyMiddlewares(action, currentState)
        val newState = applyReducers(newAction, currentState)
        if (newState == currentState) {
            return
        }
        currentState = newState
        isDispatching = true
        subscriptions.forEach {
            if (!pendingUnsubscribed.contains(it)) {
                it(currentState)
            }
        }
        isDispatching = false
        addPendingSubscriptions()
        removePendingUnsubscribed()
    }

    private fun addPendingSubscriptions() {
        subscriptions.addAll(pendingSubscriptions)
        pendingSubscriptions.clear()
    }

    private fun removePendingUnsubscribed() {
        subscriptions.removeAll(pendingUnsubscribed)
        pendingUnsubscribed.clear()
    }

    private fun applyMiddlewares(action: Action, state: S): Action {
        return next(0)(action, state)
    }

    private fun next(index: Int): (Action, S) -> Action {
        if (index == middlewares.size) {
            return { action, _ -> action }
        }
        return { action, state ->
            middlewares[index].handleAction(
                state,
                action,
                next(index = index + 1)
            )
        }
    }

    private fun applyReducers(action: Action, state: S): S {
        var newState = state
        for (reducer in reducers) {
            newState = reducer.reduce(action, newState)
        }
        return newState
    }

    override fun subscribe(subscription: Subscription<S>) {
        if (isDispatching) {
            pendingSubscriptions.add(subscription)
        } else {
            subscriptions.add(subscription)
        }
        subscription(currentState)
    }

    override fun unsubscribe(subscription: Subscription<S>) {
        if (isDispatching) {
            pendingUnsubscribed.add(subscription)
        } else {
            subscriptions.remove(subscription)
        }
        pendingSubscriptions.remove(subscription)
    }

}

class AppStore(
    initialState: AppState,
    middlewares: List<Middleware<AppState>>,
    reducers: List<Reducer<AppState>>
) : AbstractStore<AppState>(
    initialState,
    middlewares,
    reducers
)
