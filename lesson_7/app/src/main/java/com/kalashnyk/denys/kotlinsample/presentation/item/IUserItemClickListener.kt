package com.kalashnyk.denys.kotlinsample.presentation.item

interface IUserItemClickListener<M> {
    fun openDetail(m: M)
}