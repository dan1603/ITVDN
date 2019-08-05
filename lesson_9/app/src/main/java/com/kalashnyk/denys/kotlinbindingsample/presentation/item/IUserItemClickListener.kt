package com.kalashnyk.denys.kotlinbindingsample.presentation.item

interface IUserItemClickListener<M> {
    fun openDetail(m: M)
}