package android.com.mykotlin_heima.presenter.interf

interface HomeFragmentPresenterF {
    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }
    fun loadDatas()
    abstract fun loadMoreDatas(offset: Int)
}